package com.codecool.enterprise.overcomplicated.model;

public class Player {
    protected String character;
    protected String userName;
    protected TicTacToeGame game;


    public Player(String character, String userName) {
        this.character = character;
        this.userName = userName;
    }

    public void setGame(TicTacToeGame game) {
        this.game = game;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void move(int position) {
        game.changeBoard(position, character);
    }
}
