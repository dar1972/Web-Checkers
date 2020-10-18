package com.webcheckers.model;

// Created by Dhruv
public class Move {
    private Position startPos, endPos;

    public Move(Position startPos, Position endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public Position getStartPos() {
        return startPos;
    }

    public Position getEndPos() {
        return endPos;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Move)){
            return false;
        }
        else {
            Move that = (Move)o;
            return that.startPos.equals(this.startPos) && that.endPos.equals(this.endPos);
        }
    }
}
