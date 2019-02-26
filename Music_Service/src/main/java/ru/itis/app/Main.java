package ru.itis.app;

import ru.itis.app.examples.ExampleChildClass;
import ru.itis.app.examples.ExampleClass;
import ru.itis.app.examples.ExampleNestedClass;
import ru.itis.context.ApplicationContextImpl;
import ru.itis.models.User;
import ru.itis.repositories.EventRepository;
import ru.itis.repositories.UserRepository;
import ru.itis.services.EventService;
import ru.itis.services.TrackCollectionService;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        File file1 = new File("src\\main\\java\\ru\\itis\\repositories");
        File file2 = new File("src\\main\\java\\ru\\itis\\app");
        File file3 = new File("src\\main\\java\\ru\\itis\\services");
        File file4 = new File("src\\main\\java\\ru\\itis\\models");

        ArrayList<File> list = new ArrayList<>();
        list.add(file1);
        list.add(file2);
        list.add(file3);
        list.add(file4);

        ApplicationContextImpl applicationContext = ApplicationContextImpl.getContext();
        applicationContext.addResources(list);

        Application application = applicationContext.getComponent(Application.class);
        System.out.println(application == null ? null : application.toString());

        UserRepository userRepository = applicationContext.getComponent(UserRepository.class);
        System.out.println(userRepository == null ? null : userRepository.toString());

        User user = applicationContext.getComponent(User.class);
        System.out.println(user == null ? null : user.toString());

        EventRepository eventRepository = applicationContext.getComponent(EventRepository.class);
        System.out.println(eventRepository == null ? null : eventRepository.toString());

        ExampleClass exampleClass = applicationContext.getComponent(ExampleClass.class);
        System.out.println(exampleClass == null ? null : exampleClass.toString());

        ExampleNestedClass nestedClass = applicationContext.getComponent(ExampleNestedClass.class);
        System.out.println(nestedClass == null ? null : nestedClass.toString());

        ExampleChildClass childClass = applicationContext.getComponent(ExampleChildClass.class);
        System.out.println(childClass == null ? null : childClass.toString());

        EventService eventService = applicationContext.getComponent(EventService.class);
        System.out.println(eventService == null ? null : eventService.toString());

        TrackCollectionService trackCollectionService = applicationContext.getComponent(TrackCollectionService.class);
        System.out.println(trackCollectionService == null ? null : trackCollectionService.toString());
    }
}
