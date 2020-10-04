package com.webcheckers.model;

public class Game {
    
    private Player red;
    private Player white;
    private int id;

    public Game (Player red, Player white) {

        this.red = red;
        this.white = white;
        id = 0; //CHANGE THIS LATER FOR MULTIPLE GAMES OR ELSE EVERYTHING WILL BE BROKEN.
    }


    public Player getRed() {
        return red;
    }   

    public Player getWhite() {
        return white;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Game)) return false;
        final Game that = (Game) obj;
        if (this.id == that.id) {
            return true;
        }
        else {
            return false;
        }
    }


}
