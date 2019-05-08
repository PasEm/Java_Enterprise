package ru.itis.repositories;

import ru.itis.models.Course;
import ru.itis.models.Lesson;
import ru.itis.models.Person;
import ru.itis.models.Visit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends CrudRepository<Visit> {
    List<Visit> findAllByStudent(Person student);
    List<Visit> findAllByLesson(Lesson lesson);
    List<Visit> findAllByDate(LocalDateTime date);
    List<Visit> findByLessonAndDateAndPerson(Lesson lesson, Person person, LocalDateTime dateTime);
    List<Visit> findAllByCourse(String courseName);
}
