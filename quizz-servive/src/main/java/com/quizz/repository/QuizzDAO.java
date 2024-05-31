package com.quizz.repository;


import com.quizz.entity.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizzDAO extends JpaRepository<Quizz, Long> {
}
