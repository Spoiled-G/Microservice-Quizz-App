package com.question.controller;

import com.question.entity.Question;
import com.question.entity.Response;
import com.question.payload.QuestionDTO;
import com.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping("/getAllQuestions")
    public List<Question> getAllQuestion( ) {
        return questionService.getAllQuestions();
    }

    @PostMapping("/saveQuestion")
    public Question saveQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return savedQuestion;

    }


    @GetMapping("/categories/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);

    }


    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuizz
            (@RequestParam String categoryName, Integer numQuestions){
        return questionService.getQuestionsForQuizz(categoryName, numQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionDTO>> getQuestionsFromId(@RequestBody List<Integer> questionsIds){
        return questionService.getQuestionsFromId(questionsIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

}

