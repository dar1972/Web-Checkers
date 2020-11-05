package com.webcheckers.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    
    private Player red;
    private Player white;
    private Player Spectator;
    private int id;
    private static AtomicInteger atomicInteger = new AtomicInteger(0); //thread safe.
    private BoardView gameBoardWhite;
    private BoardView gameBoardRed; 
    private ArrayList<BoardView> snapshotsRed;
    private ArrayList<BoardView> snapshotsWhite;
    private ArrayList<BoardView> snapshotsTemp;
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
        gameBoardRed = new BoardView("red", true);
        this.white = white;
        gameBoardWhite = new BoardView("white", true);
        this.id = atomicInteger.incrementAndGet();
        playerWhoResigned = "";
        moves = new ArrayList<Move>();
        snapshotsRed = new ArrayList<BoardView>();
        snapshotsRed.add(new BoardView("red", true));
        snapshotsWhite = new ArrayList<BoardView>();
        snapshotsWhite.add(new BoardView("white", true));
        snapshotsTemp = new ArrayList<BoardView>();
        winner = null;
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

    public void setGameBoardWhite(BoardView gameBoardWhite) {
        this.gameBoardWhite = gameBoardWhite;
    }
    public BoardView getGameBoardRed(){
        return gameBoardRed;
    }

    public void setGameBoardRed(BoardView gameBoardRed) {
        this.gameBoardRed = gameBoardRed;
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
        int size1 = getActiveSnapshots().size();
        int size2 = getTempSnapshots().size();
        getActiveSnapshots().remove(getActiveSnapshots().size()-1);
        getTempSnapshots().remove(getTempSnapshots().size()-1);

        resetMoveAllowed(); //this will probably fuck with piece capture validation. This is a patch for more serious issues.
        if (getActiveSnapshots().size() < size1 && getTempSnapshots().size() < size2) {
            return true;
        }
        else {
            return false;
        }

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
        ArrayList<BoardView> activeSnapshots = getActiveSnapshots();
        ArrayList<BoardView> inactiveSnapshots = getInactiveSnapshots();
    
        BoardView activeBoard = copyBoard(activeSnapshots.get(getActiveSnapshots().size()-1));

        BoardView tempBoard;
        if (snapshotsTemp.size() == 0) {
            tempBoard = copyBoard(inactiveSnapshots.get(getInactiveSnapshots().size()-1));

        }
        else {
            tempBoard = copyBoard(snapshotsTemp.get(snapshotsTemp.size()-1));
        }

        Move invertedMove = move.invertMove();

        activeBoard.updateBoard(move);
        tempBoard.updateBoard(invertedMove);

        activeSnapshots.add(activeBoard);
        snapshotsTemp.add(tempBoard); //store in temporary board so the inactive player doesn't get their board prematurely refreshed

/*  Previous implementation.
        moves.add(move);

        BoardView r = copyBoard(snapshotsRed.get(snapshotsRed.size()-1));
        BoardView w = copyBoard(snapshotsWhite.get(snapshotsWhite.size()-1));


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
*/
    }

    public BoardView copyBoard(Object object) {
        try {
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
          outputStrm.writeObject(object);
          ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
          ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
          return (BoardView) objInputStream.readObject();
        }
        catch (Exception e) {
          e.printStackTrace();
          return null;
        }
      }

    public ArrayList<BoardView> getActiveSnapshots(){
        if (activeColor == ActiveColor.RED) {
            return snapshotsRed;
        }
        else {
            return snapshotsWhite;
        }
    }

    public ArrayList<BoardView> getInactiveSnapshots(){
        if (activeColor == ActiveColor.RED) {
            return snapshotsWhite;
        }
        else {
            return snapshotsRed;
        }
    }

    public ArrayList<BoardView> getTempSnapshots() {
        return snapshotsTemp;
    }
    public ArrayList<BoardView> getRedSnapshots(){
        
            return snapshotsRed;

    }

    public ArrayList<BoardView> getWhiteSnapshots(){
        
        return snapshotsWhite;

}


    public void resetMoveAllowed() {
        ArrayList<BoardView> redSnapshots = getRedSnapshots();
        ArrayList<BoardView> whiteSnapshots = getWhiteSnapshots();
        redSnapshots.get(redSnapshots.size()-1).setMoveAllowed(true);
        whiteSnapshots.get(whiteSnapshots.size()-1).setMoveAllowed(true);

    }
    public void setPlayerWhoResigned(String playerWhoResigned) {
        this.playerWhoResigned = playerWhoResigned;
    }

    public String getPlayerWhoResigned() {
        return playerWhoResigned;
    }

    public boolean colorCleared() {
        ArrayList<BoardView> snapshots = getActiveSnapshots();
        BoardView board = snapshots.get(snapshots.size()-1);

        boolean cleared = board.colorCleared();
        return cleared;
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
