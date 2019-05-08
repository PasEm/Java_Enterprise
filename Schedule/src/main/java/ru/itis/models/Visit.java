package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Visit {
    private Long id;
    private LocalDateTime dateVisit;
    private Boolean presence;
    private Lesson lesson;
    private Person student;
}