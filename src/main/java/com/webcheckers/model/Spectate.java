package com.webcheckers.model;

// Created by Dhruv
public class Spectate extends Player {

    boolean isSpectating;

    /**
     * Creates a new instance of a Player class
     *
     * @param userName the name that the user signed in with
     */
    public Spectate(String userName) {
        super(userName);
        isSpectating = false;
    }
}
