package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.transfer.TrackDto;
import ru.itis.models.User;
import ru.itis.services.LoginService;
import ru.itis.services.TrackCollectionService;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    TrackCollectionService collectionService;

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @GetMapping("/main")
    public String getMainPage(HttpServletRequest request,
                              @CookieValue(name = "auth", required = false) String auth) {
        Optional<User> user = userService.getCurrentUserByCookieValue(auth);
        user.ifPresent(user1 -> request.setAttribute("user", user1));
        return "/main";
    }

    @PostMapping("/main")
    public ResponseEntity<List<TrackDto>> getTracks() {
        List<TrackDto> tracks = collectionService.getAllTracks();
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/404")
    public ResponseEntity<Object> getNotFoundPage() {
        return ResponseEntity.status(404).build();
    }
}