package ru.itis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String surname;

    @Column(name = "first_name")
    private String firstName;
    private String country;
    private String login;
    private String avatar;

    @Column(name = "hash_password")
    private String hashPassword;
    private String cookie;

    private LocalDate birthdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    private List<Playlist> playlists;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Track> tracks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Subscription> subscriptions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriber")
    private List<Subscription> subscribers;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "participants")
    private List<Event> events;
}