package com.abtech.domain;

import com.abtech.domain.abstracts.Question;
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
@Table(name = "tbl_open_end")
@PrimaryKeyJoinColumn(name = "open_id", referencedColumnName = "question_id")
public class OpenEnd extends Question {

    @Column(length = 255)
    private String answerText;

}
