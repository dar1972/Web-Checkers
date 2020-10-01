package com.webcheckers.boardComponents;

public class Space extends Row{
    //Created by Beck Anderson

    private final int cellIdx;

    public Space(int cellIdx){
        this.cellIdx = cellIdx;
    }

    public int getCellIdx(){
        return cellIdx;
    }

    public boolean isValid(){
        return true;
    }

    public Piece getPiece(){
        //Change to piece on space
        //if none then null
        return null;
    }
}
