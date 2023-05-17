package com.abtech.service;

import com.abtech.domain.Quiz;
import com.abtech.dto.QuizDTO;
import com.abtech.dto.QuizRecord;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.exception.StatusMismatchException;
import com.abtech.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final TopicService topicService;
    private final QuizUserService quizUserService;

    private final MultipleChoiceService multipleChoiceService;
    private final FillBlankService fillBlankService;
    private final OpenEndService openEndService;
    private final TrueFalseService trueFalseService;


    public List<QuizDTO> getAllQuizzes() {
        List<QuizDTO> rsp = new ArrayList<>();
        quizRepository.findAll().forEach(quiz -> rsp.add(new QuizDTO(quiz)));
        return rsp;
    }


    public QuizDTO createQuiz(QuizRecord quizRecord) {

        if (
                quizRecord.fillBlankList() == null &&
                        quizRecord.trueFalseList() == null &&
                        quizRecord.openEndList() == null &&
                        quizRecord.multipleChoiceList() == null
        ) {
            throw new ResourceNotFoundException("Cannot create quiz without questions");
        }

        boolean validFill = quizRecord.fillBlankList() == null || fillBlankService.isValidRequest(quizRecord.fillBlankList());
        boolean validTF = quizRecord.trueFalseList() == null || trueFalseService.isValidRequest(quizRecord.trueFalseList());
        boolean validOpen = quizRecord.openEndList() == null || openEndService.isValidRequest(quizRecord.openEndList());
        boolean validMultiple = quizRecord.multipleChoiceList() == null || multipleChoiceService.isValidRequest(quizRecord.multipleChoiceList());

        if (!validTF || !validFill || !validMultiple || !validOpen) {
            throw new StatusMismatchException("Some questions already in use");
        }


        Quiz quiz = new Quiz();

        quiz.setQuizTime(quizRecord.quizTime());
        quiz.setQuizUser(quizUserService.getUserById(quizRecord.quizUserId()));
        quiz.setName(quizRecord.name());
        quiz.setDescription(quizRecord.description());
        quiz.setTopic(topicService.getTopicById(quizRecord.topicId()));

        Quiz saved = quizRepository.save(quiz);


        if (!(quizRecord.multipleChoiceList() == null)) {
            multipleChoiceService.getAllById(quizRecord.multipleChoiceList()).forEach(q -> saved.getMultipleChoiceList().add(q));
            quizRecord.multipleChoiceList().forEach(id -> multipleChoiceService.updateMultipleChoiceQuestion(id, saved));
        }

        if (!(quizRecord.trueFalseList() == null)) {
            trueFalseService.getAllById(quizRecord.trueFalseList()).forEach(q -> saved.getTrueFalseList().add(q));
            quizRecord.trueFalseList().forEach(id -> trueFalseService.updateTrueFalseQuestion(id, saved));
        }

        if (!(quizRecord.fillBlankList() == null)) {
            fillBlankService.getAllById(quizRecord.fillBlankList()).forEach(q -> saved.getFillBlankList().add(q));
            quizRecord.fillBlankList().forEach(id -> fillBlankService.updateFillBlankQuestion(id, saved));
        }

        if (!(quizRecord.openEndList() == null)) {
            openEndService.getAllById(quizRecord.openEndList()).forEach(q -> saved.getOpenEndList().add(q));
            quizRecord.openEndList().forEach(id -> openEndService.updateOpenEndQuestion(id, saved));
        }


        return new QuizDTO(saved);
    }

    public void deleteQuiz(Long id) {
        Quiz quiz = getById(id);

        if (quiz.getInUse()) {
            quiz.setInUse(false);
        } else {
            throw new StatusMismatchException("Quiz could not be deleted. Because the quiz is not active anymore");
        }

        quizRepository.save(quiz);
    }

    public QuizDTO getQuizDTOById(Long id) {
        return new QuizDTO(getById(id));
    }


    public Quiz getById(Long id) {
        return quizRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with this id : " + id));
    }

    public boolean existsQuizById(Long id) {
        return quizRepository.existsById(id);
    }
}
