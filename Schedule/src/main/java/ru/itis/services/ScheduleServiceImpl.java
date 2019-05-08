package ru.itis.services;

import ru.itis.models.Course;
import ru.itis.models.Lesson;
import ru.itis.models.Person;
import ru.itis.models.Visit;
import ru.itis.repositories.CourseRepository;
import ru.itis.repositories.LessonRepository;
import ru.itis.repositories.PersonRepository;
import ru.itis.repositories.VisitRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ScheduleServiceImpl implements ScheduleService {
    private VisitRepository visitRepository;
    private LessonRepository lessonRepository;
    private CourseRepository courseRepository;
    private PersonRepository personRepository;

    public ScheduleServiceImpl(PersonRepository personRepository, VisitRepository visitRepository,
                               LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.personRepository = personRepository;
        this.visitRepository = visitRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void updateLesson(LocalDateTime newTime, LocalDateTime dateFrom, Lesson lesson) {
        lesson.setTimeBegin(newTime);
        lesson.setDateUpdate(dateFrom);
        lessonRepository.update(lesson);
    }

    @Override
    public void updatePresence(Lesson lesson, Person person, LocalDateTime presenceTime, Boolean presence) {
        List<Visit> visits = visitRepository.findByLessonAndDateAndPerson(lesson, person, presenceTime);
        for (Visit visit: visits) {
            visit.setPresence(presence);
            visitRepository.update(visit);
        }
    }

    @Override
    public Optional<Course> getCourse(String courseName) {
        return courseRepository.findByName(courseName);
    }

    @Override
    public Optional<Lesson> getLesson(String lessonName) {
        return Optional.of(lessonRepository.findByName(lessonName));
    }

    @Override
    public List<Visit> visitByLesson(Lesson lesson) {
        return visitRepository.findAllByLesson(lesson);
    }

    @Override
    public List<Visit> visitByCourse(String courseName) {
        List<Visit> visits = visitRepository.findAllByCourse(courseName);

        for (Visit visit: visits) {
            visit.setStudent(personRepository.find(visit.getStudent().getId()).orElse(null));
            visit.setLesson(lessonRepository.find(visit.getLesson().getId()).orElse(null));
        }

        return visits;
    }

    @Override
    public List<Course> searchCourse(String searchTitle) {
        return courseRepository.findAllBySearch(searchTitle);
    }

    @Override
    public List<Person> getStudentsByCourse(String courseName) {
        return personRepository.findAllByCourse(courseName);
    }

    @Override
    public List<Lesson> getLessonsByCourse(String courseName) {
        return lessonRepository.findAllByCourse(courseName);
    }
}
