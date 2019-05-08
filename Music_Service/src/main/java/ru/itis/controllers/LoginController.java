package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itis.forms.UserForm;
import ru.itis.models.User;
import ru.itis.services.LoginService;
import ru.itis.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Optional<String> authValue = loginService.login(login, password);
        if (authValue.isPresent()) {
            response.addCookie(new Cookie("auth", authValue.get()));
            return "redirect:/main";
        } else return "/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login";
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "/signUp";
    }

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody UserForm userForm) {
        userService.addUser(userForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/signOut")
    public String signOut(HttpServletRequest request) {
        Optional<User> user = userService.getCurrentUserByCookieValue(request.getCookies());
        user.ifPresent(user1 -> loginService.signOut(user1));
        return "/main";
    }
}