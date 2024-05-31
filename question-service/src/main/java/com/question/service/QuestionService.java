package com.question.service;


import com.question.entity.Question;
import com.question.entity.Response;
import com.question.payload.QuestionDTO;
import com.question.repository.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getAllQuestions() {
        List<Question> questionList;
        try {
            questionList = questionDAO.findAll();
        } catch (Exception e) {

            System.err.println("Error fetching all questions: " + e.getMessage());
            questionList = new ArrayList<>();
        }
        return questionList;
    }

    public Question saveQuestion(Question question) {
        Question saved = null;
        try {
            saved = questionDAO.save(question);
        } catch (Exception e) {

            System.err.println("Error saving question: " + e.getMessage());
        }
        return saved;
    }

    public List<Question> getQuestionByCategory(String category) {
        List<Question> questionList;
        try {
            questionList = questionDAO.findByCategoryIgnoreCase(category);
        } catch (Exception e) {

            System.err.println("Error fetching questions by category: " + e.getMessage());
            questionList = new ArrayList<>();
        }
        return questionList;
    }

    public String deleteQuestion(int id) {
        String message = "Failed to delete";
        try {
            questionDAO.deleteById(id);
            message = "Deleted";
        } catch (Exception e) {

            System.err.println("Error deleting question: " + e.getMessage());
        }
        return message;
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuizz(String categoryName, Integer numQuestions) {
        List<Integer> randomQuestionsByCategory = questionDAO.findRandomQuestionsByCategory(categoryName, numQuestions);

        return new ResponseEntity<>(randomQuestionsByCategory, HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionDTO>> getQuestionsFromId(List<Integer> questionsIds) {
        List<QuestionDTO> questionDto = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id: questionsIds){
            questions.add(questionDAO.findById(id).get());
        }
for(Question question: questions){
    QuestionDTO questionDTO = new QuestionDTO();
    questionDTO.setId(question.getId());
    questionDTO.setQuestionTitle(question.getQuestionTitle());
    questionDTO.setOption1(question.getOption1());
    questionDTO.setOption2(question.getOption2());
    questionDTO.setOption3(question.getOption3());
    questionDTO.setOption4(question.getOption4());

    questionDto.add(questionDTO);

}
        return new ResponseEntity<>(questionDto,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;
        for(Response response: responses){
            Question question = questionDAO.findById(response.getId()).get();
            if(response.getResponse().equals(question.getCorrectAnswer()))
                score++;

        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
