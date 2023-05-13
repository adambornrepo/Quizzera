package com.abtech.service;

import com.abtech.domain.QuizUser;
import com.abtech.domain.UserInfo;
import com.abtech.dto.QuizUserDTO;
import com.abtech.dto.RegistrationDTO;
import com.abtech.dto.UserInfoDTO;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.exception.UniqueValueAlreadyExistException;
import com.abtech.repository.QuizUserRepository;
import com.abtech.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoService {

    private UserInfoRepository userInfoRepository;
    private QuizUserRepository quizUserRepository;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository, QuizUserRepository quizUserRepository) {
        this.userInfoRepository = userInfoRepository;
        this.quizUserRepository = quizUserRepository;
    }

    public UserInfoDTO getUserInfoByUsername(String username) {

        UserInfo userInfo = userInfoRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No user info with this username : " + username));

        return new UserInfoDTO(userInfo);
    }

    @Transactional
    public void updateUserInfo(String username, UserInfoDTO userInfoDTO) {

        boolean existsUsername = userInfoRepository.existsByUsername(userInfoDTO.getUsername());
        boolean existsEmail = userInfoRepository.existsByEmail(userInfoDTO.getEmail());

        UserInfo userInfo = userInfoRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No user info with this username : " + username));

        if (!userInfo.getUsername().equals(userInfoDTO.getUsername()) && existsUsername) {
            throw new UniqueValueAlreadyExistException("Username is already in use : " + userInfoDTO.getUsername());
        } else if (!userInfo.getEmail().equals(userInfoDTO.getEmail()) && existsEmail) {
            throw new UniqueValueAlreadyExistException("Email is already in use : " + userInfoDTO.getEmail());
        }

        userInfo.setUsername(userInfoDTO.getUsername());
        userInfo.setPassword(userInfoDTO.getPassword());
        userInfo.setEmail(userInfoDTO.getEmail());
        userInfo.setUserRole(userInfoDTO.getUserRole());
        userInfo.setIsActive(userInfoDTO.getIsActive());

        userInfoRepository.save(userInfo);
    }

    @Transactional
    public QuizUserDTO createUser(RegistrationDTO registrationDTO) {
        boolean existsUsername = userInfoRepository.existsByUsername(registrationDTO.getUsername());
        boolean existsEmail = userInfoRepository.existsByEmail(registrationDTO.getEmail());

        if (existsUsername) {
            throw new UniqueValueAlreadyExistException("Username is already in use : " + registrationDTO.getUsername());
        } else if (existsEmail) {
            throw new UniqueValueAlreadyExistException("Email is already in use : " + registrationDTO.getEmail());
        }

        UserInfo savedInfo = userInfoRepository.save(registrationDTO.buildUserInfo());
        QuizUser quizUser = registrationDTO.buildQuizUser();
        quizUser.setUserInfo(savedInfo);
        QuizUser save = quizUserRepository.save(quizUser);

        return new QuizUserDTO(save);
    }

    @Transactional
    public void deleteUser(String username) {
        UserInfo userInfo = userInfoRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No user info with this username : " + username));

        QuizUser quizUser = userInfo.getQuizUser();
        quizUser.setIsActive(false);
        quizUserRepository.save(quizUser);

        userInfo.setIsActive(false);
        userInfoRepository.save(userInfo);

    }

    public QuizUser getQuizUserByUsername(String username) {
        UserInfo userInfo = userInfoRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No user info with this username : " + username));

        return userInfo.getQuizUser();
    }
}
