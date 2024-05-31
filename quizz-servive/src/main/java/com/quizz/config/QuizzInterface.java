package com.quizz.config;

import com.quizz.entity.Response;
import com.quizz.payload.QuestionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizzInterface {

    @GetMapping("/questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuizz
            (@RequestParam("categoryName") String categoryName, @RequestParam("numQuestions") Integer numQuestions);

    @PostMapping("/questions/getQuestions")
    public ResponseEntity<List<QuestionDTO>> getQuestionsFromId(@RequestBody List<Integer> questionsIds);

    @PostMapping("/questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}

