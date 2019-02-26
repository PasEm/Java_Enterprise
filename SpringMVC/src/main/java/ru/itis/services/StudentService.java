package ru.itis.services;

import ru.itis.models.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);

    List<Student> getAllStudents();
    List<Student> getAllStudentsByCity(Long cityName);
}
