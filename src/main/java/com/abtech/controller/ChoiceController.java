package com.abtech.controller;

import com.abtech.domain.MultipleChoice;
import com.abtech.dto.ChoiceDTO;
import com.abtech.dto.ChoiceRecord;
import com.abtech.service.ChoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chc")
public class ChoiceController {

    private ChoiceService choiceService;

    @Autowired
    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping("/fnd") // Mod + Adm
    public ResponseEntity<?> getChoiceById(@RequestParam("id") Long id) {
        ChoiceDTO choiceDTO = choiceService.getChoiceById(id);

        return ResponseEntity.ok(choiceDTO);
    }

    @GetMapping("/fndAll") // Mod + Adm
    public ResponseEntity<?> getAllChoicesInQuestion(@RequestParam("q-id") Long qtnId) {
        List<ChoiceDTO> choiceList = choiceService.getAllChoicesInQuestion(qtnId);

        return ResponseEntity.ok(choiceList);
    }


    @PostMapping // Mod + Adm
    public ResponseEntity<?> createChoice(@Valid @RequestBody ChoiceRecord choiceRecord, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }
        ChoiceDTO choiceDTO = choiceService.createChoice(choiceRecord);

        return new ResponseEntity<>("Choice successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/up") // Mod + Adm
    public ResponseEntity<?> updateChoice(@RequestParam("id") Long id, @Valid @RequestBody ChoiceRecord choiceRecord, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }
        ChoiceDTO choiceDTO = choiceService.updateChoice(id, choiceRecord);
        return ResponseEntity.ok(choiceDTO);
    }

    @DeleteMapping("dlt") // Mod + Adm
    public ResponseEntity<?> deleteChoice(@RequestParam("id") Long id) {
        choiceService.deleteChoice(id);

        return ResponseEntity.ok("Choice with " + id + " id deleted");
    }


}
