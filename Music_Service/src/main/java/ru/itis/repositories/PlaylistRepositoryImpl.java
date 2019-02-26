package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.*;

import javax.sql.DataSource;
import java.util.*;

public class PlaylistRepositoryImpl implements PlaylistRepository{
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO playlist(name, release_date, duration, track_count, user_entity_id)" +
            "VALUES (?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from playlist where id = ?";

    //language=SQL
    private static final String SQL_DELETE = "delete from playlist where id = ?";

    //language=SQL
    private static final String SQL_UPDATE = "UPDATE playlist SET " +
            "name = ?, release_date = ?, duration = ?, track_count = ?" +
            " WHERE id = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_USER = "delete from playlist where user_entity_id = ? and name = ?";

    //language=SQL
    private static final String SQL_FIND_BY_USER_AND_NAME = "select * from playlist" +
            " where user_entity_id = ? and name = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_USER = "SELECT * FROM playlist WHERE user_entity_id = ?";

    //language=SQL
    private static final String SQL_ADD_TRACK = "INSERT INTO playlist_track(track_id, playlist_id)" +
            " values (?, ?)";

    //language=SQL
    private static final String SQL_DELETE_TRACK ="DELETE from playlist_track WHERE playlist_id = ? and track_id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_SEARCH = "select * from playlist where name like ? and playlist.user_entity_id = ?";

    //language=SQL
    private static final String SQL_SELECT_PLAYLIST_WITH_TRACKS_BY_TRACKCOUNT =
            "select playlist.id as playlist_id, * " +
                    "from playlist " +
                    "join track on playlist.is_album = false " +
                    "join playlist_track on playlist_track.playlist_id = playlist.id and playlist_track.track_id = track.id " +
                    "where playlist.track_count = ? " +
                    "order by playlist.id;";

    public PlaylistRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Playlist> playlistRowMapper = (resultSet, i) ->
            Playlist.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .releaseDate(resultSet.getDate("release_date").toLocalDate())
            .duration(resultSet.getLong("duration"))
            .trackCount(resultSet.getLong("track_count"))
            .build();

    @Override
    public Optional<Playlist> find(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, playlistRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Playlist model) {
        jdbcTemplate.update(SQL_INSERT, model.getName(), model.getReleaseDate(), model.getDuration(), model.getTrackCount(), model.getUser().getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Playlist model) {
        jdbcTemplate.update(SQL_UPDATE, model.getName(), model.getReleaseDate(), model.getDuration(), model.getTrackCount());
    }

    @Override
    public List<Playlist> findAll() {
        return null;
    }

    @Override
    public List<Playlist> findAllByUser(User user) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_USER, playlistRowMapper, user.getId());
    }

    @Override
    public void addTrack(Playlist playlist, Track track) {
        jdbcTemplate.update(SQL_ADD_TRACK, track.getId(), playlist.getId());
    }

    @Override
    public void deleteTrack(Playlist playlist, Track track) {
        jdbcTemplate.update(SQL_DELETE_TRACK, playlist.getId(), track.getId());
    }

    @Override
    public Optional<Playlist> findByUserAndPlaylistName(User user, String playlistName) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_USER_AND_NAME, playlistRowMapper, user.getId(), playlistName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByName(User user, String name) {
        jdbcTemplate.update(SQL_DELETE_BY_USER, user.getId(), name);
    }

    @Override
    public List<Playlist> findAllBySearch(String title, User user) {
        StringBuilder searchTitle = new StringBuilder().append('%').append(title).append('%');
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_SEARCH, playlistRowMapper, searchTitle, user.getId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}