package com.webcheckers.boardComponents;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoardView implements Iterable{
    //Created by Beck Anderson

    public Row[] gameBoard;

    public BoardView(){
        gameBoard = new Row[8];
        Row row;
        for (int i = 0; i<8;i++){
            if (i==0||i==2||i==4||i==6) {
                row = new Row(i,"White");
            }else{
                row = new Row(i,"Black");
            }
            gameBoard[i] = row;
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator<Row>() {

            private int currentRow = 0;

            /**
             * Returns true if the iteration has more elements.
             * (In other words, returns true if next() would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return currentRow < 8;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override
            public Row next() {
                if(this.hasNext()) {
                    int current = currentRow;
                    currentRow ++;
                    return gameBoard[current];
                }
                throw new NoSuchElementException();
            }
        };

    }
}
