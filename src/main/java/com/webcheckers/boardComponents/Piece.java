package com.webcheckers.boardComponents;

public class Piece{
    //Created by Beck Anderson

    //Types of pieces
    enum Type{
        SINGLE,
        KING}

    //Colors of pieces
    enum Color{
        RED,
        WHITE}

    private final Type type;
    private final Color color;

    /**
     * Constructor for a new piece
     * @param type the type of piece (KING/SINGLE)
     * @param color the color of the piece (RED/WHITE)
     * @param cellIdx the location of the piece
     */
    public Piece(String type,String color, int cellIdx){
        if(type.equals("s")){
            this.type = Type.SINGLE;
        }else{
            this.type = Type.KING;
        }if(color.equals("r")){
            this.color = Color.RED;
        }else{
            this.color = Color.WHITE;
        }
    }

    /**
     * Gets the type piece is (KING/SINGLE)
     * @return the type
     */
    public Type getType(){
        if(type.equals(Type.SINGLE)){
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
        if(color.equals(Color.RED)){
            return Color.RED;
        }
        else {
            return Color.WHITE;
        }
    }
}
