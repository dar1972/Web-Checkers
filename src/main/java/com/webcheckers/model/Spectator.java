package com.webcheckers.model;

import com.webcheckers.util.Message;

// Created by Dhruv
public class Spectator extends Player {

    boolean isSpectating;
    private Player currentUser;
    private Player redPlayer;
    private Player whitePlayer;
    private BoardView board;
    private Message message;

    public enum viewMode{
        PLAY,
        SPECTATOR,
        REPLAY
    }

    public enum activeColor{
        RED,
        WHITE
    }

    /**
     * Creates a new instance of a Player class
     *
     * @param userName the name that the user signed in with
     */
    public Spectator(String userName) {
        super(userName);
        isSpectating = false;
    }
}
