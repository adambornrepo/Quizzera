package com.abtech.dto;

import com.abtech.domain.MultipleChoice;
import com.abtech.enums.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceDTO extends QuestionDTO {

    private String chcA;

    private String chcB;

    private String chcC;

    private String chcD;

    private String chcE;

    @NotNull(message = "Question answer cannot be blank")
    private Character answer;

    public MultipleChoiceDTO(MultipleChoice multipleChoice) {
        super.setId(multipleChoice.getId());
        super.setQuestionType(multipleChoice.getQuestionType());
        super.setScore(multipleChoice.getScore());
        super.setQuestionText(multipleChoice.getQuestionText());
        super.setInUse(multipleChoice.getInUse());
        this.chcA = multipleChoice.getChcA();
        this.chcB = multipleChoice.getChcB();
        this.chcC = multipleChoice.getChcC();
        this.chcD = multipleChoice.getChcD();
        this.chcE = multipleChoice.getChcE();
        this.answer = multipleChoice.getAnswer();
    }
}
