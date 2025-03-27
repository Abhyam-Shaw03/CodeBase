package com.abhyam.question_service.controller;

import com.abhyam.question_service.model.Question;
import com.abhyam.question_service.model.QuestionWrapper;
import com.abhyam.question_service.model.Response;
import com.abhyam.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return service.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getALLQuestionsByCategory(@PathVariable String category){
        return service.getAllQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return service.addQuestion(question);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuestion(@RequestBody Question updatequestion, @PathVariable Long id){
        return service.updateQuestion(updatequestion, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id){
        return service.deleteQuestion(id);
    }

    @PostMapping("/getQuizQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuizById(@RequestBody List<Long> questionIds){
        return service.getQuestionsForQuizByIds(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses){
        return service.calculateScore(responses);
    }
}
