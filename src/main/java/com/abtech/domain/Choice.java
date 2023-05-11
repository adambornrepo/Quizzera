package com.abtech.domain;

import com.abtech.enums.AnswerType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_choice")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String choiceText;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerType answerType;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "m_choice_id")
    private MultipleChoice multipleChoice;

    @Column(nullable = false)
    private Boolean inUse = true;
}
