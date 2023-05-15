package com.abtech.repository;

import com.abtech.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    boolean existsByQuizIdAndQuizUserId(Long quizId, Long quizUserId);
    List<Score> findAllByQuizUserId(Long id);
}
