package com.webcheckers.model;

// Created by Dhruv
public class Move {
    private Position start, end;

    public Move(Position startPos, Position endPos) {
        this.start = startPos;
        this.end = endPos;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public Move invertMove(){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Move)){
            return false;
        }
        else {
            Move that = (Move)o;
            return that.start.equals(this.start) && that.end.equals(this.end);
        }
    }
}
