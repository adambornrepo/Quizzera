package com.abtech.repository;

import com.abtech.domain.TrueFalse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrueFalseRepository extends JpaRepository<TrueFalse, Long> {
}
