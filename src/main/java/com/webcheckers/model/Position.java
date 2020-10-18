package com.webcheckers.model;

// Created by Dhruv
public class Position {

    private int row, cell;

    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }

    @Override
    public String toString() {
        return "Position{" + "row=" + row + ", cell=" + cell + '}';
    }
}
