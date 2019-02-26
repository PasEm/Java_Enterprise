package ru.itis.services;

import ru.itis.dto.PlaylistDto;
import ru.itis.dto.PlaylistTrackDto;
import ru.itis.dto.TrackDto;
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

    Optional<Playlist> getPlaylistByUserAndName(User user, String name);

    Optional<Track> getTrack(String trackName, String authorName);

    List<PlaylistTrackDto> getPlaylistTracks(Playlist playlist);

    List<TrackDto> getAllTracks();

    List<PlaylistDto> getPlaylists(User user);
    List<PlaylistDto> searchPlaylists(String title, User user);
}