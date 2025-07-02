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
public class GamesRepositoryTest {

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
        // 1. 새로운 Game 생성 및 저장
        Game game = Game.builder()
                .gameName("MBTI Game")
                .build();
        Game savedGame = gameRepository.save(game);
        log.info("Added Game ID: " + savedGame.getGameNo());

        // 2. GameQuestion 및 관련 GameAnswer 생성 후 저장
        List<GameQuestion> gameQuestions = new ArrayList<>();
        IntStream.rangeClosed(1, 12).forEach(i -> {
            // 새로운 질문 생성
            GameQuestion gameQuestion = GameQuestion.builder()
                    .game(savedGame)
                    .questionText("MBTI Question " + i)
                    .build();
            GameQuestion savedGameQuestion = gameQuestionRepository.save(gameQuestion);
            log.info("Saved GameQuestion ID: " + savedGameQuestion.getQuestionNo());

            // 질문에 대한 두 개의 답변 생성 및 저장 (T/F 선택)
            IntStream.rangeClosed(1, 2).forEach(j -> {
                String answerText = j == 1 ? "T Answer for Question " + i : "F Answer for Question " + i;
                int score = j; // T=1, F=2로 점수 부여
                GameAnswer gameAnswer = GameAnswer.builder()
                        .question(savedGameQuestion)
                        .answerText(answerText)
                        .score(score)
                        .build();
                GameAnswer savedGameAnswer = gameAnswerRepository.save(gameAnswer);
                log.info("Saved GameAnswer ID: " + savedGameAnswer.getAnswerNo());
            });

            // 질문 리스트에 추가
            gameQuestions.add(savedGameQuestion);
        });

        // 게임에 질문들 설정 및 업데이트
        savedGame.setQuestions(gameQuestions);
        gameRepository.save(savedGame);

        // 3. GameResult 생성 및 저장 (점수 범위별 결과)
        int intervalSize = 8; // 점수 구간
        IntStream.rangeClosed(0, 4).forEach(i -> {
            int minScore = i * intervalSize;
            int maxScore = minScore + intervalSize - 1;
            if (i == 4) {
                maxScore = 24; // 마지막 구간은 최대 점수까지 포함
            }

            GameResult gameResult = GameResult.builder()
                    .resultText("MBTI Result " + (i + 1))
                    .minScore(minScore)
                    .maxScore(maxScore)
                    .build();
            GameResult savedGameResult = gameResultRepository.save(gameResult);
            log.info("Saved GameResult ID: " + savedGameResult.getResultNo());
        });
    }
}
