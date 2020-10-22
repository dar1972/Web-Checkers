package com.webcheckers.boardComponents;

import com.webcheckers.boardComponents.Piece.Color;
import com.webcheckers.model.Move;
import com.webcheckers.model.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("serial")
public class BoardView implements Iterable<Row>, Serializable{
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

    public boolean colorCleared() {
        boolean redPieceExists = false;
        boolean whitePieceExists = false;
        for( Row row: gameBoard){
            for (Space space : row) {
                if (space.getPiece() != null) {
                    if (space.getPiece().getColor() == Color.RED) {
                        redPieceExists = true;
                    }
                    else {
                        whitePieceExists = true;
                    }
                }
            }
        }

        if (redPieceExists && whitePieceExists) {
            return false;
        }
        else {
            return true;
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

        if (endRow.getIndex() == 0 || endRow.getIndex() == 7) { //Kingify if at opposite end of board.
            type = Piece.Type.KING.toString();
        }
        endSpace.createPiece(type,color);

        if (Math.abs(startSpace.getCellIdx() - endSpace.getCellIdx()) == 2) { //if a piece capture jump occured
            Position capturedPos = new Position((startRow.getIndex() + endRow.getIndex())/2, (startSpace.getCellIdx() + endSpace.getCellIdx())/2);
            Row capturedRow = gameBoard[capturedPos.getRow()];
            Space capturedSpace = capturedRow.row[capturedPos.getCell()];
            capturedSpace.deletePiece();
        }


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

    public boolean validMove(Move move){
        Position end = move.getEnd();

        ArrayList<Position> goodMoves = validMoves(move);
        for(int i=0; i<goodMoves.size();i++){
            Position check = goodMoves.get(i);
            if (end.getRow() == check.getRow() && end.getCell() == check.getCell()) {
                return true;
            }
        } return false;
    }


    private Position validMovesHelper(Move move, Position possibility, String direction){
        //boolean goodMove = false;
        Position start = move.getStart();
        Row startRow = gameBoard[start.getRow()];
        Space startSpace = startRow.row[start.getCell()];

        Row possibilityRow = gameBoard[possibility.getRow()];
        Space possibilitySpace = possibilityRow.row[possibility.getCell()];

        if (possibilitySpace.getPiece() != null) {
            if (possibilitySpace.getPiece().getColor() != startSpace.getPiece().getColor()) {
                Position possibility3 = null;
                switch (direction) {
                    case "UR":
                        if (possibility.getCell() < 7 && possibility.getRow() >= 1) {
                            possibility3 = new Position(possibility.getRow() - 1, possibility.getCell() + 1);
                        }
                        break;
                    case "UL":
                        if (possibility.getCell() >= 1 && possibility.getRow() >= 1) {
                            possibility3 = new Position(possibility.getRow() - 1, possibility.getCell() - 1);
                        }
                        break;
                    case "DL":
                        if (possibility.getCell() >= 1 && possibility.getRow() < 7) {
                            possibility3 = new Position(possibility.getRow() + 1, possibility.getCell() - 1);
                        }
                        break;
                    case "DR":
                        if (possibility.getCell() < 7 && possibility.getRow() < 7) {
                            possibility3 = new Position(possibility.getRow() + 1, possibility.getCell() + 1);
                        }
                        break;
                }

                if (possibility3 == null) {
                    return null;
                }
                Row possibility3Row = gameBoard[possibility3.getRow()];
                Space possibility3Space = possibility3Row.row[possibility3.getCell()];

                if (possibility3Space.getPiece() == null) {
                    return possibility3;
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        }else{
            return possibility;
        }
    }

    private ArrayList<Position> validMoves(Move move) {

        ArrayList<Position> goodMoves = new ArrayList<>();
        Position start = move.getStart();
        //Position end = move.getEnd();

        Row startRow = gameBoard[start.getRow()];
        Space startSpace = startRow.row[start.getCell()];

        //Row endRow = gameBoard[end.getRow()];
        //Space endSpace = endRow.row[end.getCell()];


        Piece piece = startSpace.getPiece();

        if (piece.getType() == Piece.Type.SINGLE){
            Position possibility1 = new Position(start.getRow()-1, start.getCell()+1);
            Position possibility2 = new Position(start.getRow()-1, start.getCell()-1);

            if(possibility1.getCell()<8) {
                Position goodMove = validMovesHelper(move, possibility1, "UR");
                if(goodMove != null) { //if move valid
                    goodMoves.add(goodMove);
                }

            }
            if(possibility2.getCell()>=0) {
                Position goodMove = validMovesHelper(move, possibility2, "UL");
                if(goodMove != null) {
                    goodMoves.add(goodMove);
                }

            }
        }
        if (piece.getType() == Piece.Type.KING){
            Position possibility1 = new Position(start.getRow()-1, start.getCell()+1);
            Position possibility2 = new Position(start.getRow()-1, start.getCell()-1);
            Position possibility3 = new Position(start.getRow()+1, start.getCell()-1);
            Position possibility4 = new Position(start.getRow()+1, start.getCell()+1);

            if(possibility1.getCell() < 8 && possibility1.getRow() >= 0) {
                Position goodMove = validMovesHelper(move, possibility1, "UR");
                if(goodMove != null) {
                    goodMoves.add(goodMove);
                }
            }
            if(possibility2.getCell() >= 0 && possibility2.getRow() >= 0) {
                Position goodMove = validMovesHelper(move, possibility2, "UL");
                if(goodMove != null) {
                    goodMoves.add(goodMove);
                }

            }
            if(possibility3.getCell() >= 0 && possibility3.getRow() < 8) {
                Position goodMove = validMovesHelper(move, possibility3, "DL");
                if(goodMove != null) {
                    goodMoves.add(goodMove);
                }
            }
            if(possibility4.getCell() < 8 && possibility4.getRow() < 8) {
                Position goodMove = validMovesHelper(move, possibility4, "DR");
                if(goodMove != null) {
                    goodMoves.add(goodMove);
                }
            }
        }

        return goodMoves;

    }

    @Override
    public Iterator<Row> iterator() {
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
