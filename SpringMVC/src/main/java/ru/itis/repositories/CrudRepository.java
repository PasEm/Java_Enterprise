package ru.itis.repositories;

import java.util.List;

public interface CrudRepository<M, I> {
    List<M> findAll();
    void save(M model);
    M find(I id);
    void delete(I id);
    void update(M model);
}
