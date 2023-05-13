package com.abtech.dto;

import com.abtech.enums.QuestionType;
import jakarta.persistence.MappedSuperclass;
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
@MappedSuperclass
public class QuestionDTO {

    private Long id;

    @NotBlank(message = "Question text cannot be blank")
    private String questionText;

    @NotNull(message = "Score cannot be null")
    @Positive(message = "Score must be positive")
    private Integer score;

    private Boolean inUse;

    @NotNull(message = "Question type cannot be null")
    private QuestionType questionType;
}
