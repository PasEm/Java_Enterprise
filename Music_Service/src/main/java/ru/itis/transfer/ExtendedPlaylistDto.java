package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Playlist;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExtendedPlaylistDto {
    private Long id;

    private String name;

    private Long duration;
    private Long trackCount;

    private LocalDate releaseDate;

    private List<TrackDto> tracks;

    public static ExtendedPlaylistDto from(Playlist playlist) {
        return ExtendedPlaylistDto.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .duration(playlist.getDuration())
                .trackCount(playlist.getTrackCount())
                .releaseDate(playlist.getReleaseDate())
                .tracks(TrackDto.from(playlist.getTracks()))
                .build();
    }

    public static List<ExtendedPlaylistDto> from(List<Playlist> playlists) {
        return playlists.stream().map(ExtendedPlaylistDto::from).collect(Collectors.toList());
    }
}
