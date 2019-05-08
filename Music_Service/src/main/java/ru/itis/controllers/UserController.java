package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.forms.ProfileForm;
import ru.itis.models.User;
import ru.itis.services.LoginService;
import ru.itis.services.UserService;
import ru.itis.transfer.ExtendedUserDto;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String getUserProfile(HttpServletRequest request) {
        Optional<User> user = userService.getCurrentUserByCookieValue(request.getCookies());
        user.ifPresent(user1 -> request.setAttribute("user", user1));

        return "/profile";
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<ExtendedUserDto> getUser(@PathVariable(name = "id") Long id,
                                                   HttpServletRequest request) {
        Optional<User> user = userService.getCurrentUserByCookieValue(request.getCookies());
        user.ifPresent(user1 -> request.setAttribute("user", user1));

        return ResponseEntity.of(userService.getUserById(id));
    }

    @PostMapping("/profile")
    public String updateUser(HttpServletRequest request) {
        User user = userService.getCurrentUserByCookieValue(request.getCookies()).get();

        String email = request.getParameter("email");
        String surname = request.getParameter("surname");
        String firstName = request.getParameter("first_name");
        String country = request.getParameter("country");
        String date = request.getParameter("birthdate");
        LocalDate birthdate = date.equals("") ? null : Date.valueOf(date).toLocalDate();
        String lastPassword = request.getParameter("lastPassword");
        String newPassword = request.getParameter("newPassword");

        ProfileForm userForm = ProfileForm.builder()
                .login(user.getLogin())
                .email(email.equals("") ? user.getEmail() : email)
                .firstName(firstName.equals("") ? user.getFirstName() : firstName)
                .surname(surname.equals("") ? user.getSurname() : surname)
                .country(country.equals("") ? user.getCountry() : country)
                .birthdate(birthdate == null ? user.getBirthdate() : birthdate)
                .newPassword(newPassword)
                .lastPassword(lastPassword)
                .build();

        userService.updateUser(userForm, user);

        return "redirect:/main";
    }

}