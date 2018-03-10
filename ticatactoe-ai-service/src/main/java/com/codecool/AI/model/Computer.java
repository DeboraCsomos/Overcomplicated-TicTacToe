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
        for (String pos : board) {
            if (pos == null) {
                pos = character;
                break;
            }
        }
        return board;
    }
}
