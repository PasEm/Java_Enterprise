package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Course;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CourseRepositoryImpl implements CourseRepository{
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "insert into course(count_students, name) values (?, ?)";

    //language=SQL
    private static final String SQL_UPDATE = "update course set count_students = ? and name = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE = "delete from course where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from course where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_NAME = "select * from course where name = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from course";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_SEARCH = "select * from course where name like ?";

    private RowMapper<Course> courseRowMapper = ((resultSet, i) ->
        Course.builder()
        .id(resultSet.getLong("id"))
        .name(resultSet.getString("name"))
        .countStudents(resultSet.getInt("count_students"))
        .build()
    );

    public CourseRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Course> find(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, courseRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Course model) {
        jdbcTemplate.update(SQL_INSERT, model.getCountStudents(), model.getName());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Course model) {
        jdbcTemplate.update(SQL_UPDATE, model.getCountStudents(), model.getName());
    }

    @Override
    public List<Course> findAll() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, courseRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Optional<Course> findByName(String name) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, courseRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> findAllBySearch(String courseName) {
        StringBuilder searchTitle = new StringBuilder().append('%').append(courseName).append('%');
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_SEARCH, courseRowMapper, searchTitle);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}