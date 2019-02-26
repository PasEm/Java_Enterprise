package ru.itis.repositories;

import ru.itis.models.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findAllByCity(Long cityName);
}
