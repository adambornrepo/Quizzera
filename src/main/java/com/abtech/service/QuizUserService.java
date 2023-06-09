package com.abtech.service;

import com.abtech.domain.QuizUser;
import com.abtech.dto.QuizUserDTO;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.repository.QuizUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizUserService {

    private final QuizUserRepository quizUserRepository;
    private final UserInfoService userInfoService;

    public QuizUserDTO getQuizUserByUsername(String username) {
        QuizUser quizUser = userInfoService.getQuizUserByUsername(username);
        return new QuizUserDTO(quizUser);
    }

    public QuizUserDTO updateQuizUser(String username, QuizUserDTO quizUserDTO) {
        QuizUser quizUser = userInfoService.getQuizUserByUsername(username);

        quizUser.setFirstName(quizUserDTO.getFirstName());
        quizUser.setLastName(quizUserDTO.getLastName());
        quizUser.setAge(quizUserDTO.getAge());
        quizUser.setBirthdate(quizUserDTO.getBirthdate());
        quizUser.setGender(quizUserDTO.getGender());
        quizUser.setIsActive(quizUser.getIsActive());

        QuizUser save = quizUserRepository.save(quizUser);
        return new QuizUserDTO(save);
    }

    public QuizUser getUserById(Long id) {
        return quizUserRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
    }
}
