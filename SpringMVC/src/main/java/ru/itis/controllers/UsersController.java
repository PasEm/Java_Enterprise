package ru.itis.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.models.Student;
import ru.itis.services.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersController implements Controller {
    private StudentService studentService;

    public UsersController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        if (httpServletRequest.getMethod().equals("GET")) {
            List<Student> students = studentService.getAllStudents();
            ModelAndView modelAndView = new ModelAndView();

            modelAndView.addObject("students", students);
            modelAndView.setViewName("all_students_page");
            return modelAndView;
        }
        return null;
    }
}
