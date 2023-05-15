package com.abtech.controller;

import com.abtech.dto.ScoreDTO;
import com.abtech.dto.ScoreRequest;
import com.abtech.service.ScoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("scr")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllScores() {

        List<ScoreDTO> scoreList = scoreService.getAllScores();

        return ResponseEntity.ok(scoreList);
    }

    @GetMapping("/creator")
    public ResponseEntity<?> getAllScoresByCreatorId(@RequestParam("id") Long id) {

        List<ScoreDTO> scoreList = scoreService.getAllScoresByCreatorId(id);

        return ResponseEntity.ok(scoreList);
    }

    @PostMapping
    public ResponseEntity<?> createScore(@Valid @RequestBody ScoreRequest request, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }
        ScoreDTO scoreDTO = scoreService.createScore(request);

        return new ResponseEntity<>("Score successfully created", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> giveFinalScore(@RequestParam("id") Long id, Integer points){

        ScoreDTO scoreDTO = scoreService.giveFinalScore(id, points);

        return ResponseEntity.ok(scoreDTO);
    }


}
