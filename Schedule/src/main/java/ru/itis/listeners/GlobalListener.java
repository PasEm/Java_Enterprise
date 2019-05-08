package ru.itis.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.configuration.JavaConfig;
import ru.itis.services.ScheduleService;
import ru.itis.services.StudentService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class GlobalListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(JavaConfig.class);

        StudentService studentService = context.getBean(StudentService.class);
        ScheduleService scheduleService = context.getBean(ScheduleService.class);

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("studentService", studentService);
        servletContext.setAttribute("scheduleService", scheduleService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
