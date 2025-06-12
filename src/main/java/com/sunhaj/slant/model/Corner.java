package com.sunhaj.slant.model;

import lombok.Data;

import java.util.List;

@Data
public class Corner {
    private final int x;
    private final int y;
    private final int value;

    public Corner(int x, int y) {
        this(x, y, -1);
    }

    public Corner(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
