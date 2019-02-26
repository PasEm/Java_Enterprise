package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private String name;
    private String city;
    private String country;
    private LocalDateTime date;
    private String saleSite;

    public static EventDto from(Event event) {
        return EventDto.builder()
                .name(event.getName())
                .city(event.getCity())
                .country(event.getCountry())
                .date(event.getDate())
                .saleSite(event.getSaleSite())
                .build();
    }

    public static List<EventDto> from(List<Event> events) {
        return events.stream().map(EventDto::from).collect(Collectors.toList());
    }
}