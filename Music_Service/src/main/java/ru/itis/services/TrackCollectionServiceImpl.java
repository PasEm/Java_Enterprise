package ru.itis.services;

import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.itis.transfer.ExtendedPlaylistDto;
import ru.itis.transfer.PlaylistDto;
import ru.itis.transfer.PlaylistTrackDto;
import ru.itis.transfer.TrackDto;
import ru.itis.models.Playlist;
import ru.itis.models.Track;
import ru.itis.models.User;
import ru.itis.repositories.PlaylistRepository;
import ru.itis.repositories.TrackRepository;
import ru.itis.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class TrackCollectionServiceImpl implements TrackCollectionService {
    private TrackRepository trackRepository;
    private PlaylistRepository playlistRepository;
    private UserRepository userRepository;

    @Autowired
    public TrackCollectionServiceImpl(TrackRepository trackRepository, @Qualifier("playlistRepositoryHibernateImpl") PlaylistRepository playlistRepository, UserRepository userRepository){
        this.playlistRepository = playlistRepository;
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createPlaylist(String name, User user) {
        Playlist playlist = Playlist.builder()
                .name(name)
                .creator(user)
                .build();
        playlistRepository.save(playlist);
    }

    @Override
    public void addTrack(Playlist playlist, Track track) {
        if (!trackRepository.findByPlaylist(playlist, track).isPresent()) {
            playlistRepository.addTrack(playlist, track);
        }
    }

    @Override
    public void deleteTrack(Playlist playlist, Track track) {
        playlistRepository.deleteTrack(playlist, track);
    }

    @Override
    public void deletePlaylist(String name, User user) {
        playlistRepository.deleteByName(user, name);
    }

    @Override
    public Optional<ExtendedPlaylistDto> getPlaylistById(Long playlistId) {
        Optional<Playlist> playlist = playlistRepository.find(playlistId);
        if (playlist.isPresent()) {
            return Optional.ofNullable(ExtendedPlaylistDto.from(playlist.get()));
        } else return Optional.empty();
    }

    @Override
    public Optional<Playlist> getPlaylistByUserAndName(User user, String name) {
        return playlistRepository.findByUserAndPlaylistName(user, name);
    }

    @Override
    public Optional<Track> getTrack(String trackName, String authorName) {
        Optional<User> author = userRepository.findByLogin(authorName);
        if (author.isPresent()) {
            return trackRepository.findByAuthor(author.get(), trackName);
        } else  return Optional.empty();
    }

    @Override
    public List<PlaylistTrackDto> getPlaylistTracks(Playlist playlist) {
        return PlaylistTrackDto.from(trackRepository.findAllByPlaylist(playlist));
    }

    @Override
    public List<TrackDto> getAllTracks() {
        return TrackDto.from(trackRepository.findAll());
    }

    @Override
    public List<PlaylistDto> getPlaylists(User user) {
        return PlaylistDto.from(playlistRepository.findAllByUser(user));
    }

    @Override
    public List<PlaylistDto> searchPlaylists(String title, User user) {
        return PlaylistDto.from(playlistRepository.findAllBySearch(title, user));
    }
}