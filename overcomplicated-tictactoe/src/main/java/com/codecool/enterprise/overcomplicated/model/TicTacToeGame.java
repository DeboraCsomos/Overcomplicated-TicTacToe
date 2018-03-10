package com.codecool.enterprise.overcomplicated.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TicTacToeGame {
    private Map<String, Player> players = new HashMap<>();
    private ArrayList<String> board = new ArrayList<>(9));

    public TicTacToeGame() {
        Collections.fill(board, null);
        Player player = new Player("X", "Anonymous");
        player.setGame(this);
        players.put("player", player);
        Computer computer = new Computer("O", "computer");
        computer.setGame(this);
        players.put("computer", computer);
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public Computer getComputer() {
        return (Computer) players.get("computer");
    public ArrayList<String> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<String> board) {
        this.board = board;
    }

    public void changeBoard(int position, String character) {
        if (board.get(position) == null) {
            board.set(position, character);
        }
    }
}
