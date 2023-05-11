package com.abtech.dto;

import com.abtech.enums.AnswerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChoiceRecord(
        @NotBlank(message = "Choice text cannot be blank") String choiceText,
        @NotNull(message = "Answer type cannot be null") AnswerType answerType,
        Boolean inUse

) {
}
