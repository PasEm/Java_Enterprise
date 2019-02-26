package ru.itis.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.configuration.JavaConfig;
import ru.itis.localization.Localizations;
import ru.itis.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;

@WebListener
public class GlobalListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(JavaConfig.class);

        UserService userService = context.getBean(UserService.class);
        LoginService loginService = context.getBean(LoginService.class);
        TrackCollectionService trackCollectionService = context.getBean(TrackCollectionService.class);
        EventService eventService = context.getBean(EventService.class);

        Map<String, String> localeEn = Localizations.loadLocalization("en");
        Map<String, String> localeRu = Localizations.loadLocalization("ru");

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("localeEn", localeEn);
        servletContext.setAttribute("localeRu", localeRu);

        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("eventService", eventService);
        servletContext.setAttribute("trackCollectionService", trackCollectionService);
        servletContext.setAttribute("loginService", loginService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
