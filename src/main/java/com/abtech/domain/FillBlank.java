package com.abtech.domain;

import com.abtech.domain.abstracts.Question;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_fill_blank")
@PrimaryKeyJoinColumn(name = "fill_id", referencedColumnName = "question_id")
public class FillBlank extends Question {

    @Column(length = 50)
    private String answerText;

}
