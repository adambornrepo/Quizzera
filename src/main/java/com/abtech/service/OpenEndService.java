package com.abtech.service;

import com.abtech.domain.OpenEnd;
import com.abtech.dto.OpenEndDTO;
import com.abtech.exception.ConnectedResourceException;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.repository.OpenEndRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenEndService {

    private final OpenEndRepository openEndRepository;

    public OpenEndDTO createOpenEndQuestion(OpenEndDTO questionDTO) {

        OpenEnd openEnd = new OpenEnd();

        openEnd.setQuestionType(questionDTO.getQuestionType());
        openEnd.setQuestionText(questionDTO.getQuestionText());
        openEnd.setScore(questionDTO.getScore());

        return new OpenEndDTO(openEndRepository.save(openEnd));
    }

    public void updateOpenEndQuestion(Long id) {

        OpenEnd openEnd = getById(id);

        if (!openEnd.getInUse()) {
            openEnd.setInUse(true);
            openEndRepository.save(openEnd);
        } else {
            throw new ConnectedResourceException("This open-end question already in use. Quiz Id : " + openEnd.getQuiz().getId());
        }


    }

    public void deleteOpenEndQuestion(Long id) {

        OpenEnd openEnd = getById(id);

        if (!openEnd.getInUse()) {
            openEndRepository.delete(openEnd);
        } else {
            throw new ConnectedResourceException("This open-end question cannot be deleted. Because question in use. Quiz Id : " + openEnd.getQuiz().getId());
        }
    }

    public List<OpenEndDTO> getAllUnused() {
        return openEndRepository.findAll().stream().map(OpenEndDTO::new).toList();
    }

    public List<OpenEnd> getAllById(List<Long> idList) {
        return idList.stream().map(this::getById).toList();
    }

    public OpenEnd getById(long id) {
        return openEndRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Open-end question not found with this id : " + id));
    }
}
