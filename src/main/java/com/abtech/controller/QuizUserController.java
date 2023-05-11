package com.abtech.controller;

import com.abtech.dto.RegistrationDTO;
import com.abtech.service.QuizUserService;
import com.abtech.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class QuizUserController {

    private QuizUserService quizUserService;
    private UserInfoService userInfoService;

    @Autowired
    public QuizUserController(QuizUserService quizUserService, UserInfoService userInfoService) {
        this.quizUserService = quizUserService;
        this.userInfoService = userInfoService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody RegistrationDTO registrationDTO, BindingResult result) {


        return new ResponseEntity<>("New user successfully created", HttpStatus.CREATED);
    }
}
