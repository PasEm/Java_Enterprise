package ru.itis.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Event {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private LocalDateTime date;
    private String saleSite;
    private List<User> participants;
}