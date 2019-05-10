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
public class CommentRepositoryImpl implements CommentRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO user_comment(title, description, date, user_id, track_id, event_id)" +
            "VALUES (?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_INSERT_WITH_EVENT = "INSERT INTO user_comment(description, date, user_id, event_id)" +
            "VALUES (?, ?, ?, ?);";

    //language=SQL
    private static final  String SQL_UPDATE = "UPDATE user_comment SET " +
            "title = ?, description = ?, date = ?, user_id = ?, track_id = ?, event_id = ?" +
            " WHERE id = ?";

    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM user_comment where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * from user_comment where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * from user_comment";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_EVENT = "select *, login, avatar " +
            "from user_comment join user_entity " +
            "on event_id = ? and user_entity.id in (select user_id from user_comment where event_id = ?)";

    @Autowired
    public CommentRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Comment> commentRowMapper = (resultSet, i) ->
    {
        User user = User.builder()
                .id(resultSet.getLong("user_id"))
                .login(resultSet.getString("login"))
                .avatar(resultSet.getString("avatar"))
                .build();

        return Comment.builder()
                .description(resultSet.getString("description"))
                .title(resultSet.getString("title"))
                .date(resultSet.getTimestamp("date").toLocalDateTime())
                .user(user)
                .build();
    };

    @Override
    public List<Comment> findAllByTrack(Track track) {
        return null;
    }

    @Override
    public List<Comment> findAllByEvent(Long eventId) {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_EVENT, commentRowMapper, eventId, eventId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Comment> findAllByUser(User user) {
        return null;
    }

    @Override
    public void saveByEvent(Comment model) {
        jdbcTemplate.update(SQL_INSERT_WITH_EVENT,model.getDescription(),
                model.getDate(), model.getUser().getId(), model.getEvent().getId());
    }

    @Override
    public Optional<Comment> find(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, commentRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Comment model) {
        jdbcTemplate.update(SQL_INSERT, model.getTitle(), model.getDescription(),
                model.getDate(), model.getUser().getId(),
                model.getTrack().getId(), model.getEvent().getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Comment model) {
        jdbcTemplate.update(SQL_UPDATE, model.getTitle(), model.getDescription(),
                model.getDate(), model.getUser().getId(),
                model.getTrack().getId(), model.getEvent().getId(), model.getId());
    }

    @Override
    public List<Comment> findAll() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, commentRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}