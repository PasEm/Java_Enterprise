package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {
    private String email;
    private String password;
    private String surname;
    private String firstName;
    private String login;
    private LocalDate birthdate;
    private String country;
}