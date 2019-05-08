package ru.itis.repositories;

import ru.itis.models.Course;
import ru.itis.models.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson> {
    List<Lesson> findAllByCourse (String courseName);
    List<Lesson> findAllByTime(LocalDate time);

    Lesson findByName (String name);
}
