package com.abtech.controller;

import com.abtech.dto.QuizDTO;
import com.abtech.dto.QuizRecord;
import com.abtech.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<QuizDTO> quizDTOList = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizDTOList);
    }

    @GetMapping("find")
    public ResponseEntity<?> getById(@RequestParam("id") Long id) {
        QuizDTO quizDTO = quizService.getQuizDTOById(id);
        return ResponseEntity.ok(quizDTO);
    }

    @PostMapping
    public ResponseEntity<?> createQuiz(@Valid @RequestBody QuizRecord quizRecord, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }

        QuizDTO quizDTO = quizService.createQuiz(quizRecord);

        return new ResponseEntity<>(quizDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/dlt")
    public ResponseEntity<?> deleteQuiz(@RequestParam("id") Long id) {

        quizService.deleteQuiz(id);

        return ResponseEntity.ok("Quiz successfully deleted");
    }

}
