package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Track;
import ru.itis.models.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackDto {
    private Long id;
    private String name;
    private String url;
    private String releaseDate;
    private UserDto author;
    private String avatar;

    public static TrackDto from(Track track) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return TrackDto.builder()
                .id(track.getId())
                .name(track.getName())
                .url(track.getUrl())
                .releaseDate(track.getReleaseDate().format(format))
                .author(UserDto.from(track.getAuthor()))
                .avatar(track.getAvatar())
                .build();
    }

    public static List<TrackDto> from(List<Track> tracks) {
        return tracks.stream().map(TrackDto::from).collect(Collectors.toList());
    }
}
