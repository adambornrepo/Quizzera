package com.abtech.repository;

import com.abtech.domain.Topic;
import com.abtech.dto.TopicRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    boolean existsByName(String name);
}
