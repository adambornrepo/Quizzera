package com.abtech.dto;

import com.abtech.domain.Quiz;
import com.abtech.domain.QuizUser;
import com.abtech.domain.Score;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {

    private Long id;

    private LocalDate createDate;

    private Integer totalScore;

    private Long quizId;

    private List<?> answerList;

    private String answer;

    private Long quizUserId;

    private boolean isEvaluated;

    public ScoreDTO(Score score) {
        this.id = score.getId();
        this.createDate = score.getCreateDate();
        this.totalScore = score.getTotalScore();
        this.quizId = score.getQuiz().getId();
        this.answerList = buildAnswerList(score.getAnswer());
        this.quizUserId = score.getQuizUser().getId();
        this.isEvaluated = score.isEvaluated();
    }

    public List<?> buildAnswerList(String answer) {
        return Arrays.asList(answer.split("¨"));
    }

}
