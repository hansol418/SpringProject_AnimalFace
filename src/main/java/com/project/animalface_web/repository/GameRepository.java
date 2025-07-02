package com.project.animalface_web.repository;

import com.project.animalface_web.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT a.score FROM GameAnswer a WHERE a.answerNo = :answerId")
    Optional<Integer> findAnswerScoreById(@Param("answerId") Long answerId);

}
