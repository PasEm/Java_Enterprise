package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.City;
import ru.itis.models.Student;

import javax.sql.DataSource;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    //language=SQL
    private static final String SQL_INSERT = "insert into student(name, city) values (?, ?)";

    //language=SQL
    private static final String SQL_UPDATE = "update student set name = ? and city = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE = "delete from student where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from student where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from student";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_CITY = "select * from student where city = ?";

    private JdbcTemplate jdbcTemplate;

    public StudentRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Student> studentRowMapper = ((resultSet, i) -> {
        City city = City.builder()
                .name(resultSet.getLong("city"))
                .build();

        return Student.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .city(city)
                .build();
    });

    @Override
    public List<Student> findAllByCity(Long cityName) {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_CITY, studentRowMapper, cityName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Student> findAll() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, studentRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(Student model) {
        jdbcTemplate.update(SQL_INSERT, model.getName(), model.getCity().getName());
    }

    @Override
    public Student find(Long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, studentRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Student model) {
        jdbcTemplate.update(SQL_UPDATE, model.getName(), model.getCity().getName(), model.getId());
    }
}
