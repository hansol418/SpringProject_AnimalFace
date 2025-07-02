package com.project.animalface_web.repository;

import com.project.animalface_web.domain.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameResultRepository extends JpaRepository<GameResult, Long> {
    @Query("SELECT r FROM GameResult r WHERE :score BETWEEN r.minScore AND r.maxScore")
    GameResult findByScoreRange(@Param("score") int score);
}

