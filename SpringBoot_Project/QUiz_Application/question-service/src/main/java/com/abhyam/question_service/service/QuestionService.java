package com.abhyam.question_service.service;

import com.abhyam.question_service.model.Question;
import com.abhyam.question_service.model.QuestionWrapper;
import com.abhyam.question_service.model.Response;
import com.abhyam.question_service.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repo;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category){
        try{
            return new ResponseEntity<>(repo.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> addQuestion(Question question){
        repo.save(question);
        return new ResponseEntity<>(question,HttpStatus.OK);
    }

    public ResponseEntity<String> updateQuestion(Question updatequestion, Long id){
        Question question = repo.findById(id).orElseThrow(()->new RuntimeException("NOT FOUND"));
        question.setQuestionTitle(updatequestion.getQuestionTitle());
        question.setOption1(updatequestion.getOption1());
        question.setOption2(updatequestion.getOption2());
        question.setOption3(updatequestion.getOption3());
        question.setOption4(updatequestion.getOption4());
        question.setCategory(updatequestion.getCategory());
        question.setRightAnswer(updatequestion.getRightAnswer());
        question.setDifficultyLevel(updatequestion.getDifficultyLevel());
        repo.save(question);
        return new ResponseEntity<>("UPDATED",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteQuestion(Long id){
        Optional<Question> question = repo.findById(id);
        if(question.isPresent()){
            repo.deleteById(id);
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuizByIds(List<Long> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Long id : questionIds){
            questions.add(repo.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        Integer right = 0;

        for (Response response : responses){
            Question question = repo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

//    public ResponseEntity<List<Long>> getQuestionsForQuiz(String categoryName, Long numQuestions) {
//        List<Long> questions = repo.findRandomQuestionsByCategory(categoryName, numQuestions);
//        return new ResponseEntity<>(questions, HttpStatus.OK);
//    }

//    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Long> questionIds) {
//        List<QuestionWrapper> wrappers = new ArrayList<>();
//        List<Question> questions = new ArrayList<>();
//
//        for(Long id : questionIds){
//            questions.add(repo.findById(id).get());
//        }
//
//        for(Question question : questions){
//            QuestionWrapper wrapper = new QuestionWrapper();
//            wrapper.setId(question.getId());
//            wrapper.setQuestionTitle(question.getQuestionTitle());
//            wrapper.setOption1(question.getOption1());
//            wrapper.setOption2(question.getOption2());
//            wrapper.setOption3(question.getOption3());
//            wrapper.setOption4(question.getOption4());
//            wrappers.add(wrapper);
//        }
//
//        return new ResponseEntity<>(wrappers, HttpStatus.OK);
//    }
}
