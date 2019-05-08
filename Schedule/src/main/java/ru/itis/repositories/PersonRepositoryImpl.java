package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Course;
import ru.itis.models.Person;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "insert into person(first_name, surname, additional_name, is_student, course_id) values (?, ?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_UPDATE = "update person set first_name = ? and surname = ? " +
            " and additional_name = ? and is_student = ? and course_id = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE = "delete from person where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from person where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_NAME = "select * from person where first_name = ? and surname = ? and additional_name = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from person";

    //language=SQL
    private static final String SQL_FIND_ALL_STUDENTS = "select * from person where is_student = true";

    //language=SQL
    private static final String SQL_FIND_ALL_LECTURERS = "select * from person where is_student = false";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_COURSE = "select * from person where " +
            "course_id = (select id from course where course.name = ? limit 1) and is_student = true" +
            " order by id asc";

    private RowMapper<Person> personRowMapper = ((resultSet, i) ->
    {
        Course course = Course.builder()
                .id(resultSet.getLong("course_id"))
                .build();

        return Person.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .surname(resultSet.getString("surname"))
                .additionalName(resultSet.getString("additional_name"))
                .isStudent(resultSet.getBoolean("is_student"))
                .course(course)
                .build();
    });

    public PersonRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Person> findAllStudents() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_STUDENTS, personRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Person> findAllLecturers() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_LECTURERS, personRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Person> findAllByCourse(String courseName) {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_COURSE, personRowMapper, courseName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Optional<Person> findByName(String firstName, String surname, String additionalName) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, personRowMapper, firstName, surname, additionalName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Person> find(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, personRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Person model) {
        jdbcTemplate.update(SQL_INSERT, model.getFirstName(), model.getSurname(), model.getAdditionalName(),
                model.getIsStudent(), model.getCourse().getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Person model) {
        jdbcTemplate.update(SQL_UPDATE, model.getFirstName(), model.getSurname(), model.getAdditionalName(),
                model.getIsStudent(), model.getCourse().getId(), model.getId());
    }

    @Override
    public List<Person> findAll() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, personRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}