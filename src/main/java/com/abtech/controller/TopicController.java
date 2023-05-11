package com.abtech.controller;

import com.abtech.domain.Topic;
import com.abtech.dto.TopicDTO;
import com.abtech.dto.TopicRecord;
import com.abtech.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tpc")
public class TopicController {

    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTopics() {
        List<TopicDTO> topicList = topicService.getAllTopics();
        return ResponseEntity.ok(topicList);
    }

    @PostMapping
    public ResponseEntity<?> createTopic(@Valid @RequestBody TopicRecord topicRecord, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }
        TopicDTO topicDTO = topicService.createTopic(topicRecord);

        return new ResponseEntity<>("Topic successfully created", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateTopic(@RequestParam("id") Integer id, @Valid @RequestBody TopicRecord topicRecord, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }
        TopicDTO topicDTO = topicService.updateTopic(id, topicRecord);
        return ResponseEntity.ok(topicDTO);
    }

    @DeleteMapping("/dlt")
    public ResponseEntity<?> deleteTopic(@RequestParam("id") Integer id) {
        topicService.deleteTopic(id);

        return ResponseEntity.ok("Topic with " + id + " id deleted");
    }

}
