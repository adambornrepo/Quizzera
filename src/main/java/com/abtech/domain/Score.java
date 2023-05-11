package com.abtech.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(nullable = false)
    private Boolean isCompleted = false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quizUser_id")
    private QuizUser quizUser;
}
