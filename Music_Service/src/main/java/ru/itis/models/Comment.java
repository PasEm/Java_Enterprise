package ru.itis.models;

import lombok.*;

import java.time.LocalDateTime;

@ToString(exclude = {"user", "track", "event"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Comment {
    private String title;
    private String description;
    private LocalDateTime date;
    private Integer type;
    private Event event;
    private User user;
    private Track track;
    private Long id;
}