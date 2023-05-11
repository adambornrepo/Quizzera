package com.abtech.repository;

import com.abtech.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    @Query("SELECT c FROM Choice c WHERE c.multipleChoice=:qtnId")
    List<Choice> findAllByQuestion(@Param("qtnId") Long id);
}
