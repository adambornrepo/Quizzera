package com.abtech.dto;

import com.abtech.domain.TrueFalse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrueFalseDTO extends QuestionDTO {

    @NotNull(message = "Answer cannot be null")
    private boolean answer;

    public TrueFalseDTO(TrueFalse trueFalse) {
        super.setId(trueFalse.getId());
        super.setQuestionType(trueFalse.getQuestionType());
        super.setScore(trueFalse.getScore());
        super.setQuestionText(trueFalse.getQuestionText());
        super.setInUse(trueFalse.getInUse());
        this.answer = trueFalse.isAnswer();
    }
}
