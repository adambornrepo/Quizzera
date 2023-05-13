package com.abtech.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private final LocalDate createDate = LocalDate.now();

    @Column(nullable = false)
    private LocalTime quizTime;

    @Column(nullable = false)
    private Boolean inUse = true;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OpenEnd> openEndList;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MultipleChoice> multipleChoiceList;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FillBlank> fillBlankList;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TrueFalse> trueFalseList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Score> scoreList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quizUser_id", referencedColumnName = "id")
    private QuizUser quizUser;


}
