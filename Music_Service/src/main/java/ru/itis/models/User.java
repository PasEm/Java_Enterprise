package ru.itis.models;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    private Long id;
    private String email;
    private String surname;
    private String firstName;
    private String country;
    private LocalDate birthdate;
    private String hashPassword;
    private String login;
    private String cookie;

    private List<Playlist> playlists;
    private List<Playlist> albums;
    private List<Subscription> subscriptions;
}