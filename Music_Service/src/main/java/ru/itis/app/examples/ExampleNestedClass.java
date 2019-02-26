package ru.itis.app.examples;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ExampleNestedClass {
    private ExampleClass exampleClass;
    private int id;
    private String name;
}
