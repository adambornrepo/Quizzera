package com.abtech.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    private final LocalDate createDate = LocalDate.now();

    private Integer totalScore;

    @Transient
    private List<?> answerList = new ArrayList<>();

    private String answer = flatAnswer(answerList);

    private boolean isEvaluated;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)//Look after
    @JoinColumn(name = "quizUser_id")
    private QuizUser quizUser;

    public String flatAnswer(List<?> answerList) {
        StringBuilder sb = new StringBuilder();
        answerList.forEach(a -> sb.append(a).append("¨"));
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public boolean getEvaluationInfo(Quiz quiz) {
        List<OpenEnd> openEndList = quiz.getOpenEndList();

        if (openEndList == null || openEndList.isEmpty()) {
            return true;
        }
        return false;
    }

}
