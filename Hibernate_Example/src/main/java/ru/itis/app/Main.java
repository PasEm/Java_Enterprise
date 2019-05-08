package ru.itis.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.itis.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = User.builder()
                .firstName("John")
                .lastName("Knows nothing")
                .build();

        Driver driver = Driver.builder()
                .firstName("Tirion")
                .lastName("Arbalester")
                .build();

        Car car = Car.builder()
                .model("Lion")
                .number("777")
                .driver(driver)
                .build();

        driver.setCar(car);

        Order order = Order.builder()
                .user(user)
                .driver(driver)
                .beginTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .build();

        Set<Order> orders = new HashSet<>();
        orders.add(order);

        user.setOrders(orders);
        driver.setOrders(orders);

        session.save(order);
        session.save(user);
        session.save(driver);

        transaction.commit();
        session.close();
    }
}
