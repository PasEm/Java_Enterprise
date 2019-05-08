package ru.itis.services;

import ru.itis.models.Course;
import ru.itis.models.Lesson;
import ru.itis.models.Person;
import ru.itis.models.Visit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    void updateLesson(LocalDateTime newTime, LocalDateTime dateFrom, Lesson lesson);
    void updatePresence(Lesson lesson, Person person, LocalDateTime presenceTime, Boolean presence);

    Optional<Course> getCourse(String courseName);
    Optional<Lesson> getLesson(String lessonName);

    List<Visit> visitByLesson(Lesson lesson);
    List<Visit> visitByCourse(String courseName);

    List<Course> searchCourse(String searchTitle);

    List<Person> getStudentsByCourse(String courseName);

    List<Lesson> getLessonsByCourse(String courseName);
}
