package com.abhyam.quiz_service.feign;

import com.abhyam.quiz_service.model.QuestionWrapper;
import com.abhyam.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses);

    @PostMapping("/question/getQuizQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuizById(@RequestBody List<Long> questionIds);
}
