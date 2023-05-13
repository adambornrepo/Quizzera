package com.abtech.dto;

import com.abtech.domain.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {

    private Long id;

    @NotBlank(message = "Quiz name cannot be blank")
    private String name;

    private String description;

    @PastOrPresent(message = "Create date must be in the past or present")
    private LocalDate createDate;

    @NotNull(message = "Quiz time cannot be null")
    private LocalTime quizTime;

    private Boolean inUse;

    private List<OpenEnd> openEndList;

    private List<MultipleChoice> multipleChoiceList;

    private List<FillBlank> fillBlankList;

    private List<TrueFalse> trueFalseList;

    @NotNull(message = "Topic cannot be null")
    private Topic topic;

    private List<Score> scoreList;

    @NotNull(message = "Quiz user cannot be null")
    private QuizUser quizUser;

    public QuizDTO(Quiz quiz) {

        this.id = quiz.getId();
        this.description = quiz.getDescription();
        this.createDate = quiz.getCreateDate();
        this.quizTime = quiz.getQuizTime();
        this.inUse = quiz.getInUse();
        this.openEndList = quiz.getOpenEndList();
        this.multipleChoiceList = quiz.getMultipleChoiceList();
        this.fillBlankList = quiz.getFillBlankList();
        this.trueFalseList = quiz.getTrueFalseList();
        this.topic = quiz.getTopic();
        //this.scoreList = quiz.getScoreList();
        //this.quizUser = quiz.getQuizUser();

    }
}
