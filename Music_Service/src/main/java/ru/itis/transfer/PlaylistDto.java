package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Playlist;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistDto {
    private String name;

    public static PlaylistDto from(Playlist playlist) {
        return PlaylistDto.builder()
                .name(playlist.getName())
                .build();
    }

    public static List<PlaylistDto> from(List<Playlist> playlists) {
        return playlists.stream().map(PlaylistDto::from).collect(Collectors.toList());
    }
}
