package com.abtech.dto;

import com.abtech.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private Long id;

    private String username;

    private String email;

    private String role;

    private String firstName;

    private String lastName;

    public LoginResponse(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.username = userInfo.getUsername();
        this.email = userInfo.getEmail();
        this.role = userInfo.getUserRole().name();
        this.firstName = userInfo.getQuizUser().getFirstName();
        this.lastName = userInfo.getQuizUser().getLastName();
    }
}
