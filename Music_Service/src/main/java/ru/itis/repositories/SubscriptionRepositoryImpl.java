package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class SubscriptionRepositoryImpl implements SubscriptionRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO music_subscription(name, subscriber_id, user_id)" +
            "VALUES (?, ?, ?)";

    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM music_subscription WHERE user_id = ? and subscriber_id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * FROM music_subscription WHERE id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM music_subscription";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_MAX_COUNT = "SELECT * FROM music_subscription";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_MIN_COUNT = "";

    //language=SQL
    private static final  String SQL_UPDATE = "UPDATE music_subscription SET " +
            "name = ?, subscriber_id = ?, user_id = ? " +
            "WHERE id = ?";

    public SubscriptionRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Subscription> subscriptionRowMapper = (resultSet, i) ->
            Subscription.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .build();

    @Override
    public List<Subscription> findAllByMaxCount() {
        return null;
    }

    @Override
    public List<Subscription> findAllByMinCount() {
        return null;
    }

    @Override
    public Optional<Subscription> find(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, subscriptionRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Subscription model) {
        jdbcTemplate.update(SQL_INSERT, model.getName(), model.getSubscriber().getId(), model.getUser().getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Subscription model) {
        jdbcTemplate.update(SQL_UPDATE, model.getName(), model.getSubscriber().getId(), model.getUser().getId(), model.getId());
    }

    @Override
    public List<Subscription> findAll() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, subscriptionRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}