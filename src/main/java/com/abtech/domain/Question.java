package com.abtech.domain;

import com.abtech.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String questionText;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Boolean inUse = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType questionType;

}
