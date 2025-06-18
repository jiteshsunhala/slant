package com.sunhaj.slant.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

@Data
public class Board {
    private final ArrayList<ArrayList<CellValue>> cells;
    private final ArrayList<ArrayList<Corner>> corners;
    private final int r;
    private final int c;

    public Board(int r, int c, List<Corner> corners) {
        this.r = r;
        this.c = c;

        this.corners = new ArrayList<>();
        for(int i=0;i<=r;i++) {
            ArrayList<Corner> rowCorners = new ArrayList<>();
            for(int j=0;j<=c;j++) {
                rowCorners.add(new Corner(i, j, null));
            }
            this.corners.add(rowCorners);
        }

        corners.forEach(corner -> this.corners.get(corner.getX()).set(corner.getY(), corner));

        this.cells = new ArrayList<>();
        for(int i=0;i<r;i++) {
            ArrayList<CellValue> rowCells = new ArrayList<>();
            for(int j=0;j<c;j++) {
                rowCells.add(CellValue.none);
            }
            cells.add(rowCells);
        }
    }

    public void print() {
        System.out.println("Printing board..");
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                String cornerValue = Optional.ofNullable(corners.get(i).get(j).getValue()).map(String::valueOf).orElse("+");
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

        System.out.println();
    }

    public Optional<Corner> getNextCorner(Corner corner,
                                          int value,
                                          Corner.Direction cornerDirection,
                                          BiPredicate<Board, Corner> cornerCondition,
                                          List<Integer> skipValues) {
        int incX = cornerDirection.getX();
        int incY = cornerDirection.getY();

        for(int i=corner.getX() + incX, j=corner.getY() + incY;isValidCorner(i, j); i += incX, j += incY) {
            Corner nextCorner = corners.get(i).get(j);
            Integer cornerValue = nextCorner.getValue();

            if(cornerValue == null) {
                return Optional.empty();
            }

            if(value == cornerValue && cornerCondition.test(this, nextCorner)) {
                return Optional.of(corners.get(i).get(j));
            }

            if(!skipValues.contains(cornerValue)) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public CellValue getCellValue(Corner.Cell cell) {
        return getCellValue(cell.getX(), cell.getY());
    }

    public CellValue getCellValue(int x, int y) {
        if(!isValidCell(x, y)) {
            return CellValue.invalid;
        }
        return cells.get(x).get(y);
    }

    public void setCell(int x, int y, CellValue cellValue) {
        if(!isValidCell(x, y)) {
            return;
        }
        cells.get(x).set(y, cellValue);
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    public boolean isEdgedCorner(Corner corner) {
        return corner.getX() == 0 || corner.getX() == r || corner.getY() == 0 || corner.getY() == c;
    }

    public List<CellValue> getAllCellValues() {
        return cells.stream().flatMap(Collection::stream).toList();
    }

    public List<Corner> getAllCorners() {
        return getCorners().stream().flatMap(Collection::stream).toList();
    }

    private boolean isValidCorner(int x, int y) {
        return x >= 0 && x <= r && y >= 0 && y <= c;
    }

    public boolean isSolved() {
        return getAllCellValues().stream().noneMatch(CellValue.none::equals);
    }
}