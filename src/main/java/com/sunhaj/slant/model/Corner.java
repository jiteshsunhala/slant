package com.sunhaj.slant.model;

public record Corner(int x, int y, int value, boolean isOnEdge) implements Comparable<Corner> {
    public Corner(int x, int y, boolean isOnEdge) {
        this(x, y, -1, isOnEdge);
    }

    @Override
    public int compareTo(Corner that) {
        if(this.x == that.x) {
            return this.y - that.y;
        }
        return this.x - that.x;
    }
}
