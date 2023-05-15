package com.abtech.service;

import com.abtech.domain.*;
import com.abtech.dto.ScoreDTO;
import com.abtech.dto.ScoreRequest;
import com.abtech.exception.InvalidScoreException;
import com.abtech.exception.ResourceNotFoundException;
import com.abtech.exception.StatusMismatchException;
import com.abtech.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final QuizService quizService;
    private final QuizUserService quizUserService;


    public List<ScoreDTO> getAllScores() {
        return scoreRepository.findAll().stream().map(ScoreDTO::new).toList();
    }

    public List<ScoreDTO> getAllScoresByCreatorId(Long id) {

        return scoreRepository.findAllByQuizUserId(id).stream().map(ScoreDTO::new).toList();
    }

    public ScoreDTO createScore(ScoreRequest request) {

        boolean isScored = scoreRepository.existsByQuizIdAndQuizUserId(request.getQuizId(), request.getQuizUserId());
        if (isScored) {
            throw new StatusMismatchException("This quiz already scored. User id : " + request.getQuizId() + " Quiz id : " + request.getQuizUserId());
        }
        //boolean existsQuiz = quizService.existsQuizById(request.getQuizId());// actually , this is unnecessary
        Quiz quiz = quizService.getById(request.getQuizId());

        int totalScore = 0;
        int index = 0;

        for (MultipleChoice multipleChoice : quiz.getMultipleChoiceList()) {
            if (String.valueOf(multipleChoice.getAnswer()).equalsIgnoreCase(String.valueOf(request.getAnswerList().get(index)))) {
                totalScore += multipleChoice.getScore();
            }
            index++;
        }

        for (TrueFalse trueFalse : quiz.getTrueFalseList()) {
            if (trueFalse.getAnswer().equals(request.getAnswerList().get(index))) {
                totalScore += trueFalse.getScore();
            }
            index++;
        }

        for (FillBlank fillBlank : quiz.getFillBlankList()) {
            if (fillBlank.getAnswerText().equalsIgnoreCase(String.valueOf(request.getAnswerList().get(index)))) {
                totalScore += fillBlank.getScore();
            }
            index++;
        }

        Score score = new Score();
        score.setQuiz(quiz);
        score.setQuizUser(quizUserService.getUserById(request.getQuizUserId()));
        score.setAnswerList(request.getAnswerList());
        score.setTotalScore(totalScore);
        score.setAnswer(score.flatAnswer(score.getAnswerList()));
        score.setEvaluated(score.getEvaluationInfo(quiz));

        return new ScoreDTO(scoreRepository.save(score));
    }

    public ScoreDTO giveFinalScore(Long id, Integer points) {

        if (points < 0) {
            throw new InvalidScoreException("Score cannot be negative : " + points);
        }

        Score score = scoreRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found with this id : " + id));

        List<OpenEnd> openEndList = score.getQuiz().getOpenEndList();

        int maxOpenEndScore = 0;

        if (score.isEvaluated()) {
            throw new StatusMismatchException("This quiz already evaluated");
        } else {
            maxOpenEndScore = openEndList.stream().mapToInt(OpenEnd::getScore).sum();
        }

        if (points > maxOpenEndScore) {
            throw new InvalidScoreException("Scores cannot exceed the total score of open-ended questions. Max: " + maxOpenEndScore + " Given : " + points);
        }

        score.setEvaluated(true);
        score.setTotalScore(score.getTotalScore() + points);

        return new ScoreDTO(scoreRepository.save(score));
    }
}
