package ru.itis.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.configuration.AppConfig;
import ru.itis.localization.Localizations;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;

@WebListener
public class GlobalListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(AppConfig.class);
//
//        Map<String, String> localeEn = Localizations.loadLocalization("en");
//        Map<String, String> localeRu = Localizations.loadLocalization("ru");
//
//        ServletContext servletContext = sce.getServletContext();
//
//        servletContext.setAttribute("localeEn", localeEn);
//        servletContext.setAttribute("localeRu", localeRu);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
