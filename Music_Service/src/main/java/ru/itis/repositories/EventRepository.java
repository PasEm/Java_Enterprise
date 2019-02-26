package ru.itis.repositories;

import ru.itis.dto.UserDto;
import ru.itis.models.Event;
import ru.itis.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends CrudRepository<Event> {
    List<Event> findAllByCountry (String country);
    List<Event> findAllByCity (String city);
    List<Event> findAllByAuthor (User author);
    List<Event> findAllByDate (LocalDateTime date);

    List<Event> findAllEventsByParticipants(List<UserDto> participants);
}