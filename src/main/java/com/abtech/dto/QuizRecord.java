package com.abtech.dto;

import com.abtech.domain.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record QuizRecord(

        @NotBlank(message = "Quiz name cannot be blank")
        String name,
        String description,
        @NotNull(message = "Quiz time cannot be null")
        LocalTime quizTime,
        List<Long> openEndList,
        List<Long> multipleChoiceList,
        List<Long> fillBlankList,
        List<Long> trueFalseList,
        @NotNull(message = "Topic cannot be null")
        Integer topicId,
        @NotNull(message = "Quiz user cannot be null")
        Long quizUserId
) {
}
