package com.webcheckers.model;

public class Game {
    
    private Player red;
    private Player white;
    private int id;
    public enum ActiveColor {
        RED,
        WHITE
    }
    private ActiveColor activeColor;
    private String winner;

    /**
     * Create a new instance of a game
     * @param red the red player in a game
     * @param white the white player in a game
     */
    public Game (Player red, Player white) {
        this.red = red;
        this.white = white;
        this.activeColor = ActiveColor.RED;
        id = 0; //CHANGE THIS LATER FOR MULTIPLE GAMES OR ELSE EVERYTHING WILL BE BROKEN.
    }

    /**
     * this will get and return the red player
     * @return the red player
     */
    public Player getRed() {
        return red;
    }

    /**
     * this will get and return the white player
     * @return the white player
     */
    public Player getWhite() {
        return white;
    }



     public ActiveColor getActiveColor() {
        return activeColor;
     }

     public String getWinner() {

        return winner;
     }

     public boolean backupMove() {
         //do something. 
         return true;
     }

    /**
     * This will check to see if the selected Object is equal
     * to this Game instance
     * @param obj the object to be compared
     * @return true if equal, false if not
     */
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
