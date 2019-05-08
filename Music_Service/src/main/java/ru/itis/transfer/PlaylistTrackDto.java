package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Track;
import ru.itis.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistTrackDto {
    private String name;
    private String genre;
    private Long duration;
    private User author;

    public static PlaylistTrackDto from(Track track) {
        return PlaylistTrackDto.builder()
                .name(track.getName())
                .genre(track.getGenre())
                .duration(track.getDuration())
                .author(track.getAuthor())
                .build();
    }

    public static List<PlaylistTrackDto> from(List<Track> tracks) {
        return tracks.stream().map(PlaylistTrackDto::from).collect(Collectors.toList());
    }
}
