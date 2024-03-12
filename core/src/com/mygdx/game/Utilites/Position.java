package com.mygdx.game.Utilites;

import java.util.ArrayList;
import java.util.List;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) { this.x = x; }

    public int getY() {
        return y;
    }

    public void setY(int y) { this.y = y; }
        public List<Integer> getCoords(int x, int y) {
        List<Integer> pos = new ArrayList<>();
        pos.add(x);
        pos.add(y);
        return pos;
    }

    public Double getDistance (BaseChar target) {
        double targetDistance = 0;
        targetDistance = Math.sqrt((Math.pow(target.position.getX() - this.getX(), 2)) +
                (Math.pow(target.position.getY() - this.getY(), 2)));
        return targetDistance;
    }
    public Position getDiff (Position targetPos){
        return new Position(x - targetPos.getX(), y - targetPos.getY());
    }

    public boolean equals(Position target) {
        return x == target.x && y == target.y;
    }
}
