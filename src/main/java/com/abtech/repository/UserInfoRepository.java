package com.abtech.repository;

import com.abtech.domain.UserInfo;
import com.abtech.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsername(String username) throws ResourceNotFoundException;
}
