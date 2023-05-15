package com.abtech.dto;

import com.abtech.domain.QuizUser;
import com.abtech.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizUserDTO {

    private Long id;

    @Size(min = 2, max = 50, message = "Firstname must be between {min} and {max} characters")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Size(min = 2, max = 50, message = "Lastname must be between {min} and {max} characters")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotNull(message = "Age cannot be null")
    @Positive(message = "Age must be positive")
    private Integer age;

    @NotNull(message = "Birthdate cannot be null")
    @Past(message = "Birthdate must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @NotNull(message = "isActive cannot be null")
    private Boolean isActive;

    public QuizUserDTO(QuizUser quizUser) {
        this.id = quizUser.getId();
        this.firstName = quizUser.getFirstName();
        this.lastName = quizUser.getLastName();
        this.age = quizUser.getAge();
        this.birthdate = quizUser.getBirthdate();
        this.gender = quizUser.getGender();
        this.isActive = quizUser.getIsActive();
    }
}
