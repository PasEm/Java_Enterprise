package ru.itis.repositories;

import ru.itis.models.Comment;
import ru.itis.models.Event;
import ru.itis.models.Track;
import ru.itis.models.User;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment> {
    List<Comment> findAllByTrack (Track track);
    List<Comment> findAllByEvent(Long eventId);
    List<Comment> findAllByUser (User user);

    void saveByEvent(Comment model);
}