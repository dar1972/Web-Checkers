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
        this.row = new Space[8];
        for (int i=0;i<8;i++) {
            int colorSet = 0;
            if (color.equals("White")) {
                space = new Space(i,"White",pieceColor);
                colorSet = 1;
            }else {
                space = new Space(i, "Black",pieceColor);
                colorSet = 2;
            }
            row[i] = space;
            if(colorSet==1){
                color = "Black";
            }else{
                color = "White";
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
