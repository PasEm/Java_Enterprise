package ru.itis.models;

import lombok.*;

@ToString(exclude = {"subscriber", "user"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Subscription {
    private String name;
    private User subscriber;
    private User user;
    private Long id;
}
