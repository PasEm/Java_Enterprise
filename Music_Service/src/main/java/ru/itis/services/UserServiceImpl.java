package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.forms.UserForm;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import javax.servlet.http.Cookie;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void addUser(UserForm userForm) {
        User user = User.builder()
                .firstName(userForm.getFirstName())
                .surname(userForm.getSurname())
                .country(userForm.getCountry())
                .email(userForm.getEmail())
                .hashPassword(encoder.encode(userForm.getPassword()))
                .birthdate(userForm.getBirthdate())
                .login(userForm.getLogin())
                .cookie(null)
                .build();
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserForm userForm, boolean withPassword) {
        User user = User.builder()
                .login(userForm.getLogin())
                .firstName(userForm.getFirstName())
                .surname(userForm.getSurname())
                .country(userForm.getCountry())
                .email(userForm.getEmail())
                .hashPassword(withPassword ? encoder.encode(userForm.getPassword()) : userForm.getPassword())
                .birthdate(userForm.getBirthdate())
                .build();
        userRepository.update(user);
    }

    @Override
    public boolean isEqualPassword(String newPassword, String lastPassword) {
        return encoder.matches(newPassword, lastPassword);
    }

    @Override
    public Optional<User> getCurrentUserByCookieValue(Cookie[] cookies) {
        String cookieValue = null;
        if (cookies == null) {
            return Optional.empty();
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookieValue = cookie.getValue();
                break;
            }
        }
        return userRepository.findByCookie(cookieValue);
    }
}