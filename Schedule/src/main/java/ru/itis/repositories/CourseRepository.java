package ru.itis.repositories;

import ru.itis.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course> {
    Optional<Course> findByName (String name);

    List<Course> findAllBySearch(String courseName);
}