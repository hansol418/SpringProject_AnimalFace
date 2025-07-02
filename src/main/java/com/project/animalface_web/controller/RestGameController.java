//package com.project.animalface_web.controller;
//
//import com.project.animalface_web.domain.Game;
//import com.project.animalface_web.domain.GameResult;
//import com.project.animalface_web.service.GameService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/game")
//public class RestGameController {
//
//    private final GameService gameService;
//
//    @GetMapping("/{gameId}/start")
//    public ResponseEntity<Game> startGame(@PathVariable Long gameId) {
//        Game game = gameService.startGame(gameId);
//        return ResponseEntity.ok(game);
//    }
//
//    @PostMapping("/{gameId}/result")
//    public ResponseEntity<GameResult> getGameResult(@PathVariable Long gameId, @RequestBody List<Long> selectedAnswerIds) {
//        GameResult result = gameService.calculateResult(selectedAnswerIds);
//        return ResponseEntity.ok(result);
//    }
//}
//
//
