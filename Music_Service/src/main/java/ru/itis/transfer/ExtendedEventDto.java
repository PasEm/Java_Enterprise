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
public class ExtendedEventDto {
    private Long id;
    private String name;
    private String city;
    private String country;
    private String date;
    private String saleSite;
    private String avatar;

    private List<UserDto> participants;

    private List<CommentDto> comments;

    public static ExtendedEventDto from(Event event) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy, hh:mm");
        return ExtendedEventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .city(event.getCity())
                .country(event.getCountry())
                .date(event.getDate().format(format))
                .saleSite(event.getSaleSite())
                .avatar(event.getAvatar())
                .participants(UserDto.from(event.getParticipants()))
                .comments(CommentDto.from(event.getComments()))
                .build();
    }

    public static List<EventDto> from(List<Event> events) {
        return events.stream().map(EventDto::from).collect(Collectors.toList());
    }
}
