package com.abtech.service;

import com.abtech.domain.Topic;
import com.abtech.dto.TopicDTO;
import com.abtech.dto.TopicRecord;
import com.abtech.exception.ConnectedResourceException;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.exception.UniqueValueAlreadyExistException;
import com.abtech.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public List<TopicDTO> getAllTopics() {
        List<TopicDTO> topicDTOList = new ArrayList<>();
        topicRepository
                .findAll()
                .forEach(topic -> topicDTOList.add(new TopicDTO(topic)));
        return topicDTOList;
    }

    public TopicDTO createTopic(TopicRecord topicRecord) {
        boolean existsName = topicRepository.existsByName(topicRecord.name());

        if (existsName) {
            throw new UniqueValueAlreadyExistException("Topic name is already in use : " + topicRecord.name());
        }

        Topic topic = new Topic();
        topic.setName(topicRecord.name());
        return new TopicDTO(topicRepository.save(topic));
    }

    public TopicDTO updateTopic(Integer id, TopicRecord topicRecord) {
        boolean existsName = topicRepository.existsByName(topicRecord.name());

        if (existsName) {
            throw new UniqueValueAlreadyExistException("Topic name is already in use : " + topicRecord.name());
        }

        Topic topic = topicRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No topic with this id : " + id));

        topic.setName(topicRecord.name());
        return new TopicDTO(topicRepository.save(topic));
    }

    public void deleteTopic(Integer id) {
        Topic topic = topicRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No topic with this id : " + id));

        if (!topic.getQuizList().isEmpty()) {
            throw new ConnectedResourceException("There are questions on this topic : " + topic.getName());
        }

        topicRepository.delete(topic);
    }

    public Topic getTopicById(Integer id) {
        return topicRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with this id : " + id));
    }
}
