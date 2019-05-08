package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {
    private Long id;
    private String name;
    private LocalDateTime timeBegin;
    private LocalDateTime dateUpdate;
    private Course course;
    private Person lecturer;
    private Boolean isCanceled;
}