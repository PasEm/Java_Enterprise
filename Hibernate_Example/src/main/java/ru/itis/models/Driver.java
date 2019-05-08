package ru.itis.models;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"orders","car"})
@Builder
public class Driver {
    private Long id;

    private String firstName;
    private String lastName;

    private Car car;

    private Set<Order> orders;
}
