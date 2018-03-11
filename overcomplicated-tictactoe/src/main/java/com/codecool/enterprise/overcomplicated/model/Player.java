package com.codecool.enterprise.overcomplicated.model;

public class Player {
    private String character;
    private String userName;
    private TicTacToeGame game;
    private String avatar;


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

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }
}
