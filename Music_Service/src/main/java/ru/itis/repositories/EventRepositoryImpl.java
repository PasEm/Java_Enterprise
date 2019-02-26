package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.dto.UserDto;
import ru.itis.models.*;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class EventRepositoryImpl implements EventRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO music_event(name, address, city, country, date, sale_site)" +
            "VALUES (?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final  String SQL_UPDATE = "UPDATE music_event SET " +
            "name = ?, address = ?, city = ?, country = ?, date = ?, sale_site = ?" +
            " WHERE id = ?";

    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM music_event WHERE id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * from music_event where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from music_event";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_PARTICIPANTS_ = "select * from music_event where " +
            "id in (select event_id from  event_participant where " +
            "participant_id = any (?::bigint[]))";

    private static final String SQL_FIND_ALL_BY_PARTICIPANTS = "select * from music_event where " +
            "id in (select event_id from  event_participant where " +
            "participant_id = any (?::bigint[]))";

    //language=SQL
    private static final String SQL_SELECT_EVENT_WITH_AUTHORS_BY_ID = null;

    public EventRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Event> eventRowMapper = (resultSet, i) ->
            Event.builder()
            .name(resultSet.getString("name"))
            .address(resultSet.getString("address"))
            .city(resultSet.getString("city"))
            .country(resultSet.getString("country"))
            .date(resultSet.getTimestamp("date").toLocalDateTime())
            .saleSite(resultSet.getString("sale_site"))
            .build();

    @Override
    public List<Event> findAllByCountry(String country) {
        return null;
    }

    @Override
    public List<Event> findAllByCity(String city) {
        return null;
    }

    @Override
    public List<Event> findAllByAuthor(User author) {
        return null;
    }

    @Override
    public List<Event> findAllByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public List<Event> findAllEventsByParticipants(List<UserDto> participants) {
        long[] id = new long[participants.size()];
        for (int i = 0; i < id.length; i++){
            id[i] = participants.get(i).getId();
        }
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_PARTICIPANTS, eventRowMapper, (Object) id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Optional<Event> find(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, eventRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Event model) {
        jdbcTemplate.update(SQL_INSERT, model.getName(), model.getAddress(),
                model.getCity(), model.getCountry(), model.getDate(), model.getSaleSite());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Event model) {
        jdbcTemplate.update(SQL_UPDATE, model.getName(), model.getAddress(), model.getCity(),
                model.getCountry(), model.getDate(), model.getSaleSite(), model.getId());
    }

    @Override
    public List<Event> findAll() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, eventRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}