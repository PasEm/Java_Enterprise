package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Course;
import ru.itis.models.Lesson;
import ru.itis.models.Person;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class LessonRepositoryImpl implements LessonRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "insert into lesson(name, time_begin, date_update, course_id, person_id, is_canceled) values (?, ?, ?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_UPDATE = "update lesson set name = ? and time_begin = ? and date_update = ? " +
            " and course_id = ? and person_id = ? and is_canceled = ? where id = ? and date_update > ?";

    //language=SQL
    private static final String SQL_DELETE = "delete from lesson where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from lesson where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from lesson";

    //language=SQL
    private static final String SQL_FIND_BY_NAME = "select * from lesson where name = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_COURSE = "select * from lesson where " +
            "course_id = (select id from course where course.name = ? limit 1) " +
            "and date_update >= ? and date_update < ?" +
            " order by date_update, time_begin asc";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_TIME = "select * from lesson where time_begin = ?";

    private RowMapper<Lesson> lessonRowMapper = ((resultSet, i) -> {
        Course course = Course.builder()
                .id(resultSet.getLong("course_id"))
                .build();
        Person person = Person.builder()
                .id(resultSet.getLong("person_id"))
                .build();

        return Lesson.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .timeBegin(resultSet.getTimestamp("time_begin").toLocalDateTime())
                .dateUpdate(resultSet.getTimestamp("date_update").toLocalDateTime())
                .course(course)
                .lecturer(person)
                .isCanceled(resultSet.getBoolean("is_canceled"))
                .build();
    });

    public LessonRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Lesson> find(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, lessonRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Lesson model) {
        jdbcTemplate.update(SQL_INSERT, model.getName(), model.getTimeBegin(), model.getDateUpdate(),
                model.getCourse().getId(), model.getLecturer().getId(), model.getIsCanceled());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Lesson model) {
        jdbcTemplate.update(SQL_UPDATE, model.getName(), model.getTimeBegin(), model.getDateUpdate(),
                model.getCourse().getId(), model.getLecturer().getId(), model.getIsCanceled(), model.getId(), model.getDateUpdate());
    }

    @Override
    public List<Lesson> findAll() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, lessonRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Lesson> findAllByCourse(String courseName) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime beginDate = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(currentDate.getYear(),
                currentDate.getMonth().getValue() == 12 ? 1 : currentDate.getMonth().getValue() + 1, 1, 0, 0);
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_COURSE, lessonRowMapper, courseName, beginDate, endDate);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Lesson findByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, lessonRowMapper, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Lesson> findAllByTime(LocalDate time) {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_TIME, lessonRowMapper, time);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}