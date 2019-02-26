package ru.itis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.*;
import ru.itis.services.*;

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
        dataSource.setUsername(userName);
        dataSource.setUrl(url);
        return dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(dataSource());
    }

    @Bean
    public CommentRepository commentRepository() {
        return new CommentRepositoryImpl(dataSource());
    }

    @Bean
    public EventRepository eventRepository() {
        return new EventRepositoryImpl(dataSource());
    }

    @Bean
    public PlaylistRepository playlistRepository() {
        return new PlaylistRepositoryImpl(dataSource());
    }

    @Bean
    public SubscriptionRepository subscriptionRepository() {
        return new SubscriptionRepositoryImpl(dataSource());
    }

    @Bean
    public TrackRepository trackRepository() {
        return  new TrackRepositoryImpl(dataSource());
    }

    @Bean
    public EventService eventService() {
        return new EventServiceImpl(userRepository(), eventRepository());
    }

    @Bean
    public LoginService loginService() {
        return new LoginServiceImpl(userRepository(), passwordEncoder());
    }

    @Bean
    public TrackCollectionService trackCollectionService() {
        return new TrackCollectionServiceImpl(trackRepository(), playlistRepository(), userRepository());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository(), passwordEncoder());
    }
}