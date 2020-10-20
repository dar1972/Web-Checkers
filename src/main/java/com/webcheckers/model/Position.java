package com.webcheckers.model;

// Created by Dhruv
public class Position {

    private int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Position{" + "row=" + row + ", cell=" + col + '}';
    }
}
