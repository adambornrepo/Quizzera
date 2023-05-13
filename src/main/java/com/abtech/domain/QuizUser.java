package com.abtech.domain;

import com.abtech.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class QuizUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String firstName;


    @Column(length = 50, nullable = false)
    private String lastName;

    @Transient
    private Integer age;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate createDate = LocalDate.now();

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToOne
    @JoinColumn(name = "info_id")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "quizUser",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Score> scoreList;

    @OneToMany(mappedBy = "quizUser",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Quiz> quiz;


}
