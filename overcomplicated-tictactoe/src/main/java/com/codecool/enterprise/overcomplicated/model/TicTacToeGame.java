package com.codecool.enterprise.overcomplicated.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TicTacToeGame {
    private Map<String, Player> players = new HashMap<>();
    private String[] gameState = new String[9];

    public TicTacToeGame() {
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
    }

    public String[] getGameState() {
        return gameState;
    }

    public void changeGameState(int position, String character) {
        if (gameState[position] == null) {
            gameState[position] = character;
        }
    }
}
