package com.webcheckers.model;

import com.webcheckers.boardComponents.BoardView;

import java.util.ArrayList;

public class Game {
    
    private Player red;
    private Player white;
    private int id = 0;
    private BoardView gameBoardWhite;
    private BoardView gameBoardRed; 
    private ArrayList<BoardView> snapshotsRed;
    private ArrayList<BoardView> snapshotsWhite;
    private ArrayList<Move> moves;
    private String playerWhoResigned;
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
        playerWhoResigned = "";
        moves = new ArrayList<Move>();
        snapshotsRed = new ArrayList<BoardView>();
        snapshotsRed.add(new BoardView("red"));
        snapshotsWhite = new ArrayList<BoardView>();
        snapshotsWhite.add(new BoardView("white"));

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

    public void updateActivePlayer(){
        if(activeColor==ActiveColor.RED){
            activeColor = ActiveColor.WHITE;
        }else{
            activeColor = ActiveColor.RED;
        }

        moves.clear();
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player player) {
        this.winner = player;
    }
    public boolean backupMove() {
        //do something.
        return true;
    }

    public int getGameId() {
        return id;
    }

    public ArrayList<Move> getMoves(){
        return moves;
    }

  /*  public void removeMoveFromList(Move move) {
        int index = moves.lastIndexOf(move);
        moves.remove(index);
    }
*/
    public void storeMove(Move move){
        moves.add(move);

        BoardView r = snapshotsRed.get(snapshotsRed.size()-1);
        BoardView w = snapshotsWhite.get(snapshotsWhite.size()-1);
        Move invertedMove = move.invertMove();


        if (activeColor == ActiveColor.RED) {

            r.updateBoard(move);
            w.updateBoard(invertedMove);
        }
        else {
            r.updateBoard(invertedMove);
            w.updateBoard(move);

        }

        snapshotsRed.add(r);
        snapshotsWhite.add(w);
    }

    public ArrayList<BoardView> getActiveSnapshots(){
        if (activeColor == ActiveColor.RED) {
            return snapshotsRed;
        }
        else {
            return snapshotsWhite;
        }
    }


    public void setPlayerWhoResigned(String playerWhoResigned) {
        this.playerWhoResigned = playerWhoResigned;
    }

    public String getPlayerWhoResigned() {
        return playerWhoResigned;
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
