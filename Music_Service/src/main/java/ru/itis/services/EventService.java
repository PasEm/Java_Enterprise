package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.Event;
import ru.itis.models.User;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

public interface EventService {
    void deleteParticipant(String userLogin, String cookieValue);
    void addParticipant(String userLogin, String cookieValue);
    void clearParticipantBasket(String cookieValue);

    String getBasketId(Cookie[] cookies);

    List<UserDto> getAllParticipants();
    List<UserDto> searchParticipants(String title);
    List<UserDto> getParticipantBasket(String cookieValue);

    List<Event> getEventsByParticipants(List<UserDto> participants);
}
