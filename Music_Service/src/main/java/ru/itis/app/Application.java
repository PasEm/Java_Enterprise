package ru.itis.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.configuration.JavaConfig;
import ru.itis.models.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/MusicService");
        jdbcTemplate = new JdbcTemplate(dataSource);

        long[] id = new long[3];
        id[0] = 10001L;
        id[1] = 3001L;
        id[2] = 4001L;


        List<Event> events = findAllEventsByParticipants(id);
        for (Event event: events) {
            System.out.println(event);
        }
    }

    private static final String SQL_FIND_ALL_BY_PARTICIPANTS = "select * from music_event where " +
            "id in (select event_id from  event_participant where " +
            "participant_id = any (?::bigint[]))";

    private static JdbcTemplate jdbcTemplate;

    private static RowMapper<Event> eventRowMapper = (resultSet, i) ->
            Event.builder()
                    .name(resultSet.getString("name"))
                    .address(resultSet.getString("address"))
                    .city(resultSet.getString("city"))
                    .country(resultSet.getString("country"))
                    .date(resultSet.getTimestamp("date").toLocalDateTime())
                    .saleSite(resultSet.getString("sale_site"))
                    .build();

    public static List<Event> findAllEventsByParticipants(long[] id) {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_PARTICIPANTS, eventRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
