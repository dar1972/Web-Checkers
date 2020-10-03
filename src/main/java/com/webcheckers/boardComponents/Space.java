package com.webcheckers.boardComponents;

public class Space{
    //Created by Beck Anderson

    private final int cellIdx;
    private final String color;
    private Piece piece;

    /**
     * Constructor for a new space
     * @param cellIdx the index in a row that the piece is
     * @param color the color of the space (WHITE/BLACK)
     */
    public Space(int cellIdx, String color, String pieceColor){
        this.cellIdx = cellIdx;
        this.color = color;
        if(color.equals("Black")){
            if(!(pieceColor.equals("q"))){
                piece = new Piece("s", pieceColor, cellIdx);
            }
        }else{
            piece = null;
        }
    }

    /**
     * Gets the cell index of the space
     * @return the cell index
     */
    public int getCellIdx(){
        return cellIdx;
    }

    /**
     * checks to see if the space is a valid position for a piece
     * @return true if it is and false if not
     */
    public boolean isValid(){
        return color.equals("Black");
    }

    /**
     * Checks to see if a piece is on the space
     * @return the piece is there is one, null if not
     */
    public Piece getPiece(){
        return piece;
    }
}
