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

    public TicTacToeMove(Player currentPlayer, String gameTable) {
        this.currentPlayer = currentPlayer;
        this.gameTableSerialized = gameTable;
    }

    public TicTacToeMove() {
        this.currentPlayer = Player.X;
        this.gameTableSerialized = "__, __, __; __, __, __; __, __, __";
    }

    public Long getId() {
        return id;
    }
}