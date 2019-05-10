package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.transfer.ExtendedPlaylistDto;
import ru.itis.transfer.PlaylistDto;
import ru.itis.transfer.PlaylistTrackDto;
import ru.itis.models.Playlist;
import ru.itis.models.User;
import ru.itis.services.LoginService;
import ru.itis.services.TrackCollectionService;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PlaylistController {
    @Autowired
    TrackCollectionService collectionService;

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @GetMapping("/playlist/{id}")
    @ResponseBody
    public ResponseEntity<ExtendedPlaylistDto> getPlaylist(HttpServletRequest request,
                                                           @CookieValue(name = "auth") String auth,
                                                           @PathVariable(name = "id") Long id) {
        Optional<User> user = userService.getCurrentUserByCookieValue(auth);
        user.ifPresent(user1 -> request.setAttribute("user", user1));

        return ResponseEntity.of(collectionService.getPlaylistById(id));
    }

    @GetMapping("/playlists")
    public String getPlaylistPage(HttpServletRequest request,
                                  @CookieValue(name = "auth") String auth) {
        User user = userService.getCurrentUserByCookieValue(auth).orElse(null);
        request.setAttribute("user", user);
        return "/playlists";
    }

    @GetMapping("/playlists/search")
    @ResponseBody
    public ResponseEntity<List<PlaylistDto>> getPlaylistPage(HttpServletRequest request,
                                                             @CookieValue(name = "auth") String auth,
                                                             @RequestParam(value = "searchPlaylist") String searchTitle) {
        Optional<User> user = userService.getCurrentUserByCookieValue(auth);
        List<PlaylistDto> playlists = searchTitle.equals("") ?
                collectionService.getPlaylists(user.orElse(null))
                : collectionService.searchPlaylists(searchTitle, user.orElse(null));

        request.setAttribute("playlists", playlists);
        return ResponseEntity.ok(playlists);
    }

    @PostMapping("/playlists")
    @ResponseBody
    public ResponseEntity<List<PlaylistTrackDto>> findPlaylist(@CookieValue(name = "auth") String auth,
                                                               @RequestParam(value = "currentPlaylistName") String playlistName) {
        User user = userService.getCurrentUserByCookieValue(auth).orElse(null);

        Playlist searchPlaylist = collectionService.getPlaylistByUserAndName(user, playlistName).orElse(null);

        ArrayList<PlaylistTrackDto> tracks = new ArrayList<>(collectionService.getPlaylistTracks(searchPlaylist));

        return ResponseEntity.ok(tracks);
    }

    @PostMapping("/playlist")
    public ResponseEntity<Object> createPlaylist(@RequestParam(value = "name") String name,
                                                 @CookieValue(name = "auth") String auth) {
        Optional<User> user = userService.getCurrentUserByCookieValue(auth);
        collectionService.createPlaylist(name, user.get());
        return ResponseEntity.ok().build();
    }
}