package com.abtech.dto;

import com.abtech.domain.Choice;
import com.abtech.enums.AnswerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceDTO {

    private Long id;

    @NotBlank(message = "Choice text cannot be blank")
    private String choiceText;

    @NotNull(message = "Answer type cannot be null")
    private AnswerType answerType;

    private Boolean inUse;

    public ChoiceDTO(Choice choice) {
        this.id = choice.getId();
        this.choiceText = choice.getChoiceText();
        this.answerType = choice.getAnswerType();
        this.inUse = choice.getInUse();
    }
}
