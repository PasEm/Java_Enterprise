package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Course;
import ru.itis.models.Lesson;
import ru.itis.models.Person;
import ru.itis.models.Visit;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class VisitRepositoryImpl implements VisitRepository {
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_INSERT = "insert into visit(date_visit, lesson_id, student_id, presence) values (?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_UPDATE = "update visit set date_visit = ? and lesson_id = ? " +
            " and student_id = ? and presence = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE = "delete from visit where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from visit where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_LESSON_DATE_PERSON = "select * from visit where lesson_id = ? and date_visit = ? and student_id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_LESSON = "select * from visit where lesson_id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_STUDENT = "select * from visit where student_id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_DATE = "select * from visit where date_visit = ?";

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from visit";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_COURSE = "select * from visit" +
            " where student_id in " +
            "(select id from person where is_student = true and " +
            "course_id = (select id from course where course.name = ? limit 1))" +
            " order by student_id, date_visit asc";

    private RowMapper<Visit> visitRowMapper = ((resultSet, i) ->
    {
        Lesson lesson = Lesson.builder()
            .id(resultSet.getLong("lesson_id"))
            .build();
        Person student = Person.builder()
                .id(resultSet.getLong("student_id"))
                .build();

        return Visit.builder()
                .id(resultSet.getLong("id"))
                .lesson(lesson)
                .presence(resultSet.getBoolean("presence"))
                .student(student)
                .dateVisit(resultSet.getTimestamp("date_visit").toLocalDateTime())
                .build();
    });

    public VisitRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Visit> find(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, visitRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Visit model) {
        jdbcTemplate.update(SQL_INSERT, model.getDateVisit(), model.getLesson().getId(),
                model.getStudent().getId(), model.getPresence());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Visit model) {
        jdbcTemplate.update(SQL_UPDATE, model.getDateVisit(), model.getLesson().getId(),
                model.getStudent().getId(), model.getPresence(), model.getId());
    }

    @Override
    public List<Visit> findAll() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, visitRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Visit> findAllByStudent(Person student) {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_STUDENT, visitRowMapper, student.getId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Visit> findAllByLesson(Lesson lesson) {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_LESSON, visitRowMapper, lesson.getId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Visit> findAllByDate(LocalDateTime date) {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_DATE, visitRowMapper, date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Visit> findByLessonAndDateAndPerson(Lesson lesson, Person person, LocalDateTime dateTime) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_LESSON_DATE_PERSON, visitRowMapper, lesson.getId(), dateTime, person.getId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Visit> findAllByCourse(String courseName) {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_COURSE, visitRowMapper, courseName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}