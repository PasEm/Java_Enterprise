package ru.itis.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"user", "driver"})
public class Order {
    private Long id;

    private User user;
    private Driver driver;

    private LocalDateTime beginTime;
    private LocalDateTime endTime;
}
