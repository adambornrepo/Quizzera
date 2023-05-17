package com.abtech.service;

import com.abtech.domain.Quiz;
import com.abtech.domain.TrueFalse;
import com.abtech.dto.TrueFalseDTO;
import com.abtech.exception.ConnectedResourceException;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.repository.TrueFalseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrueFalseService {

    private final TrueFalseRepository trueFalseRepository;

    public TrueFalseDTO createTrueFalseQuestion(TrueFalseDTO questionDTO) {

        TrueFalse trueFalse = new TrueFalse();

        trueFalse.setQuestionType(questionDTO.getQuestionType());
        trueFalse.setQuestionText(questionDTO.getQuestionText());
        trueFalse.setScore(questionDTO.getScore());
        trueFalse.setAnswer(questionDTO.getAnswer());

        return new TrueFalseDTO(trueFalseRepository.save(trueFalse));
    }

    public void updateTrueFalseQuestion(Long id, Quiz quiz) {

        TrueFalse trueFalse = getById(id);

        if (!trueFalse.getInUse()) {
            trueFalse.setInUse(true);
            trueFalse.setQuiz(quiz);
            trueFalseRepository.save(trueFalse);
        } else {
            throw new ConnectedResourceException("This true/false question already in use. Quiz Id : " + trueFalse.getQuiz().getId());
        }

    }

    public void deleteTrueFalseQuestion(Long id) {

        TrueFalse trueFalse = getById(id);

        if (!trueFalse.getInUse()) {
            trueFalseRepository.delete(trueFalse);
        } else {
            throw new ConnectedResourceException("This true/false question cannot be deleted. Because question in use. Quiz Id : " + trueFalse.getQuiz().getId());
        }

    }

    public List<TrueFalseDTO> getAllUnused() {
        return trueFalseRepository.findAll().stream().filter(f-> !f.getInUse()).map(TrueFalseDTO::new).toList();
    }

    public List<TrueFalse> getAllById(List<Long> idList) {
        return idList.stream().map(this::getById).toList();
    }

    public TrueFalse getById(Long id) {
        return trueFalseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("True/False question not found with this id : " + id));
    }

    public boolean isValidRequest(List<Long> requestList){
        for (Long id : requestList) if (getById(id).getInUse()) return false;
        return true;
    }

}
