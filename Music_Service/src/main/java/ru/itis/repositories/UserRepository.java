package ru.itis.repositories;

import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {
    List<User> findAllByName (String name);
    List<User> findAllParticipant();
    List<User> getParticipantsList(String cookieValue);
    List<User> findAllBySearch(String title);

    void addParticipant(String coolieValue, User model);
    void deleteParticipants(String cookieValue, User model);
    void deleteParticipantBasket(String cookieValue);

    Optional<User> findByLogin(String login);
    Optional<User> findByCookie(String cookieValue);


}