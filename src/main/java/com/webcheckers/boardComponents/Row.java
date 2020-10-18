package com.webcheckers.boardComponents;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Row implements Iterable{
    //Created by Beck Anderson

    private final int index;
    public Space[] row;

    /**
     * Create a new row
     * @param index the row
     * @param color the color space should be
     */
    public Row(int index, String color,String pieceColor){
        this.index = index;
        Space space;
        this.row = new Space[8];  //create a new array for th column
        //create spaces for each spot in the board
        for (int i=0;i<8;i++) {
            int colorSet = 0;
            //create a white space if color is white
            if (color.equals("White")) {
                space = new Space(i,"White",pieceColor);
                //colorSet = 1 will switch it to black next time
                colorSet = 1;
            }
            //create a black space if color is black
            else {
                space = new Space(i, "Black",pieceColor);
                //colorSet = 2 will switch it to white next time
                colorSet = 2;
            }
            //add the space to the row
            row[i] = space;
            // change the next space color based on the last one
            if(colorSet==1){
                color = "Black";
            }else{
                color = "White";
            }
        }
    }

    /**
     *
     */
    public void validateRow(){
        for( Space space : row){
            if( index%2 == 0){
                if( space.getCellIdx()%2 == 1){
                    space.setValid(true);
                }
            }
            else{
                if( space.getCellIdx()%2 == 0){
                    space.setValid(true);
                }
            }
        }
    }

    /**
     *
     */
    public synchronized void RedRow(){
        for( Space space : row){
            if( index%2 == 0){
                if( space.getCellIdx()%2 == 1){
                    space.setPiece( new Piece("s", "r", space.getCellIdx()));
                }
            }
            else {
                if( space.getCellIdx()%2 == 0){
                    space.setPiece( new Piece("s", "r", space.getCellIdx()));
                }
            }
        }
    }

    /**
     *
     */
    public synchronized void WhiteRow(){
        for( Space space : row){
            if( index%2 == 0){
                if( space.getCellIdx()%2 == 1){
                    space.setPiece( new Piece("s", "w", space.getCellIdx()));
                }
            }
            else {
                if( space.getCellIdx()%2 == 0){
                    space.setPiece( new Piece("s", "w", space.getCellIdx()));
                }
            }
        }
    }

    public int getIndex(){
        return index;
    }

    /**
     * Returns an iterator over elements of type Space.
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return new Iterator<Space>() {

            private int currentIndex = 0;

            /**
             * Returns Space if the iteration has more elements.
             * (In other words, returns Space if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return True if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return currentIndex < 8;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override
            public Space next() {
                if(this.hasNext()) {
                    int current = currentIndex;
                    currentIndex ++;
                    return row[current];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
