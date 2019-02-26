package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.dto.PlaylistDto;
import ru.itis.dto.PlaylistTrackDto;
import ru.itis.models.Playlist;
import ru.itis.models.User;
import ru.itis.services.LoginService;
import ru.itis.services.TrackCollectionService;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/my_collection/ftl")
public class UserCollectionServlet extends HttpServlet {
    private TrackCollectionService collectionService;
    private UserService userService;
    private LoginService loginService;
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        collectionService = (TrackCollectionService) context.getAttribute("trackCollectionService");
        userService = (UserService) context.getAttribute("userService");
        loginService = (LoginService) context.getAttribute("loginService");
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<User> user = userService.getCurrentUserByCookieValue(request.getCookies());
        ArrayList<PlaylistDto> playlists = new ArrayList<>(collectionService.getPlaylists(user.get()));

        request.setAttribute("playlists", playlists);

        String searchTitle = request.getParameter("searchPlaylist");
        String authorisationStatus = request.getParameter("dropdownProfile");

        String json = null;

        if (searchTitle != null) {
            List<PlaylistDto> result = collectionService.searchPlaylists(searchTitle, user.get());
            json = searchTitle.equals("") ? objectMapper.writeValueAsString(null) : objectMapper.writeValueAsString(result);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
        if (authorisationStatus != null) {
            if (authorisationStatus.equals("load")) {
                json = objectMapper.writeValueAsString(user);
            } else {
                json = objectMapper.writeValueAsString("");
                loginService.signOut(user.get());
            }
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
        if (json == null) {
            request.getRequestDispatcher("/ftl/playlists.ftl").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.getCurrentUserByCookieValue(request.getCookies()).orElse(null);

        String json;
        String playlistList = request.getParameter("currentPlaylistName");
        String playlistPlay = request.getParameter("playlist");

        String playlistName = playlistList == null ? playlistPlay : playlistList;

        Playlist playlist = collectionService.getPlaylistByUserAndName(user, playlistName).orElse(null);

        ArrayList<PlaylistTrackDto> tracks = new ArrayList<>(collectionService.getPlaylistTracks(playlist));
        json = objectMapper.writeValueAsString(tracks);

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}