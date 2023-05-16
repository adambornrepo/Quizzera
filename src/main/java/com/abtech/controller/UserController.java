package com.abtech.controller;

import com.abtech.dto.*;
import com.abtech.service.QuizUserService;
import com.abtech.service.UserInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final QuizUserService quizUserService;
    private final UserInfoService userInfoService;

    @GetMapping("/{uname}")
    public ResponseEntity<?> getQuizUserByUsername(@PathVariable("uname") String username) {
        QuizUserDTO quizUserDTO = quizUserService.getQuizUserByUsername(username);
        return ResponseEntity.ok(quizUserDTO);
    }

    @GetMapping("/inf")
    public ResponseEntity<?> getUserInfoByUsername(@RequestParam("uname") String username) {
        UserInfoDTO userInfoDTO = userInfoService.getUserInfoByUsername(username);
        return ResponseEntity.ok(userInfoDTO);
    }


    @PutMapping("/up")
    public ResponseEntity<?> updateQuizUser(@RequestParam("uname") String username, @Valid @RequestBody QuizUserDTO quizUserDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }
        QuizUserDTO updatedQuizUser = quizUserService.updateQuizUser(username, quizUserDTO);

        return ResponseEntity.ok(updatedQuizUser);
    }

    @PutMapping("/inf/up")
    public ResponseEntity<?> updateUserInfo(@RequestParam("uname") String username, @Valid @RequestBody UserInfoDTO userInfoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }
        userInfoService.updateUserInfo(username, userInfoDTO);

        return ResponseEntity.ok("User info successfully updated");
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody RegistrationDTO registrationDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errorMessages);
        }

        QuizUserDTO quizUserDTO = userInfoService.createUser(registrationDTO);

        return new ResponseEntity<>("New user successfully created", HttpStatus.CREATED);
    }

    @DeleteMapping("/dlt")
    public ResponseEntity<?> deleteUser(@RequestParam("uname") String username) {
        userInfoService.deleteUser(username);

        return ResponseEntity.ok("User successfully deleted");
    }

    @PostMapping("/login")
    public ResponseEntity<?> createUser(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errorMessages);
        }

        LoginResponse loginResponse = userInfoService.authenticateUser(loginDTO);

        return ResponseEntity.ok(loginResponse);
    }
}
