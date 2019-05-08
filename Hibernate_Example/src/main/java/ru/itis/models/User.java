package ru.itis.models;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"orders"})
@Builder
public class User {
    private Long id;

    private String firstName;
    private String lastName;

    private Set<Order> orders;
}
