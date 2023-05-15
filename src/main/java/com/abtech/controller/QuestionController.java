package com.abtech.controller;

import com.abtech.dto.*;
import com.abtech.enums.QuestionType;
import com.abtech.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/qtn")
@RequiredArgsConstructor
public class QuestionController {

    private final FillBlankService fillBlankService;
    private final MultipleChoiceService multipleChoiceService;
    private final TrueFalseService trueFalseService;
    private final OpenEndService openEndService;

    @GetMapping
    public ResponseEntity<?> getAllUnused() {

        List<FillBlankDTO> fillBlankDTOList = fillBlankService.getAllUnused();
        List<OpenEndDTO> openEndDTOList = openEndService.getAllUnused();
        List<MultipleChoiceDTO> multipleChoiceDTOList = multipleChoiceService.getAllUnused();
        List<TrueFalseDTO> trueFalseDTOList = trueFalseService.getAllUnused();

        List<QuestionDTO> allUnusedQuestion = Stream.of(fillBlankDTOList, openEndDTOList, multipleChoiceDTOList, trueFalseDTOList)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return ResponseEntity.ok(allUnusedQuestion);
    }


    @PostMapping("/mlt")
    public ResponseEntity<?> createQuestion(@Valid @RequestBody MultipleChoiceDTO questionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }

        MultipleChoiceDTO rsp = multipleChoiceService.createMultipleChoiceQuestion(questionDTO);
        return new ResponseEntity<>(rsp, HttpStatus.CREATED);
    }

    @PostMapping("/fb")
    public ResponseEntity<?> createQuestion(@Valid @RequestBody FillBlankDTO questionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }

        FillBlankDTO rsp = fillBlankService.createFillBlankQuestion(questionDTO);
        return new ResponseEntity<>(rsp, HttpStatus.CREATED);
    }

    @PostMapping("/tf")
    public ResponseEntity<?> createQuestion(@Valid @RequestBody TrueFalseDTO questionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }

        TrueFalseDTO rsp = trueFalseService.createTrueFalseQuestion(questionDTO);
        return new ResponseEntity<>(rsp, HttpStatus.CREATED);
    }

    @PostMapping("/oe")
    public ResponseEntity<?> createQuestion(@Valid @RequestBody OpenEndDTO questionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }

        OpenEndDTO rsp = openEndService.createOpenEndQuestion(questionDTO);
        return new ResponseEntity<>(rsp, HttpStatus.CREATED);
    }

    @DeleteMapping("/dlt")
    public ResponseEntity<?> deleteTopic(@RequestParam("id") Long id, QuestionType questionType) {

        if (questionType.equals(QuestionType.MULTIPLE_CHOICE)) {
            multipleChoiceService.deleteMultipleChoiceQuestion(id);
        } else if (questionType.equals(QuestionType.FILL_BLANK)) {
            fillBlankService.deleteFillBlankQuestion(id);
        } else if (questionType.equals(QuestionType.TRUE_FALSE)) {
            trueFalseService.deleteTrueFalseQuestion(id);
        } else if (questionType.equals(QuestionType.OPEN_END)) {
            openEndService.deleteOpenEndQuestion(id);
        }

        return ResponseEntity.ok("Question ( " + questionType + " ) with " + id + " id deleted");
    }

}
