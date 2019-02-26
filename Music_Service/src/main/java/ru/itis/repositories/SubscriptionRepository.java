package ru.itis.repositories;

import ru.itis.models.Subscription;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription> {
    List<Subscription> findAllByMaxCount();
    List<Subscription> findAllByMinCount();
}