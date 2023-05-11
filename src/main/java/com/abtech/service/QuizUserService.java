package com.abtech.service;

import com.abtech.domain.QuizUser;
import com.abtech.dto.QuizUserDTO;
import com.abtech.repository.QuizUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizUserService {

    private QuizUserRepository quizUserRepository;
    private UserInfoService userInfoService;

    @Autowired
    public QuizUserService(QuizUserRepository quizUserRepository, UserInfoService userInfoService) {
        this.quizUserRepository = quizUserRepository;
        this.userInfoService = userInfoService;
    }

    public QuizUserDTO getQuizUserByUsername(String username) {
        QuizUser quizUser = userInfoService.getQuizUserByUsername(username);
        return new QuizUserDTO(quizUser);
    }

    public QuizUserDTO updateQuizUser(String username, QuizUserDTO quizUserDTO) {
        QuizUser quizUser = userInfoService.getQuizUserByUsername(username);

        quizUser.setFirstName(quizUser.getFirstName());
        quizUser.setLastName(quizUser.getLastName());
        quizUser.setAge(quizUser.getAge());
        quizUser.setBirthdate(quizUser.getBirthdate());
        quizUser.setGender(quizUser.getGender());
        quizUser.setIsActive(quizUser.getIsActive());

        QuizUser save = quizUserRepository.save(quizUser);
        return new QuizUserDTO(save);
    }

}
