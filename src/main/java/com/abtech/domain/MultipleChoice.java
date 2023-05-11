package com.abtech.domain;

import com.abtech.domain.abstracts.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_multiple_choice")
@PrimaryKeyJoinColumn(name = "multiple_id", referencedColumnName = "question_id")
public class MultipleChoice extends Question {

    @OneToMany(mappedBy = "multipleChoice", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Choice> choice;

}
