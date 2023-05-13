package com.abtech.dto;

import com.abtech.domain.OpenEnd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpenEndDTO extends QuestionDTO {
    public OpenEndDTO(OpenEnd openEnd) {
        super.setId(openEnd.getId());
        super.setQuestionType(openEnd.getQuestionType());
        super.setScore(openEnd.getScore());
        super.setQuestionText(openEnd.getQuestionText());
        super.setInUse(openEnd.getInUse());
    }
}
