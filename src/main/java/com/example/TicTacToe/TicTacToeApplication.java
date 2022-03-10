package com.example.TicTacToe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicTacToeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);
        CellStatus[][] cells = {{CellStatus.__, CellStatus.X, CellStatus.__},
                {CellStatus.__, CellStatus.O, CellStatus.__},
                {CellStatus.__, CellStatus.__, CellStatus.__}
        };
    }
}
