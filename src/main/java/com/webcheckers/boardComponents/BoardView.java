package com.webcheckers.boardComponents;

import com.webcheckers.model.Move;
import com.webcheckers.model.Position;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoardView implements Iterable{
    //Created by Beck Anderson

    public Row[] gameBoard;
    private final int ROWS = 8;


    /**
     * Creates a new board
     * @param color the color of the player who will see the board
     */
    public BoardView(String color){
        gameBoard = new Row[8];
        Row row;
        String pieceColor = "q";
        for (int i = 0; i < ROWS; i ++){
            //for rows 0,1,and 2, white pieces if white player, red if red
            if(i==0||i==1||i==2){
                if(color.equals("white")) {
                    pieceColor = "r";
                }else{
                    pieceColor = "w";
                }
            }
            //for rows 5,6, and 7 red pieces if white player, white if red
            else if(i==7||i==6||i==5){
                if(color.equals("white")) {
                    pieceColor = "w";
                }else{
                    pieceColor = "r";
                }
            }
            //for rows 4 and 5, no pieces
            else if(i==3){
                pieceColor = "q";
            }
            //for rows 0, 2, 4, and 6, the rows will start with white
            if (i==0||i==2||i==4||i==6) {
                row = new Row(i,"White",pieceColor);
            }
            //for rows 1, 3, 5, and 7, the rows will start with black
            else{
                row = new Row(i,"Black",pieceColor);
            }
            //store the row in the gameBoard
            gameBoard[i] = row;
        }
    }

    public synchronized void fillRedBoard(){
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

    public synchronized void fillWhiteBoard(){
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

    public void updateBoard(Move move){
        Position start = move.getStart();
        Position end = move.getEnd();

        Row startRow = gameBoard[start.getRow()];
        Space startSpace = startRow.row[start.getCell()];

        Row endRow = gameBoard[end.getRow()];
        Space endSpace = endRow.row[end.getCell()];

        Piece piece = startSpace.getPiece();
        startSpace.deletePiece();
        String type = piece.getType().toString();
        String color = piece.getColor().toString();
        endSpace.createPiece(type,color);
    }

    public boolean spaceCollision(Move move) {
        Position end = move.getEnd();

        Row endRow = gameBoard[end.getRow()];
        Space endSpace = endRow.row[end.getCell()];

        if (endSpace.getPiece() == null) {
            return false;
        }
        else {
            return true;
        }
        

    }


    public boolean validMoveDistance(Move move) {

        Position start = move.getStart();
        Position end = move.getEnd();

        Row startRow = gameBoard[start.getRow()];
        Space startSpace = startRow.row[start.getCell()];

        Row endRow = gameBoard[end.getRow()];
        Space endSpace = endRow.row[end.getCell()];


        Piece piece = startSpace.getPiece();

        if (piece.getType() == Piece.Type.SINGLE){
            Position possibility1 = new Position(start.getRow()+1, start.getCell()+1);
            Position possibility2 = new Position(start.getRow()+1, start.getCell()-1);

            Row possibility1Row = gameBoard[start.getRow()];
            Space possibility1Space = startRow.row[start.getCell()];


            if (possibility1Space.getPiece() != null) {
                if (possibility1Space.getPiece().getColor() != startSpace.getPiece().getColor()) {
                    
                }
            }

        }
        if (piece.getType() == Piece.Type.KING){
            Position possibility1 = new Position(start.getRow()+1, start.getCell()+1);
            Position possibility2 = new Position(start.getRow()+1, start.getCell()-1);
            Position possibility3 = new Position(start.getRow()-1, start.getCell()-1);
            Position possibility4 = new Position(start.getRow()-1, start.getCell()+1);


        }

        return true;

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
