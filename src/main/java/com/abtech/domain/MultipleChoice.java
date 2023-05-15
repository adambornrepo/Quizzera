package com.abtech.domain;

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
@Entity
@Table(name = "tbl_multiple_choice")
public class MultipleChoice extends Question {

    @Column(length = 155)
    private String chcA;

    @Column(length = 155)
    private String chcB;

    @Column(length = 155)
    private String chcC;

    @Column(length = 155)
    private String chcD;

    @Column(length = 155)
    private String chcE;

    @Column(nullable = false)
    private Character answer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


}
