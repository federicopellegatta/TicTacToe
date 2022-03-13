package com.example.TicTacToe;

import javax.persistence.*;

@Entity
public class TicTacToeMove {
    public Player currentPlayer;
    public String gameTableSerialized;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public TicTacToeMove(String gameTableSerialized, Player currentPlayer) {
        this.gameTableSerialized = gameTableSerialized;
        this.currentPlayer = currentPlayer;
    }

    public TicTacToeMove() {
        this.currentPlayer = Player.X;
        this.gameTableSerialized = "E,E,E;E,E,E;E,E,E";
    }

    public Long getId() {
        return id;
    }
}