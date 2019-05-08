package ru.itis.repositories;

import ru.itis.models.Course;
import ru.itis.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person> {
    List<Person> findAllStudents();
    List<Person> findAllLecturers();
    List<Person> findAllByCourse(String courseName);

    Optional<Person> findByName(String firstName, String surname, String additionalName);
}
