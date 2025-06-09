package com.sunhaj.slant.model;

public record Corner(int x, int y, int value) {
    public Corner(int x, int y) {
        this(x, y, -1);
    }
}
