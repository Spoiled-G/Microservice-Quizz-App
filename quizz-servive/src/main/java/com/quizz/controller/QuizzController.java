package com.quizz.controller;


import com.quizz.entity.Response;
import com.quizz.payload.QuestionDTO;
import com.quizz.payload.QuizzDto;
import com.quizz.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quizz")
public class QuizzController {

    @Autowired
    QuizzService quizzService;

         @PostMapping("/create")
         public ResponseEntity<String> createQuizz(@RequestBody QuizzDto dto){
             return quizzService.createQuizz(dto.getCategoryName(),dto.getNumQuestions(),dto.getTitle());
     }

     @GetMapping("/get/{id}")
     public ResponseEntity<List<QuestionDTO>> getQuizz(@PathVariable int id){
         return quizzService.getQuizz(id);
     }

    @PostMapping("/submit/{id}")
     public ResponseEntity<Integer> calculateResult( @PathVariable Integer id,@RequestBody List<Response> response){
             return quizzService.calculateResult(id,response);
     }

}
