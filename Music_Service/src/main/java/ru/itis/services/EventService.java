package ru.itis.services;

import ru.itis.models.User;
import ru.itis.transfer.CommentDto;
import ru.itis.transfer.EventDto;
import ru.itis.transfer.ExtendedEventDto;
import ru.itis.transfer.UserDto;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

public interface EventService {
    void clearParticipantBasket(String cookieValue);
    List<UserDto> deleteParticipant(String userLogin, String cookieValue);
    List<UserDto> addParticipant(String userLogin, String cookieValue);

    String getBasketId(Cookie[] cookies);

    Optional<ExtendedEventDto> getEventById(Long id);

    List<UserDto> getAllParticipants();
    List<UserDto> searchParticipants(String title);
    List<UserDto> getParticipantBasket(String cookieValue);

    List<EventDto> getEventsByParticipants(List<UserDto> participants);

    List<CommentDto> addComment(Long eventId, String comment, User user);
}
