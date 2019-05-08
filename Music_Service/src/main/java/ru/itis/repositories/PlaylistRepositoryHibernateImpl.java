package ru.itis.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.itis.models.Playlist;
import ru.itis.models.Track;
import ru.itis.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
public class PlaylistRepositoryHibernateImpl implements PlaylistRepository{
    @Autowired
    private SessionFactory sessionFactory;

    private EntityManager manager;

    @Autowired
    public PlaylistRepositoryHibernateImpl(@Qualifier(value = "entityManagerFactory")EntityManagerFactory factory) {
        this.manager = factory.createEntityManager();
    }

    @Override
    public void addTrack(Playlist playlist, Track track) {
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery(SQL_ADD_TRACK);
        query.setParameter(1, track.getId());
        query.setParameter(2, playlist.getId());
        manager.getTransaction().commit();
    }

    @Override
    public void deleteTrack(Playlist playlist, Track track) {
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery(SQL_DELETE_TRACK);
        query.setParameter(1, playlist.getId());
        query.setParameter(2, track.getId());
        manager.getTransaction().commit();
    }

    @Override
    public void deleteByName(User user, String name) {
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery(SQL_DELETE_BY_USER);
        query.setParameter(1, user.getId());
        query.setParameter(2, name);
        manager.getTransaction().commit();
    }

    @Override
    public List<Playlist> findAllBySearch(String title, User user) {
        List<Playlist> playlists = sessionFactory
                .openSession()
                .createNativeQuery(SQL_FIND_ALL_BY_SEARCH, Playlist.class)
                .setParameter(1, title)
                .setParameter(2, user.getId())
                .getResultList();
        return playlists;
    }

    @Override
    public List<Playlist> findAllByUser(User user) {
        List<Playlist> playlists = sessionFactory
                .openSession()
                .createNativeQuery(SQL_FIND_ALL_BY_USER, Playlist.class)
                .setParameter(1, user.getId())
                .getResultList();
        return playlists;
    }

    @Override
    public Optional<Playlist> findByUserAndPlaylistName(User user, String playlistName) {
        Playlist playlist = sessionFactory
                .openSession()
                .createNativeQuery(SQL_FIND_BY_USER_AND_NAME, Playlist.class)
                .setParameter(1, user.getId())
                .setParameter(2, playlistName)
                .getSingleResult();
        return Optional.ofNullable(playlist);
    }

    @Override
    public Optional<Playlist> find(Long id) {
        Playlist playlist = manager.find(Playlist.class, id);
        return Optional.ofNullable(playlist);
    }

    @Override
    public void save(Playlist model) {
        manager.getTransaction().begin();
        manager.persist(model);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery(SQL_DELETE);
        query.setParameter(1, id);
        manager.getTransaction().commit();
    }

    @Override
    public void update(Playlist model) {
        manager.getTransaction().begin();
        manager.merge(model);
        manager.getTransaction().commit();
    }

    @Override
    public List<Playlist> findAll() {
        return sessionFactory
                .openSession()
                .createQuery("FROM Playlist", Playlist.class).getResultList();
    }

    //language=SQL
    private static final String SQL_DELETE = "delete from playlist where id = ?1";

    //language=SQL
    private static final String SQL_DELETE_BY_USER = "delete from playlist where user_entity_id = ?1 and name = ?2";

    //language=SQL
    private static final String SQL_FIND_BY_USER_AND_NAME = "select * from playlist" +
            " where user_entity_id = ?1 and name = ?2";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_USER = "SELECT * FROM playlist WHERE user_entity_id = ?1";

    //language=SQL
    private static final String SQL_ADD_TRACK = "INSERT INTO playlist_track(track_id, playlist_id)" +
            " values (?1, ?2)";

    //language=SQL
    private static final String SQL_DELETE_TRACK ="DELETE from playlist_track WHERE playlist_id = ?1 and track_id = ?2";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_SEARCH = "select * from playlist where lower(name) like lower('%' || ?1 || '%') and playlist.user_entity_id = ?2";
}
