package com.webcheckers.model;

//created by Marcus

public class Player {

    private String userName;
    public boolean spectating;

    /**
     * Creates a new instance of a Player class
     * @param userName the name that the user signed in with
     */
    public Player (String userName) {
        this.userName = userName;
    }

    /**
     * This will get and return the name of the player
     * @return the name of the player
     */
    public synchronized String getName() {
        return userName;
    }

    public boolean isSpectating(boolean spectating) {
        return spectating;
    }

    /**
     * Checks and sees if the provided Object is equal to
     * this instance of a Player class
     * @param obj the object to be compared
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Player)) return false;
        final Player that = (Player) obj;
        return this.userName.equals(that.userName);
    }

    /**
     * Returns a unique hashcode for the instance of the Player
     * @return the unique hashcode int
     */
    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    /**
     * Creates a String version of the Player's username
     * @return the player's name
     */
    @Override
    public String toString() {
        return this.userName;
    }
}
