package com.webcheckers.model;

import com.webcheckers.boardComponents.BoardView;

import java.util.Deque;

public class Game {
    
    private Player red;
    private Player white;
    private int id = 0;
    private BoardView gameBoardWhite;
    private BoardView gameBoardRed;
    private Deque<Move> moves;
    public enum ActiveColor {
        RED,
        WHITE
    }
    private ActiveColor activeColor = ActiveColor.RED;
    private Player winner;

    /**
     * Create a new instance of a game
     * @param red the red player in a game
     * @param white the white player in a game
     */
    public Game (Player red, Player white) {
        this.red = red;
        gameBoardRed = new BoardView("red");
        this.white = white;
        gameBoardWhite = new BoardView("white");
        id = id++;
    }

    /**
     * this will get and return the red player
     * @return the red player
     */
    public synchronized Player getRed() {
        return red;
    }

    /**
     * this will get and return the white player
     * @return the white player
     */
    public synchronized Player getWhite() {
        return white;
    }

    public BoardView getGameBoardWhite(){
        return gameBoardWhite;
    }

    public BoardView getGameBoardRed(){
        return gameBoardRed;
    }

    public ActiveColor getActiveColor() {
        return activeColor;
    }

    public Player getActivePlayer(){
        if(activeColor==ActiveColor.RED){
            return red;
        }else{
            return white;
        }
    }

    public void updateActivePlayer(Move move){
        move.invertMove();
        if(activeColor==ActiveColor.RED){
            activeColor = ActiveColor.WHITE;
        }else{
            activeColor = ActiveColor.RED;
        }
    }

    public Player getWinner() {
        return winner;
    }

    public boolean backupMove() {
        //do something.
        return true;
    }

    public int getGameId() {
        return id;
    }

    public Deque<Move> getMoves(){
        return moves;
    }

    public void storeMove(Move move){
        moves.add(move);
    }

    public Move popNewestMove(){
        return moves.pollLast();
    }

    public Move popOldestMove(){
        return moves.pollFirst();
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
