package com.abhyam.quiz_service.controller;

import com.abhyam.quiz_service.model.QuestionWrapper;
import com.abhyam.quiz_service.model.Quiz;
import com.abhyam.quiz_service.model.QuizDTO;
import com.abhyam.quiz_service.model.Response;
import com.abhyam.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService service;

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuiz(){
        return service.getAllQuiz();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        return service.createQuiz(quizDTO);
    }

    @PostMapping("/getQuestions/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuizById(@PathVariable Long id){
        return service.getQuestionsForQuizById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id){
        return service.deleteQuiz(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuiz(@PathVariable Long id, @RequestBody QuizDTO quizDTO){
        return service.updateQuiz(id, quizDTO);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id, @RequestBody List<Response> responses){
        return service.calculateScore(id, responses);
    }
}
