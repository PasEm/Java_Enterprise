package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.forms.LoginForm;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class LoginServiceImpl implements LoginService {
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public LoginServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    @Override
    public Optional<String> login(String login, String password) {
        LoginForm loginForm = LoginForm.builder()
                .login(login)
                .password(password)
                .build();

        Optional<User> user = userRepository.findByLogin(loginForm.getLogin());
        if (user.isPresent() && encoder.matches(loginForm.getPassword(), user.get().getHashPassword())) {
            String cookieValue = UUID.randomUUID().toString();
            user.get().setCookie(cookieValue);
            userRepository.update(user.get());
            return Optional.of(cookieValue);
        } else return Optional.empty();
    }

    @Override
    public boolean isExistsByCookie(String cookie) {
        return userRepository.findByCookie(cookie).isPresent();
    }

    @Override
    public void signOut(User user) {
        user.setCookie(null);
        userRepository.update(user);
    }
}
