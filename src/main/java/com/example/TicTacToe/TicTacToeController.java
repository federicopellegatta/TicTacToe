package com.example.TicTacToe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicTacToeController {
    private final TicTacToeMoveRepository ticTacToeRepository;

    public TicTacToeController(TicTacToeMoveRepository ticTacToeMoveRepository) {
        this.ticTacToeRepository = ticTacToeMoveRepository;
    }

    @GetMapping("/games")
    public Iterable<TicTacToeMove> getAllGames() {
        return ticTacToeRepository.findAll();
    }

    @PostMapping("/game/makeMove")
    public GameLogic makeMove(@RequestParam int i, @RequestParam int j) {
        var gameTable = ticTacToeRepository.findTopByOrderByIdDesc();
        GameLogic game;
        if (gameTable.isEmpty()) {
            game = newGame();
        } else {
            game = new GameLogic(gameTable.get());
            
            if (!game.isMoveValid(i, j)) throw new IllegalArgumentException("Move not valid");
            if (game.isGameOver()) throw new IllegalArgumentException("Game is over");

            game.makeMove(i, j);
            ticTacToeRepository.save(game.toEntity());

        }
        return game;
    }

    @PostMapping("/game/newGame")
    public GameLogic newGame() {
        var newGame = new TicTacToeMove();
        GameLogic game = new GameLogic(newGame);
        ticTacToeRepository.save(game.toEntity());
        return game;
    }
}