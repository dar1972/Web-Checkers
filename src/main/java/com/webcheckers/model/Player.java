package com.webcheckers.model;

//created by Marcus

public class Player {

    private String userName;

    public Player (String userName) {

        this.userName = userName;
    }

    public String getName() {
        return userName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Player)) return false;
        final Player that = (Player) obj;
        return this.userName.equals(that.userName);
    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    @Override
    public String toString() {
        
        return this.userName;
    }
}
