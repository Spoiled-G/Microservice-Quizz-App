package com.quizz.payload;


import lombok.Data;

@Data
public class QuizzDto {

    private String categoryName;
    Integer numQuestions;
    String title;
}
