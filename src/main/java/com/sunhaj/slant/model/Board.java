package com.sunhaj.slant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Board {
    private final ArrayList<ArrayList<CellValue>> cells;
    private final int r;
    private final int c;

    private final Map<String, Corner> corners;

    public Board(int r, int c, List<Corner> corners) {
        this.r = r;
        this.c = c;
        this.corners = corners.stream()
                .collect(Collectors.toMap(this::getKey, Function.identity()));
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
        return getKey(corner.getX(), corner.getY());
    }

    private String getKey(int x, int y) {
        return String.join("_", String.valueOf(x), String.valueOf(y));
    }

    public void print() {
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                String cornerValue = Optional.ofNullable(corners.get(getKey(i, j))).map(corner -> String.valueOf(corner.getValue())).orElse("+");
                if (j != c) {
                    cornerValue += "-";
                }
                System.out.print(cornerValue);
            }
            if (i != r) {
                System.out.println();
                for (int l = 0; l <= c; l++) {
                    if (l != c) {
                        System.out.print("|" + cells.get(i).get(l).getValue());
                    } else {
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
        }
    }
}