package com.quizz.service;


import com.quizz.config.QuizzInterface;
import com.quizz.entity.Quizz;
import com.quizz.entity.Response;
import com.quizz.payload.QuestionDTO;
import com.quizz.repository.QuizzDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;



@Service
public class QuizzService {

    @Autowired
    QuizzDAO quizzDAO;

    @Autowired
    QuizzInterface quizzInterface;

    public ResponseEntity<String> createQuizz(String category, int numQ, String title) {

        List<Integer> questions = quizzInterface.getQuestionsForQuizz(category, numQ).getBody();

        Quizz quizz = new Quizz();
        quizz.setTitle(title);
        quizz.setQuestionIds(questions);
        quizzDAO.save(quizz);

        return new ResponseEntity<>("Quizz Created", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionDTO>> getQuizz(long id) {
        Quizz quizz = quizzDAO.findById(id).get();
        List<Integer> questionids = quizz.getQuestionIds();
        ResponseEntity<List<QuestionDTO>> questionsFromId = quizzInterface.getQuestionsFromId(questionids);
        return questionsFromId;

    }

    public ResponseEntity<Integer> calculateResult(@PathVariable Integer id, @RequestBody List<Response> response){
        ResponseEntity<Integer> score = quizzInterface.getScore(response);
        return score;
    }

}

