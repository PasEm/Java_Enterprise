package ru.itis.services;

import ru.itis.transfer.ExtendedPlaylistDto;
import ru.itis.transfer.PlaylistDto;
import ru.itis.transfer.PlaylistTrackDto;
import ru.itis.transfer.TrackDto;
import ru.itis.models.Playlist;
import ru.itis.models.Track;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface TrackCollectionService {
    void createPlaylist(String name, User user);
    void addTrack(Playlist playlist, Track track);
    void deleteTrack(Playlist playlist, Track track);
    void deletePlaylist(String name, User user);

    Optional<ExtendedPlaylistDto> getPlaylistById(Long playlistId);

    Optional<Playlist> getPlaylistByUserAndName(User user, String name);

    Optional<Track> getTrack(String trackName, String authorName);

    List<PlaylistTrackDto> getPlaylistTracks(Playlist playlist);

    List<TrackDto> getAllTracks();

    List<PlaylistDto> getPlaylists(User user);
    List<PlaylistDto> searchPlaylists(String title, User user);
}