package com.sunhaj.slant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Board {
    private ArrayList<ArrayList<Cell>> cells;
    private int r, c;

    private Map<String, Corner> corners;

    public Board(int r, int c, List<Corner> corners) {
        this.r = r;
        this.c = c;
        this.corners = corners.stream()
                .collect(Collectors.toMap(this::getKey, Function.identity()));
        this.cells = new ArrayList<>();

        for(int i=0;i<r;i++) {
            ArrayList<Cell> rowCells = new ArrayList<>();
            for(int j=0;j<c;j++) {
                rowCells.add(getCell(i,j));
            }
            cells.add(rowCells);
        }
    }

    private String getKey(Corner corner) {
        return getKey(corner.x(), corner.y());
    }

    private String getKey(int x, int y) {
        return String.join("_", String.valueOf(x), String.valueOf(y));
    }

    private Cell getCell(int x, int y) {
        // corner positions for cell: (0, 0)
        // (0, 0), (0, 1), (1, 0), (1, 1)

        // corner positions for cell: (0, 4)
        // (0, 4), (0, 5), (1, 4), (1, 5)

        // corner positions for cell: (x, y)
        // (x, y), (x, y+1), (x+1, y), (x+1, y+1)

        Corner topLeft = corners.getOrDefault(getKey(x, y), new Corner(x, y));
        Corner topRight = corners.getOrDefault(getKey(x, y+1), new Corner(x, y+1));
        Corner bottomLeft = corners.getOrDefault(getKey(x+1, y), new Corner(x+1, y));
        Corner bottomRight = corners.getOrDefault(getKey(x+1, y+1), new Corner(x+1, y+1));

        return new Cell(topLeft, topRight, bottomLeft, bottomRight);
    }

    public void print() {
    }
}