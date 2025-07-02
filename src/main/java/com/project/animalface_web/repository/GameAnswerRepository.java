package com.project.animalface_web.repository;

import com.project.animalface_web.domain.GameAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameAnswerRepository extends JpaRepository<GameAnswer, Long> {
    List<GameAnswer> findByQuestion_QuestionNo(Long questionNo);
}

