package ru.itis.models;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Playlist {
    private User user;
    private String name;
    private Long duration;
    private List<Track> tracks;
    private LocalDate releaseDate;
    private Long trackCount;
    private Long id;
}