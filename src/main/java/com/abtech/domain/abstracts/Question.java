package com.abtech.domain.abstracts;


import com.abtech.domain.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "tbl_question_super")
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(nullable = false, length = 255)
    private String questionText;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Boolean inUse = true;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

}
