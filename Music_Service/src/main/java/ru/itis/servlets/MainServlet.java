package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.dto.TrackDto;
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
import java.util.List;

@WebServlet("/main/ftl")
public class MainServlet extends HttpServlet {
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
        User user = userService.getCurrentUserByCookieValue(request.getCookies()).orElse(null);

        String authorisationStatus = request.getParameter("dropdownProfile");
        String json = null;

        if (authorisationStatus != null) {
            if (authorisationStatus.equals("load")) {
                json = objectMapper.writeValueAsString(user);
            } else {
                json = objectMapper.writeValueAsString("");
                loginService.signOut(user);
            }
            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        if (json == null) {
            request.getRequestDispatcher("/ftl/main.ftl").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json;

        List<TrackDto> tracks = collectionService.getAllTracks();
        json = objectMapper.writeValueAsString(tracks);

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}