package com.webcheckers.boardComponents;

public class Piece extends Space{
    //Created by Beck Anderson

    //Types of pieces
    private enum Type{
        SINGLE,
        KING}

    //Colors of pieces
    private enum Color{
        RED,
        WHITE}

    private final String type;
    private final String color;

    public Piece(String type,String color, int cellIdx){
        super(cellIdx);
        this.type = type;
        this.color = color;
    }

    public Type getType(){
        if(type.equals("SINGLE")){
            return Type.SINGLE;
        }
        else {
            return Type.KING;
        }
    }

    public Color getColor(){
        if(color.equals("RED")){
            return Color.RED;
        }
        else {
            return Color.WHITE;
        }
    }
}
