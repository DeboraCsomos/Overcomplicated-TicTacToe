package com.codecool.enterprise.overcomplicated.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TicTacToeGame {
    private Map<String, Player> players = new HashMap<>();
    private ArrayList<String> board = new ArrayList<>(Collections.nCopies(9, ""));

    public TicTacToeGame() {
        Player player = new Player("X", "Anonymous");
        player.setGame(this);
        players.put("player", player);
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public Player getPlayer() {
        return players.get("player");
    }

    public ArrayList<String> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<String> board) {
        this.board = board;
    }

    public void changeBoard(int position, String character) {
        if (board.get(position).isEmpty()) {
            board.set(position, character);
        }
    }

    public String checkWinner() {
        String winnerChar = null;

        //check rows in board
        for (int i = 0; i < 7; i += 3) {
            if (!board.get(i).equals("") && board.get(i).equals(board.get(i + 1)) && board.get(i).equals(board.get(i + 2))) {
                winnerChar = board.get(i);
            }
        }

        //check columns in board
        for (int i = 0; i < 3; i++) {
            if (!board.get(i).equals("") && board.get(i).equals(board.get(i + 3)) && board.get(i).equals(board.get(i + 6))) {
                winnerChar = board.get(i);
            }
        }

        // Check diagonals
        if (!board.get(0).equals("") && board.get(0).equals(board.get(4)) && board.get(4).equals(board.get(8))) {
            winnerChar = board.get(0);
        }
        if (!board.get(2).equals("") && board.get(2).equals(board.get(4)) && board.get(4).equals(board.get(6))) {
            winnerChar = board.get(2);
        }

        if (winnerChar != null) {
            emptyBoard();
        }
        return winnerChar;
    }


    public boolean checkIsGameFinished() {
        for (String pos : board) {
            if (pos.isEmpty()) {
                return false;
            }
        }
        emptyBoard();
        return true;
    }

    public void emptyBoard() {
        Collections.fill(board,"");
    }

}
