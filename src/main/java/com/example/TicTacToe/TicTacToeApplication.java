package com.example.TicTacToe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicTacToeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);
        CellStatus[][] cells = {{CellStatus.E, CellStatus.X, CellStatus.E},
                {CellStatus.E, CellStatus.O, CellStatus.E},
                {CellStatus.E, CellStatus.E, CellStatus.E}
        };
    }
}
