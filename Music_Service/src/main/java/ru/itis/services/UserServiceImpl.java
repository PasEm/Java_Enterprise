package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.forms.ProfileForm;
import ru.itis.forms.UserForm;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.transfer.ExtendedUserDto;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
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
    public void updateUser(ProfileForm userForm, User user) {
        String password = isEqualPassword(userForm.getNewPassword(), userForm.getLastPassword(), user.getHashPassword());

        user.setFirstName(userForm.getFirstName());
        user.setSurname(userForm.getSurname());
        user.setEmail(userForm.getEmail());
        user.setCountry(userForm.getCountry());
        user.setBirthdate(userForm.getBirthdate());
        user.setLogin(userForm.getLogin());
        user.setHashPassword(password);

        userRepository.update(user);
    }

    @Override
    public String isEqualPassword(String newPassword, String lastPassword, String userPassword) {
        newPassword = newPassword == null ? null : newPassword.equals("") ? null : newPassword;
        lastPassword = lastPassword == null ? null : lastPassword.equals("") ? null : lastPassword;
        if (newPassword == null || !newPassword.equals(lastPassword) || !encoder.matches(newPassword, userPassword)) {
            return userPassword;
        } else return encoder.encode(lastPassword);
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

    @Override
    public Optional<ExtendedUserDto> getUserById(Long id) {
        Optional<User> user = userRepository.find(id);
        if (user.isPresent()) {
            return Optional.ofNullable(ExtendedUserDto.from(user.get()));
        } else return Optional.empty();
    }
}