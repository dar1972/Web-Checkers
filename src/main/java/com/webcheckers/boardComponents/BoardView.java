package com.webcheckers.boardComponents;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoardView implements Iterable{
    //Created by Beck Anderson

    public Row[] gameBoard;

    public BoardView(String color){
        gameBoard = new Row[8];
        Row row;
        String pieceColor = "q";
        for (int i = 0; i<8;i++){
            if(i==0||i==1||i==2){
                if(color.equals("white")) {
                    pieceColor = "r";
                }else{
                    pieceColor = "w";
                }
            }else if(i==7||i==6||i==5){
                if(color.equals("white")) {
                    pieceColor = "w";
                }else{
                    pieceColor = "r";
                }
            }else if(i==3){
                pieceColor = "q";
            }
            if (i==0||i==2||i==4||i==6) {
                row = new Row(i,"White",pieceColor);
            }else{
                row = new Row(i,"Black",pieceColor);
            }
            gameBoard[i] = row;
        }
    }

    public void fillRedBoard(){
        for( Row row: gameBoard){
            int index = row.getIndex();
            switch (index) {
                case 0:
                    row.WhiteRow();
                    break;
                case 1:
                    row.WhiteRow();
                    break;
                case 2:
                    row.WhiteRow();
                    break;
                case 3:
                    row.validateRow();
                    break;
                case 4:
                    row.validateRow();
                    break;
                case 5:
                    row.RedRow();
                    break;
                case 6:
                    row.RedRow();
                    break;
                case 7:
                    row.RedRow();
                    break;
            }
        }
    }

    public void fillWhiteBoard(){
        for( Row row: gameBoard){
            switch (row.getIndex()){
                case 0:
                    row.RedRow();
                    break;
                case 1:
                    row.RedRow();
                    break;
                case 2:
                    row.RedRow();
                    break;
                case 3:
                    row.validateRow();
                    break;
                case 4:
                    row.validateRow();
                    break;
                case 5:
                    row.WhiteRow();
                    break;
                case 6:
                    row.WhiteRow();
                    break;
                case 7:
                    row.WhiteRow();
                    break;
            }
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
