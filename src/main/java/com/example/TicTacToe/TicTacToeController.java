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

    @PostMapping("/game/newMove")
    public GameLogic makeMove(@RequestParam int i, @RequestParam int j) {
        var gameTable = ticTacToeRepository.findTopByOrderByIdDesc();
        
        if (gameTable.isPresent()) {
            GameLogic game = GameLogic.fromEntity(gameTable.get());
            game.makeMove(i, j);
            ticTacToeRepository.save(game.toEntity());
            return game;
        } else {
            var firstMove = new TicTacToeMove();
            GameLogic game = GameLogic.fromEntity(firstMove);
            game.makeMove(i, j);
            ticTacToeRepository.save(game.toEntity());
            return game;
        }
    }

}