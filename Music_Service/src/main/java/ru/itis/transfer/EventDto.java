package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Event;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private Long id;
    private String name;
    private String city;
    private String country;
    private String date;
    private String saleSite;

    public static EventDto from(Event event) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy, hh:mm");
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .city(event.getCity())
                .country(event.getCountry())
                .date(event.getDate().format(format))
                .saleSite(event.getSaleSite())
                .build();
    }

    public static List<EventDto> from(List<Event> events) {
        return events.stream().map(EventDto::from).collect(Collectors.toList());
    }
}
