package com.example.TicTacToe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicTacToeMoveRepository extends JpaRepository<TicTacToeMove, Long> {

    public Optional<TicTacToeMove> findTopByOrderByIdDesc();
}
