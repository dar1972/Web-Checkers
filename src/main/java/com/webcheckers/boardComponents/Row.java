package com.webcheckers.boardComponents;

import java.util.Iterator;

public class Row implements Iterable {
    //Created by Beck Anderson

    private final int index;
    public Space[] row;

    /**
     * Create a new row
     * @param index the row
     * @param color the color space should be
     */
    public Row(int index, String color){
        this.index = index;
        Space space;
        for (int i = 0; i<8;i++) {
            int colorSet = 0;
            if (color.equals("White")) {
                space = new Space(i,"White");
                colorSet = 1;
            }else {
                space = new Space(i, "Black");
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

    @Override
    public Iterator iterator() {
        return null;
    }

    public int getIndex(){
        return index;
    }
}
