package com.project.animalface_web.repository;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameAnswer;
import com.project.animalface_web.domain.GameQuestion;
import com.project.animalface_web.domain.GameResult;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameAnswerRepository gameAnswerRepository;

    @Autowired
    private GameQuestionRepository gameQuestionRepository;

    @Autowired
    private GameResultRepository gameResultRepository;

    @Test
    public void testSaveGameWithQuestionsAnswersAndResults() {
        // Create and save a new Game
        Game game = Game.builder()
                .gameName("Sample Game")
                .build();
        Game savedGame = gameRepository.save(game);
        log.info("Added Game ID: " + savedGame.getGameNo());

        // Create and save GameQuestions and associated GameAnswers
        List<GameQuestion> gameQuestions = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(i -> {
            GameQuestion gameQuestion = GameQuestion.builder()
                    .game(savedGame)
                    .questionText("Sample Question " + i)
                    .build();
            GameQuestion savedGameQuestion = gameQuestionRepository.save(gameQuestion);
            log.info("Saved GameQuestion ID: " + savedGameQuestion.getQuestionNo());

            // Create and save GameAnswers for this GameQuestion
            IntStream.rangeClosed(1, 2).forEach(j -> {
                GameAnswer gameAnswer = GameAnswer.builder()
                        .question(savedGameQuestion)
                        .answerText("Sample Answer " + j + " for Question " + i)
                        .score(j)
                        .build();
                GameAnswer savedGameAnswer = gameAnswerRepository.save(gameAnswer);
                log.info("Saved GameAnswer ID: " + savedGameAnswer.getAnswerNo());
            });

            // Add to list for the Game
            gameQuestions.add(savedGameQuestion);
        });

        // Save the updated Game with GameQuestions
        savedGame.setQuestions(gameQuestions);
        gameRepository.save(savedGame);

        // Create and save GameResults with score ranges divided into 5 intervals
        int intervalSize = 8; // (40-0) / 5
        IntStream.rangeClosed(0, 4).forEach(i -> {
            int minScore = i * intervalSize;
            int maxScore = minScore + intervalSize - 1;
            if (i == 4) {
                maxScore = 40; // Ensure the last interval goes up to 40
            }

            GameResult gameResult = GameResult.builder()
                    .resultText("Sample Result " + (i + 1))
                    .minScore(minScore)
                    .maxScore(maxScore)
                    .build();
            GameResult savedGameResult = gameResultRepository.save(gameResult);
            log.info("Saved GameResult ID: " + savedGameResult.getResultNo());
        });
    }
}
