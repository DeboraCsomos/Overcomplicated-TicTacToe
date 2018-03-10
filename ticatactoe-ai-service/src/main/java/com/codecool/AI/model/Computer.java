package com.codecool.AI.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Computer {
    private String character;
    private String userName;

    private Computer() {
    }

    public Computer(String character, String userName) {
        this.character = character;
        this.userName = userName;
    }

    public ArrayList<String> move(ArrayList<String> board) {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i) == null) {
                board.set(i, character);
                break;
            }
        }
        return board;
    }
}
