package ru.itis.services;

import ru.itis.forms.UserForm;
import ru.itis.models.User;

import javax.servlet.http.Cookie;
import java.util.Optional;

public interface UserService {
    void addUser(UserForm userForm);
    void updateUser(UserForm userForm, boolean withPassword);

    boolean isEqualPassword(String newPassword, String lastPassword);

    Optional<User> getCurrentUserByCookieValue(Cookie[] cookies);
}