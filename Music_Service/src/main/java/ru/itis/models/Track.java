package ru.itis.models;

import lombok.*;

import java.time.LocalDate;

@ToString(exclude = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Track {
    private Long id;
    private String name;
    private LocalDate releaseDate;
    private String genre;
    private String url;
    private Long duration;
    private Boolean liked;
    private Integer viewCount;
    private User author;
}
