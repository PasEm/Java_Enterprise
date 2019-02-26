package ru.itis.app.examples;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ExampleClass {
    private int id;
    private Long number;
    private boolean gender;
    private double cost;
}