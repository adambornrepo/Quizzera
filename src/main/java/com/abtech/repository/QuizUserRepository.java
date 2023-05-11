package com.abtech.repository;

import com.abtech.domain.QuizUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizUserRepository extends JpaRepository<QuizUser, Long> {
}
