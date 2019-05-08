package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Track;
import ru.itis.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackDto {
    private String name;
    private String url;
    private LocalDate releaseDate;
    private UserDto author;

    public static TrackDto from(Track track) {
        return TrackDto.builder()
                .name(track.getName())
                .url(track.getUrl())
                .releaseDate(track.getReleaseDate())
                .author(UserDto.from(track.getAuthor()))
                .build();
    }

    public static List<TrackDto> from(List<Track> tracks) {
        return tracks.stream().map(TrackDto::from).collect(Collectors.toList());
    }
}
