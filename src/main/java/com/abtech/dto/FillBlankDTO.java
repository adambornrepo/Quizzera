package com.abtech.dto;

import com.abtech.domain.FillBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FillBlankDTO extends QuestionDTO {

    @NotBlank(message = "Answer text cannot be blank")
    private String answerText;

    public FillBlankDTO(FillBlank fillBlank) {
        super.setId(fillBlank.getId());
        super.setQuestionType(fillBlank.getQuestionType());
        super.setScore(fillBlank.getScore());
        super.setQuestionText(fillBlank.getQuestionText());
        super.setInUse(fillBlank.getInUse());
        this.answerText = fillBlank.getAnswerText();
    }
}