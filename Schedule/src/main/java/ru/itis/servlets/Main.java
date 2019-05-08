package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.Course;
import ru.itis.models.Lesson;
import ru.itis.models.Visit;
import ru.itis.services.ScheduleService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/main")
public class Main extends HttpServlet {
    private ObjectMapper objectMapper;
    private ScheduleService scheduleService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.objectMapper = new ObjectMapper();
        this.scheduleService = (ScheduleService) context.getAttribute("scheduleService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTitle = request.getParameter("courses");
        String json = null;

        if (searchTitle != null) {
            List<Course> result = scheduleService.searchCourse(searchTitle);
            json = searchTitle.equals("") ? objectMapper.writeValueAsString(null) : objectMapper.writeValueAsString(result);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
        if (json == null) {
            request.getRequestDispatcher("/ftl/main.ftl").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("currentCourse");
        String lessonName = request.getParameter("lessonName");
        String json = "";

        if (courseName != null) {
            List<Visit> visits = scheduleService.visitByCourse(courseName);
            List<Lesson> lessonsList = scheduleService.getLessonsByCourse(courseName);
            List<Object> result = new ArrayList<>();
            result.add(visits);
            result.add(lessonsList);
            json = objectMapper.writeValueAsString(result);
        }

        if (lessonName != null) {
            LocalDateTime newTime = Timestamp.valueOf(request.getParameter("lessonNewTime")).toLocalDateTime();
            Lesson lesson = scheduleService.getLesson(lessonName).orElse(null);
            scheduleService.updateLesson(newTime, lesson.getTimeBegin(), lesson);
        }

        if (json.equals("")) {
            request.getRequestDispatcher("/ftl/playlists.ftl").forward(request, response);
        } else {
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }
}