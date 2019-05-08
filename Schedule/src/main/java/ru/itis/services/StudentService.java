package ru.itis.services;

import ru.itis.models.Course;
import ru.itis.models.Person;

import java.util.List;

public interface StudentService {
    void addStudent(Person person);
    void deleteStudent(Person person);
}
