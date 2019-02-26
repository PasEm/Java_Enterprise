package ru.itis.repositories;

import ru.itis.models.Playlist;
import ru.itis.models.Track;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface TrackRepository extends CrudRepository<Track> {
    List<Track> findAllByDuration (Long duration);
    List<Track> findAllByGenre (String genre);
    List<Track> findAllByAuthor (User author);
    List<Track> findLiked(User user);
    List<Track> findAllByPlaylist(Playlist playlist);

    Optional<Track> findByAuthor(User user, String trackName);
    Optional<Track> findByPlaylist(Playlist playlist, Track track);
}