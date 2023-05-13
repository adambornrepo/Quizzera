package com.abtech.repository;

import com.abtech.domain.FillBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FillBlankRepository extends JpaRepository<FillBlank, Long> {
}
