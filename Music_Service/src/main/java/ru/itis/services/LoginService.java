package ru.itis.services;

import ru.itis.models.User;

import java.util.Optional;

public interface LoginService {
    Optional<String> login(String login, String password);

    boolean isExistsByCookie(String cookie);
    void signOut(User user);
}
