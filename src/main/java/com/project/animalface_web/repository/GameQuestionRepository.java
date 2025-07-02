package com.project.animalface_web.repository;

import com.project.animalface_web.domain.GameQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameQuestionRepository extends JpaRepository<GameQuestion, Long> {
    List<GameQuestion> findByGame_GameNo(Long gameNo);
}
