package ru.itis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repositories.*;
import ru.itis.services.ScheduleService;
import ru.itis.services.ScheduleServiceImpl;
import ru.itis.services.StudentService;
import ru.itis.services.StudentServiceImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class JavaConfig {

    @Value("${driver.username}")
    private String userName;

    @Value("${driver.password}")
    private String password;

    @Value("${driver.url}")
    private String url;

    @Value("${driver.className}")
    private String className;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        return dataSource;
    }

    @Bean
    public CourseRepository courseRepository() {
        return new CourseRepositoryImpl(dataSource());
    }

    @Bean
    public LessonRepository lessonRepository() {
        return new LessonRepositoryImpl(dataSource());
    }

    @Bean
    public PersonRepository personRepository() {
        return new PersonRepositoryImpl(dataSource());
    }

    @Bean
    public VisitRepository visitRepository() {
        return new VisitRepositoryImpl(dataSource());
    }

    @Bean
    public StudentService studentService() {
        return new StudentServiceImpl(personRepository());
    }

    @Bean
    public ScheduleService scheduleService() {
        return new ScheduleServiceImpl(personRepository(), visitRepository(), lessonRepository(), courseRepository());
    }

}