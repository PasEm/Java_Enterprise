package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.models.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class TrackRepositoryImpl implements TrackRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO track(name, release_date, genre, duration, url, author_id)" +
            "VALUES (?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final  String SQL_UPDATE = "UPDATE track SET " +
            "name = ?, duration = ?, author_id = ?, genre = ?, release_date = ?, url = ? " +
            "WHERE id = ?";

    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM track WHERE id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT user_entity.id as user_id, user_entity.login as user_login, " +
            "track.id, track.name, track.genre, track.duration, track.url, track.release_date, track.avatar " +
            "FROM track " +
            "join user_entity on user_entity.id = track.author_id";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * FROM track WHERE id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_AUTHOR = "select * from track where author_id = ? and name = ?";

    //language=SQL
    private static final String SQL_FIND_LIKED_BY_USER = "SELECT track.name, track.genre, track.duration, track.author_id, track.url " +
            "FROM track" +
            " JOIN viewed_track ON user_id = ? and liked = true";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_GENRE = "SELECT * FROM track WHERE track = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_AUTHOR = "SELECT * FROM track WHERE author_id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_DURATION = "SELECT * FROM track WHERE duration = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_PLAYLIST = "SELECT user_entity.id as user_id, user_entity.login as user_login, " +
            "track.id, track.name, track.genre, track.duration, track.url, track.release_date " +
            "FROM track " +
            "join user_entity on user_entity.id = track.author_id " +
            "where track.id in (select track_id from playlist_track where playlist_id = ?)";

    //language=SQL
    private static final String SQL_FIND_BY_PLAYLIST = "select * from track " +
            "where id = (select track_id from playlist_track where playlist_id = ? and track_id = ?)";

    @Autowired
    public TrackRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Track> trackRowMapper = (resultSet, i) ->
            Track.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .genre(resultSet.getString("genre"))
                    .duration(resultSet.getLong("duration"))
                    .releaseDate(resultSet.getDate("release_date").toLocalDate())
                    .avatar(resultSet.getString("avatar"))
                    .build();

    private RowMapper<Track> trackWithUserRowMapper = ((resultSet, i) ->
    {
        User author = User.builder()
                .id(resultSet.getLong("user_id"))
                .login(resultSet.getString("user_login"))
                .build();

        return Track.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .genre(resultSet.getString("genre"))
                .duration(resultSet.getLong("duration"))
                .author(author)
                .avatar(resultSet.getString("avatar"))
                .releaseDate(resultSet.getDate("release_date").toLocalDate())
                .build();
    });


    @Override
    public List<Track> findAllByDuration(Long duration) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_DURATION, trackRowMapper);
    }

    @Override
    public List<Track> findAllByGenre(String genre) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_GENRE, trackRowMapper);
    }

    @Override
    public List<Track> findAllByAuthor(User author) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_AUTHOR, trackRowMapper, author.getId());
    }

    @Override
    public List<Track> findLiked(User user) {
        return jdbcTemplate.query(SQL_FIND_LIKED_BY_USER, trackRowMapper, user.getId());
    }

    @Override
    public List<Track> findAllByPlaylist(Playlist playlist) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_PLAYLIST, trackWithUserRowMapper, playlist.getId());
    }

    @Override
    public Optional<Track> findByAuthor(User user, String trackName) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_AUTHOR, trackRowMapper, user.getId(), trackName));
    }

    @Override
    public Optional<Track> findByPlaylist(Playlist playlist, Track track) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_PLAYLIST, trackRowMapper, playlist.getId(), track.getId()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Track> find(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, trackRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Track model) {
        jdbcTemplate.update(SQL_INSERT, model.getName(), model.getReleaseDate(), model.getGenre(),
                model.getUrl(), model.getDuration(), model.getAuthor().getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Track model) {
        jdbcTemplate.update(SQL_UPDATE, model.getName(), model.getDuration(), model.getAuthor().getId(),
                model.getGenre(), model.getReleaseDate(), model.getUrl());
    }

    @Override
    public List<Track> findAll() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, trackWithUserRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}