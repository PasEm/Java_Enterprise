package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.models.Comment;
import ru.itis.repositories.CommentRepository;
import ru.itis.transfer.CommentDto;
import ru.itis.transfer.EventDto;
import ru.itis.transfer.ExtendedEventDto;
import ru.itis.transfer.UserDto;
import ru.itis.models.Event;
import ru.itis.models.User;
import ru.itis.repositories.EventRepository;
import ru.itis.repositories.UserRepository;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class EventServiceImpl implements EventService {
    private UserRepository userRepository;
    private EventRepository eventRepository;
    private CommentRepository commentRepository;

    @Autowired
    public EventServiceImpl(UserRepository userRepository, EventRepository eventRepository, CommentRepository commentRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<UserDto> addParticipant(String userLogin, String cookieValue) {
        Optional<User> user = userRepository.findByLogin(userLogin);
        List<User> participants = userRepository.getParticipantsList(cookieValue);
        if (user.isPresent() && !participants.contains(user.get())) {
            userRepository.addParticipant(cookieValue, user.get());
            participants.add(user.get());
        }
        return UserDto.from(participants);
    }

    @Override
    public void clearParticipantBasket(String cookieValue) {
        userRepository.deleteParticipantBasket(cookieValue);
    }

    @Override
    public String getBasketId(Cookie[] cookies) {
        String cookieValue = null;

        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("participantBasket")) {
                cookieValue = cookie.getValue();
                break;
            }
        }
       return cookieValue;
    }

    @Override
    public Optional<ExtendedEventDto> getEventById(Long id) {
        Optional<Event> event = eventRepository.find(id);
        if (event.isPresent()) {
            List<User> participants = userRepository.findAllByEventId(id);
            List<Comment> comments = commentRepository.findAllByEvent(id);

            event.get().setParticipants(participants);
            event.get().setComments(comments);

            return Optional.ofNullable(ExtendedEventDto.from(event.get()));
        } else return Optional.empty();
    }

    @Override
    public List<UserDto> deleteParticipant(String userLogin, String cookieValue) {
        Optional<User> user = userRepository.findByLogin(userLogin);
        List<User> participants = userRepository.getParticipantsList(cookieValue);
        if (user.isPresent() && participants.contains(user.get())) {
            userRepository.deleteParticipants(cookieValue, user.get());
            participants.remove(user.get());
        }
        return UserDto.from(participants);
    }

    @Override
    public List<UserDto> getAllParticipants() {
        return UserDto.from(userRepository.findAllParticipant());
    }

    @Override
    public List<UserDto> searchParticipants(String title) {
        return UserDto.from(userRepository.findAllBySearch(title));
    }

    @Override
    public List<UserDto> getParticipantBasket(String cookieValue) {
        return UserDto.from(userRepository.getParticipantsList(cookieValue));
    }

    @Override
    public List<EventDto> getEventsByParticipants(List<UserDto> participants) {
        if (participants.size() > 0) {
            return EventDto.from(eventRepository.findAllEventsByParticipants(participants));
        } else return null;
    }

    @Override
    public List<CommentDto> addComment(Long eventId, String comment, User user) {
        Optional<Event> event = eventRepository.find(eventId);

        if (event.isPresent()) {
            commentRepository.saveByEvent(
                    Comment.builder()
                            .user(user)
                            .event(event.get())
                            .description(comment)
                            .date(LocalDateTime.now())
                            .build()
            );

            return CommentDto.from(commentRepository.findAllByEvent(eventId));
        } else return null;
    }
}