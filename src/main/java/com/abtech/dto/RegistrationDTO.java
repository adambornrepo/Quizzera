package com.abtech.dto;

import com.abtech.domain.QuizUser;
import com.abtech.domain.UserInfo;
import com.abtech.enums.Gender;
import com.abtech.enums.Role;
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
public class RegistrationDTO {
    @Size(min = 2, max = 50, message = "Firstname must be between {min} and {max} characters")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Size(min = 2, max = 50, message = "Lastname must be between {min} and {max} characters")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 50, message = "Username must be between {min} and {max} characters")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Birthdate cannot be null")
    @Past(message = "Birthdate must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    public UserInfo buildUserInfo() {

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setEmail(email);
        userInfo.setUserRole(Role.ROLE_QUIZZER); // Default role : quizzer

        return userInfo;
    }

    public QuizUser buildQuizUser() {

        QuizUser quizUser = new QuizUser();

        quizUser.setFirstName(firstName);
        quizUser.setLastName(lastName);
        quizUser.setBirthdate(birthdate);
        quizUser.setGender(gender);

        return quizUser;
    }
}
