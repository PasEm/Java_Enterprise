package ru.itis.repositories;

import ru.itis.models.Playlist;
import ru.itis.models.Track;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends CrudRepository<Playlist> {
    void addTrack(Playlist playlist, Track track);
    void deleteTrack(Playlist playlist, Track track);
    void deleteByName(User user, String name);

    List<Playlist> findAllBySearch(String title, User user);
    List<Playlist> findAllByUser(User user);

    Optional<Playlist> findByUserAndPlaylistName(User user, String playlistName);
}