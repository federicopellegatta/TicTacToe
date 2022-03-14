package com.example.TicTacToe;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

enum CellStatus {E, X, O}

enum Player {X, O}

class InvalidTicTacToeInput extends RuntimeException {
    InvalidTicTacToeInput(String msg) {
        super(msg);
    }
}

public class GameLogic {
    public CellStatus[][] gameTable = new CellStatus[3][3];
    public Player currentPlayer;

    GameLogic() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                gameTable[i][j] = CellStatus.E;

        currentPlayer = Player.X;
    }

    public GameLogic(TicTacToeMove move) {
        this();
        this.gameTable = serializedToArray(move.gameTableSerialized);
        this.currentPlayer = move.currentPlayer;
    }

    static private boolean isWinning(CellStatus c0, CellStatus c1, CellStatus c2) {
        return c0 != CellStatus.E && c0 == c1 && c1 == c2;
    }

    static private Optional<Player> getWinner(CellStatus c) {
        return Optional.of(c == CellStatus.X ? Player.X : Player.O);
    }


    public static CellStatus[][] serializedToArray(String str) {
        return Arrays.stream(str.split(";"))
                .map(s -> Arrays.stream(s.split(","))
                        .map(CellStatus::valueOf).toArray(CellStatus[]::new))
                .toArray(CellStatus[][]::new);
    }


    public void makeMove(int i, int j) throws InvalidTicTacToeInput {

        if (i < 0 || i > 2 || j < 0 || j > 2) {
            throw new InvalidTicTacToeInput("Out of Bounds");
        }

        if (gameTable[i][j] != CellStatus.E) {
            throw new InvalidTicTacToeInput("Position already used");
        }

        gameTable[i][j] = currentPlayer == Player.X ? CellStatus.X : CellStatus.O;
        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    }

    public Optional<Player> getTheWinner() {
        var g = this.gameTable;

        // Rows
        if (isWinning(g[0][0], g[0][1], g[0][2])) return getWinner(g[0][0]);
        if (isWinning(g[1][0], g[1][1], g[1][2])) return getWinner(g[1][0]);
        if (isWinning(g[2][0], g[2][1], g[2][2])) return getWinner(g[2][0]);

        // Columns
        if (isWinning(g[0][0], g[1][0], g[2][0])) return getWinner(g[0][0]);
        if (isWinning(g[0][1], g[1][1], g[2][1])) return getWinner(g[0][1]);
        if (isWinning(g[0][2], g[1][2], g[2][2])) return getWinner(g[0][2]);

        // Diagonals
        if (isWinning(g[0][0], g[1][1], g[2][2])) return getWinner(g[0][0]);
        if (isWinning(g[0][2], g[1][1], g[2][0])) return getWinner(g[0][2]);

        return Optional.empty();
    }

    public boolean isDraw() {
        return Arrays.stream(gameTable)
                .allMatch(r -> Arrays.stream(r).noneMatch(c -> c == CellStatus.E));
    }

    public boolean isMoveValid(int i, int j) {
        return gameTable[i][j] == CellStatus.E;
    }

    public boolean isGameOver() {
        return getTheWinner().isPresent() || isDraw();
    }

    public TicTacToeMove toEntity() {
        var gameTableSerialized = Arrays.stream(gameTable)
                .map(r -> Arrays.stream(r).map(Enum::toString).collect(Collectors.joining(",")))
                .collect(Collectors.joining(";"));
        return new TicTacToeMove(gameTableSerialized, currentPlayer);
    }

}
