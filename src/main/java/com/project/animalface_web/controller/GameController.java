package com.project.animalface_web.controller;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameAnswer;
import com.project.animalface_web.domain.GameQuestion;
import com.project.animalface_web.domain.GameResult;
import com.project.animalface_web.service.GameService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private long currentQuestionIndex = 0;
    private int totalScore = 0;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // 게임 목록 조회
    @GetMapping
    public String listGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "game/list";  // games/list.html
    }

    // 특정 게임의 질문 조회
    @GetMapping("/{gameNo}")
    public String showQuestions(@PathVariable Long gameNo, Model model) {
        List<GameQuestion> questions = gameService.getQuestionsByGameId(gameNo);

        if (currentQuestionIndex < questions.size()) {
            GameQuestion question = questions.get((int) currentQuestionIndex);
            model.addAttribute("question", question);
            model.addAttribute("answers", question.getAnswers());
            model.addAttribute("gameNo", gameNo);
            return "game/view"; // 질문 보기
        } else {
            // 질문이 더 이상 없을 경우 결과 페이지로 이동
            return "redirect:/game/" + gameNo + "/result";
        }
    }

    @PostMapping("/{gameNo}/answer")
    public String answerQuestion(@PathVariable Long gameNo, @RequestParam String answer) {
        List<GameQuestion> questions = gameService.getQuestionsByGameId(gameNo);
        GameQuestion question = questions.get((int) currentQuestionIndex);

        // 선택한 답변의 점수 추가
        question.getAnswers().stream()
                .filter(a -> a.getAnswerText().equals(answer))
                .findFirst()
                .ifPresent(selectedAnswer -> totalScore += selectedAnswer.getScore());

        currentQuestionIndex++;
        return "redirect:/game/" + gameNo; // 다음 질문으로 이동
    }

    // 결과 페이지 처리
    @GetMapping("/{gameNo}/result")
    public String showResult(@PathVariable Long gameNo, Model model) {
        // 점수에 따른 결과를 가져오는 로직 (예시로 GameResult를 찾는 메서드 추가 필요)
        GameResult result = gameService.getResultByScore(totalScore);
        log.info("Total Score: " + totalScore + ", Result: " + result); // 로그 추가

        model.addAttribute("result", result);
        model.addAttribute("totalScore", totalScore); // 점수도 모델에 추가
        return "game/result"; // 결과 화면
    }

    // 게임 생성 폼
    @GetMapping("/create")
    public String createGameForm(Model model) {
        model.addAttribute("game", new Game());
        return "createGame/create2";  // games/create.html
    }

    // 게임 생성 처리
    @PostMapping
    public String createGame(@ModelAttribute Game game) {
        gameService.saveGame(game);
        return "redirect:/game";
    }

    // 게임 삭제 처리
    @DeleteMapping("/{gameNo}")
    public String deleteGame(@PathVariable Long gameNo) {
        gameService.deleteGame(gameNo);
        return "redirect:/game";
    }
}
