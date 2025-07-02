package com.project.animalface_web.service;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameAnswer;
import com.project.animalface_web.domain.GameQuestion;
import com.project.animalface_web.domain.GameResult;
import jakarta.transaction.Transactional;
import java.util.List;

@Transactional
public interface GameService {
    List<Game> getAllGames();
    Game getGameById(Long gameNo);
    Game saveGame(Game game);
    void deleteGame(Long gameNo);
    List<GameQuestion> getQuestionsByGameId(Long gameNo);
    List<GameAnswer> getAnswersByQuestionId(Long questionNo);
    GameQuestion getQuestionById(Long questionNo); // 추가된 메서드
    GameResult getResultByScore(int score);
}
