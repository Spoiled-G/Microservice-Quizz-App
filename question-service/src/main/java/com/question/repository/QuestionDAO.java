package com.question.repository;


import com.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


    @Repository
    public interface QuestionDAO extends JpaRepository<Question, Integer> {

        public List<Question> findByCategoryIgnoreCase (String category);

        @Query(value="SELECT q.id FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
        public List<Integer> findRandomQuestionsByCategory(String category, int numQ);


    }
