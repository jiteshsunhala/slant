package com.sunhaj.slant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Board {
    private ArrayList<ArrayList<CellValue>> cells;
    private int r, c;

    private final Map<String, Integer> corners;

    public Board(int r, int c, List<Corner> corners) {
        this.r = r;
        this.c = c;
        this.corners = corners.stream()
                .collect(Collectors.toMap(this::getKey, Corner::value));
        this.cells = new ArrayList<>();

        for(int i=0;i<r;i++) {
            ArrayList<CellValue> rowCells = new ArrayList<>();
            for(int j=0;j<c;j++) {
                rowCells.add(CellValue.none);
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

    public void print() {
        for(int i=0;i<=r;i++) {
            for(int j=0;j<=c;j++) {
                Integer corner = corners.get(getKey(i, j));
                String cornerValue = corner == null ? "+" : String.valueOf(corner);
                if(j != c) {
                    cornerValue += "-";
                }
                System.out.print(cornerValue);
            }
            if(i != r) {
                System.out.println();
                for (int l = 0; l <= c; l++) {
                    if(l != c) {
                        System.out.print("|" + cells.get(i).get(l).getValue());
                    } else {
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
        }
    }

    public void solvePairOfOnes() {
    }
}