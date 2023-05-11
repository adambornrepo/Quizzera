package com.abtech.service;

import com.abtech.domain.Choice;
import com.abtech.dto.ChoiceDTO;
import com.abtech.dto.ChoiceRecord;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChoiceService {

    private ChoiceRepository choiceRepository;

    @Autowired
    public ChoiceService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    public ChoiceDTO createChoice(ChoiceRecord choiceRecord) {

        Choice choice = new Choice();
        choice.setChoiceText(choiceRecord.choiceText());
        choice.setAnswerType(choiceRecord.answerType());

        Choice save = choiceRepository.save(choice);


        return new ChoiceDTO(save);
    }


    public ChoiceDTO updateChoice(Long id, ChoiceRecord choiceRecord) {

        Choice choice = choiceRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Choice not found with this Id : " + id));

        choice.setAnswerType(choiceRecord.answerType());
        choice.setChoiceText(choiceRecord.choiceText());

        Choice save = choiceRepository.save(choice);

        return new ChoiceDTO(save);
    }

    public ChoiceDTO getChoiceById(Long id) {

        Choice choice = choiceRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Choice not found with this Id : " + id));

        return new ChoiceDTO(choice);
    }

    public void deleteChoice(Long id) {

        Choice choice = choiceRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Choice not found with this Id : " + id));
        choice.setInUse(false);
        updateChoice(id, new ChoiceRecord(choice.getChoiceText(), choice.getAnswerType(), false));
    }

    public List<ChoiceDTO> getAllChoicesInQuestion(Long id) {
        List<Choice> choiceList = choiceRepository.findAllByQuestion(id);

        if (choiceList.isEmpty()) {
            throw new ResourceNotFoundException("No choice found with this question id : " + id);
        }

        List<ChoiceDTO> choiceDTOList = new ArrayList<>();
        choiceList
                .forEach(choice -> choiceDTOList.add(new ChoiceDTO(choice)));

        return choiceDTOList;
    }
}
