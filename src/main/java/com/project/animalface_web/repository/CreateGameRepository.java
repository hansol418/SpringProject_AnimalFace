package com.project.animalface_web.repository;

import com.project.animalface_web.domain.CreateGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CreateGameRepository extends JpaRepository<CreateGame, Long> {

    @Query("select c from CreateGame c where c.createGameNo=:createGameNo")
    Optional<CreateGame> findByCreateGameNo(@Param("createGameNo") long createGameNo);
}
