package com.project.animalface_web.service;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameAnswer;
import com.project.animalface_web.domain.GameQuestion;
import com.project.animalface_web.domain.GameResult;
import com.project.animalface_web.repository.GameAnswerRepository;
import com.project.animalface_web.repository.GameQuestionRepository;
import com.project.animalface_web.repository.GameRepository;
import com.project.animalface_web.repository.GameResultRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor // 생성자 자동 생성
@Transactional
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameQuestionRepository gameQuestionRepository;
    private final GameAnswerRepository gameAnswerRepository;
    private final GameResultRepository gameResultRepository;

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGameById(Long gameNo) {
        return gameRepository.findById(gameNo)
                .orElseThrow(() -> new IllegalArgumentException("Game not found")); // 예외 처리
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void deleteGame(Long gameNo) {
        gameRepository.deleteById(gameNo);
    }

    @Override
    public List<GameQuestion> getQuestionsByGameId(Long gameNo) {
        return gameQuestionRepository.findByGame_GameNo(gameNo);
    }

    @Override
    public List<GameAnswer> getAnswersByQuestionId(Long questionNo) {
        List<GameAnswer> answers = gameAnswerRepository.findByQuestion_QuestionNo(questionNo);
        if (answers.size() < 2) {
            throw new IllegalArgumentException("Two answers are required for each question.");
        }
        return answers;
    }

    @Override
    public GameQuestion getQuestionById(Long questionNo) { // 추가된 메서드
        return gameQuestionRepository.findById(questionNo)
                .orElseThrow(() -> new IllegalArgumentException("Question not found")); // 예외 처리
    }

    @Override
    public GameResult getResultByScore(int score) {
        return gameResultRepository.findAll().stream()
                .filter(result -> score >= result.getMinScore() && score <= result.getMaxScore())
                .findFirst()
                .orElse(null); // 적절한 결과가 없으면 null 반환
    }
}
