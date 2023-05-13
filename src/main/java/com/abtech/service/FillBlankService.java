package com.abtech.service;

import com.abtech.domain.FillBlank;
import com.abtech.dto.FillBlankDTO;
import com.abtech.exception.ConnectedResourceException;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.repository.FillBlankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FillBlankService {

    private final FillBlankRepository fillBlankRepository;

    public FillBlankDTO createFillBlankQuestion(FillBlankDTO questionDTO) {
        FillBlank fillBlank = new FillBlank();

        fillBlank.setQuestionType(questionDTO.getQuestionType());
        fillBlank.setQuestionText(questionDTO.getQuestionText());
        fillBlank.setScore(questionDTO.getScore());
        fillBlank.setAnswerText(questionDTO.getAnswerText());

        return new FillBlankDTO(fillBlankRepository.save(fillBlank));
    }

    public void updateFillBlankQuestion(Long id) {

        FillBlank fillBlank = getById(id);

        if (!fillBlank.getInUse()) {
            fillBlank.setInUse(true);
            fillBlankRepository.save(fillBlank);
        } else {
            throw new ConnectedResourceException("This fillblank question already in use. Quiz Id : " + fillBlank.getQuiz().getId());
        }

    }

    public void deleteFillBlankQuestion(Long id) {

        FillBlank fillBlank = getById(id);

        if (!fillBlank.getInUse()) {
            fillBlankRepository.delete(fillBlank);
        } else {
            throw new ConnectedResourceException("This fill in the blank question cannot be deleted. Because question in use. Quiz Id : " + fillBlank.getQuiz().getId());
        }

    }

    public List<FillBlankDTO> getAllUnused() {
        return fillBlankRepository.findAll().stream().map(FillBlankDTO::new).toList();
    }

    public List<FillBlank> getAllById(List<Long> idList) {
        return idList.stream().map(this::getById).toList();
    }

    public FillBlank getById(Long id) {
        return fillBlankRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FillBlank question not found with this id : " + id));
    }
}
