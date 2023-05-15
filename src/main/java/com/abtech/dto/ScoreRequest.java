package com.abtech.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreRequest {

    @NotNull(message = "Quiz ID cannot be null")
    private Long quizId;

    @NotNull(message = "Quiz User ID cannot be null")
    private Long quizUserId;

    private List<?> answerList;
}
