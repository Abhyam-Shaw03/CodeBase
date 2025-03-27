package com.abhyam.quiz_service.service;

import com.abhyam.quiz_service.feign.QuizInterface;
import com.abhyam.quiz_service.model.QuestionWrapper;
import com.abhyam.quiz_service.model.Quiz;
import com.abhyam.quiz_service.model.QuizDTO;
import com.abhyam.quiz_service.model.Response;
import com.abhyam.quiz_service.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepo repo;

    @Autowired
    private QuizInterface quizInterface;

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuiz(){
        List<Quiz> quizzes = repo.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    public ResponseEntity<String> createQuiz(QuizDTO quizDTO){
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDTO.getTitle());
        quiz.setQuestionIds((quizDTO.getQuestionIds()));
        repo.save(quiz);
        return new ResponseEntity<>("QUIZ CREATED", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuiz(Long id){
        Quiz quiz = repo.findById(id).orElseThrow(()-> new RuntimeException("NOT FOUND..!!"));
        repo.deleteById(id);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    public ResponseEntity<String> updateQuiz(Long id, QuizDTO quizDTO) {
        Quiz quiz = repo.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND"));
        quiz.setTitle(quizDTO.getTitle());
        quiz.setQuestionIds(quizDTO.getQuestionIds());
        repo.save(quiz);
        return new ResponseEntity<>("UPDATED",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuizById(Long id){
        Quiz quiz = repo.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND"));
        List<Long> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsForQuizById(questionIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateScore(Long id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.calculateScore(responses);
        return score;
    }
}
