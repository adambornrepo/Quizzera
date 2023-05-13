package com.abtech.service;

import com.abtech.domain.MultipleChoice;
import com.abtech.dto.MultipleChoiceDTO;
import com.abtech.exception.ConnectedResourceException;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.repository.MultipleChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MultipleChoiceService {

    private final MultipleChoiceRepository multipleChoiceRepository;

    public MultipleChoiceDTO createMultipleChoiceQuestion(MultipleChoiceDTO questionDTO) {
        MultipleChoice multipleChoice = new MultipleChoice();
        multipleChoice.setQuestionText(questionDTO.getQuestionText());
        multipleChoice.setQuestionType(questionDTO.getQuestionType());
        multipleChoice.setScore(questionDTO.getScore());
        multipleChoice.setChcA(questionDTO.getChcA());
        multipleChoice.setChcB(questionDTO.getChcB());
        multipleChoice.setChcC(questionDTO.getChcC());
        multipleChoice.setChcD(questionDTO.getChcD());
        multipleChoice.setChcE(questionDTO.getChcE());
        multipleChoice.setAnswer(questionDTO.getAnswer());

        return new MultipleChoiceDTO(multipleChoiceRepository.save(multipleChoice));
    }

    public void updateMultipleChoiceQuestion(Long id) {

        MultipleChoice multipleChoice = getById(id);

        if (!multipleChoice.getInUse()) {
            multipleChoice.setInUse(true);
            multipleChoiceRepository.save(multipleChoice);
        } else {
            throw new ConnectedResourceException("This multiple choice question already in use. Quiz Id : " + multipleChoice.getQuiz().getId());
        }

    }

    public void deleteMultipleChoiceQuestion(Long id) {

        MultipleChoice multipleChoice = getById(id);

        if (!multipleChoice.getInUse()) {
            multipleChoiceRepository.delete(multipleChoice);
        } else {
            throw new ConnectedResourceException("This multiple choice question cannot be deleted. Because question in use. Quiz Id : " + multipleChoice.getQuiz().getId());
        }

    }

    public List<MultipleChoiceDTO> getAllUnused() {
        return multipleChoiceRepository.findAll().stream().map(MultipleChoiceDTO::new).toList();
    }

    public List<MultipleChoice> getAllById(List<Long> idList) {
        return idList.stream().map(this::getById).toList();
    }

    public MultipleChoice getById(Long id) {
        return multipleChoiceRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Multiple choice question not found with this id : " + id));
    }

}
