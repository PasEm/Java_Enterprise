package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.transfer.EventDto;
import ru.itis.transfer.UserDto;
import ru.itis.models.Event;
import ru.itis.models.User;
import ru.itis.repositories.EventRepository;
import ru.itis.repositories.UserRepository;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

@Component
public class EventServiceImpl implements EventService {
    private UserRepository userRepository;
    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(UserRepository userRepository, EventRepository eventRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addParticipant(String userLogin, String cookieValue) {
        Optional<User> user = userRepository.findByLogin(userLogin);
        if (user.isPresent() && !userRepository.getParticipantsList(cookieValue).contains(user.get()))
            userRepository.addParticipant(cookieValue, user.get());
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
    public Optional<EventDto> getEventById(Long id) {
        Optional<Event> event = eventRepository.find(id);
        if (event.isPresent()) {
            return Optional.ofNullable(EventDto.from(event.get()));
        } else return Optional.empty();
    }

    @Override
    public void deleteParticipant(String userLogin, String cookieValue) {
        Optional<User> user = userRepository.findByLogin(userLogin);
        if (userRepository.getParticipantsList(cookieValue).contains(user.get()))
            userRepository.deleteParticipants(cookieValue, user.get());
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
    public List<Event> getEventsByParticipants(List<UserDto> participants) {
        if (participants.size() > 0) {
            return eventRepository.findAllEventsByParticipants(participants);
        } else return null;
    }
}
