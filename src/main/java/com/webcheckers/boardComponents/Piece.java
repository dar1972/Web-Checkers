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

    /**
     * Constructor for a new piece
     * @param type the type of piece (KING/SINGLE)
     * @param color the color of the piece (RED/WHITE)
     * @param cellIdx the location of the piece
     */
    public Piece(String type,String color, int cellIdx){
        super(cellIdx, color);
        this.type = type;
        this.color = color;
    }

    /**
     * Gets the type piece is (KING/SINGLE)
     * @return the type
     */
    public Type getType(){
        if(type.equals("SINGLE")){
            return Type.SINGLE;
        }
        else {
            return Type.KING;
        }
    }

    /**
     * Gets the color of the piece (RED/WHITE)
     * @return the color
     */
    public Color getColor(){
        if(color.equals("RED")){
            return Color.RED;
        }
        else {
            return Color.WHITE;
        }
    }
}
