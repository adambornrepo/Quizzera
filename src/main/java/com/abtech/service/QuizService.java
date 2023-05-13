package com.abtech.service;

import com.abtech.domain.Quiz;
import com.abtech.dto.QuizDTO;
import com.abtech.dto.QuizRecord;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.exception.StatusMismatchException;
import com.abtech.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final TopicService topicService;
    private final MultipleChoiceService multipleChoiceService;
    private final FillBlankService fillBlankService;
    private final OpenEndService openEndService;
    private final TrueFalseService trueFalseService;
    private final QuizUserService quizUserService;

    public List<QuizDTO> getAllQuizzes() {
        return quizRepository.findAll().stream().map(QuizDTO::new).toList();
    }

    public QuizDTO createQuiz(QuizRecord quizRecord) {

        Quiz quiz = new Quiz();

        quiz.setQuizTime(quizRecord.quizTime());
        quiz.setQuizUser(quizUserService.getUserById(quizRecord.quizUserId()));
        quiz.setName(quizRecord.name());
        quiz.setDescription(quizRecord.description());
        quiz.setTopic(topicService.getTopicById(quizRecord.topicId()));
        quiz.setFillBlankList(fillBlankService.getAllById(quizRecord.fillBlankList()));
        quiz.setTrueFalseList(trueFalseService.getAllById(quizRecord.trueFalseList()));
        quiz.setMultipleChoiceList(multipleChoiceService.getAllById(quizRecord.multipleChoiceList()));
        quiz.setOpenEndList(openEndService.getAllById(quizRecord.openEndList()));

        return new QuizDTO(quizRepository.save(quiz));
    }

    public void deleteQuiz(Long id) {
        Quiz quiz = quizRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with this id : " + id));

        if (quiz.getInUse()) {
            quiz.setInUse(false);
        } else {
            throw new StatusMismatchException("Quiz could not be deleted. Because the quiz is not in use");
        }

        quizRepository.save(quiz);
    }
}
