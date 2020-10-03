package com.webcheckers.model;

public class Game {
    
    private Player red;
    private Player white;

    public Game (Player red, Player white) {

        this.red = red;
        this.white = white;

    }


    public Player getRed() {
        return red;
    }   

    public Player getWhite() {
        return white;
    }
    


}
