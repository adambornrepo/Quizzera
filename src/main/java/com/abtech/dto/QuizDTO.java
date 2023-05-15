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

    private String name;

    private String description;

    private LocalDate createDate;

    private LocalTime quizTime;

    private Boolean inUse;

    private List<MultipleChoice> multipleChoiceList;

    private List<TrueFalse> trueFalseList;

    private List<FillBlank> fillBlankList;

    private List<OpenEnd> openEndList;

    private String topicName;

    private List<Score> scoreList;

    private Long quizUserId;

    public QuizDTO(Quiz quiz) {

        this.id = quiz.getId();
        this.name = quiz.getName();
        this.description = quiz.getDescription();
        this.createDate = quiz.getCreateDate();
        this.quizTime = quiz.getQuizTime();
        this.inUse = quiz.getInUse();
        this.openEndList = quiz.getOpenEndList();
        this.multipleChoiceList = quiz.getMultipleChoiceList();
        this.fillBlankList = quiz.getFillBlankList();
        this.trueFalseList = quiz.getTrueFalseList();
        this.topicName = quiz.getTopic().getName();
        //this.scoreList = quiz.getScoreList();
        this.quizUserId = quiz.getQuizUser().getId();

    }
}
