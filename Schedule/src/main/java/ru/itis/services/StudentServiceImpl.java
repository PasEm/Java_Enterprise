package ru.itis.services;

import ru.itis.models.Person;
import ru.itis.repositories.PersonRepository;

public class StudentServiceImpl implements StudentService {
    private PersonRepository personRepository;

    public StudentServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void addStudent(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deleteStudent(Person person) {
        personRepository.delete(person.getId());
    }
}