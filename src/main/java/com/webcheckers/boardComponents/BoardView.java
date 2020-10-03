package com.webcheckers.boardComponents;

import java.util.Iterator;

public class BoardView implements Iterable{
    //Created by Beck Anderson

    public Row[] gameBoard;

    public BoardView(){
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
        return null;
    }


}
