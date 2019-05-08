package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.models.*;

import javax.sql.DataSource;
import java.util.*;

@Component
public class UserRepositoryImpl implements UserRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO user_entity(login, surname, first_name, country, birthdate, email, hash_password, cookie)" +
            " values (?, ?, ?, ?, ?, ?, ?, ?)";

    //language=SQL
    private static final  String SQL_UPDATE = "UPDATE user_entity SET " +
            "surname = ?, first_name = ?, country = ?, cookie = ?, email = ?, birthdate = ?, hash_password = ?" +
            " WHERE login = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID =
            "SELECT * FROM user_entity WHERE id = ?";

    //language=SQL
    private static final String SQL_DELETE =
            "delete from user_entity where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_NAME =
            "SELECT * FROM user_entity WHERE first_name = ? and surname = ? LIMIT 1";

    //language=SQL
    private static final String SQL_FIND_BY_LOGIN = "SELECT * FROM user_entity where login = ?";

    //language=SQL
    private static final String SQL_FIND_ALL =
            "SELECT * FROM user_entity";

    //language=SQL
    private static final String SQL_FIND_BY_COOKIE = "select * from user_entity where cookie = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_PARTICIPANTS =
            "SELECT * FROM user_entity where id in (select participant_id from event_participant)";

    //language=SQL
    private static final String SQL_FIND_PARTICIPANT_BASKET = "select * from user_entity where " +
            "id in (select participant_id from participant_basket where id = ?)";

    //language=SQL
    private static final String SQL_ADD_PARTICIPANT = "insert into participant_basket(id, participant_id) " +
            "values (?, ?)";

    //language=SQL
    private static final String SQL_DELETE_PARTICIPANT = "delete from participant_basket where id = ? and participant_id = ?";

    //language=SQL
    private static final String SQL_DELETE_PARTICIPANT_BASKET = "delete from participant_basket where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_SEARCH = "select * from user_entity where lower(login) like lower('%' || ? || '%')";

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (resultSet, i) ->
            User.builder()
            .id(resultSet.getLong("id"))
            .surname(resultSet.getString("surname"))
            .firstName(resultSet.getString("first_name"))
            .country(resultSet.getString("country"))
            .birthdate(resultSet.getDate("birthdate") == null ? null : resultSet.getDate("birthdate").toLocalDate())
            .login(resultSet.getString("login"))
            .hashPassword(resultSet.getString("hash_password"))
            .email(resultSet.getString("email"))
            .cookie(resultSet.getString("cookie"))
            .build();

    @Override
    public Optional<User> find(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User model) {
        jdbcTemplate.update(SQL_INSERT, model.getLogin(), model.getSurname(), model.getFirstName(),
                model.getCountry(), model.getBirthdate(), model.getEmail(), model.getHashPassword(), model.getCookie());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(User model) {
        jdbcTemplate.update(SQL_UPDATE, model.getSurname(), model.getFirstName(), model.getCountry(),
                model.getCookie(), model.getEmail(), model.getBirthdate(), model.getHashPassword(), model.getLogin());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public List<User> findAllByName(String name) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_NAME, userRowMapper, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findAllParticipant() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_PARTICIPANTS, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> getParticipantsList(String cookieValue) {
        try {
            return jdbcTemplate.query(SQL_FIND_PARTICIPANT_BASKET, userRowMapper, cookieValue);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findAllBySearch(String title) {
        StringBuilder searchTitle = new StringBuilder().append('%').append(title).append('%');
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_SEARCH, userRowMapper, searchTitle);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void addParticipant(String coolieValue, User model) {
        jdbcTemplate.update(SQL_ADD_PARTICIPANT, coolieValue, model.getId());
    }

    @Override
    public void deleteParticipants(String cookieValue, User model) {
        jdbcTemplate.update(SQL_DELETE_PARTICIPANT, cookieValue, model.getId());
    }

    @Override
    public void deleteParticipantBasket(String cookieValue) {
        jdbcTemplate.update(SQL_DELETE_PARTICIPANT_BASKET, cookieValue);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_LOGIN, userRowMapper, login));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByCookie(String cookieValue) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_COOKIE, userRowMapper, cookieValue));
        }  catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}