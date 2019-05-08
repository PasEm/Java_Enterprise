package ru.itis.services;

import ru.itis.forms.ProfileForm;
import ru.itis.forms.UserForm;
import ru.itis.models.User;
import ru.itis.transfer.ExtendedUserDto;

import javax.servlet.http.Cookie;
import java.util.Optional;

public interface UserService {
    void addUser(UserForm userForm);
    void updateUser(ProfileForm profileForm, User user);

    String isEqualPassword(String newPassword, String lastPassword, String userPassword);

    Optional<User> getCurrentUserByCookieValue(Cookie[] cookies);

    Optional<ExtendedUserDto> getUserById(Long id);
}