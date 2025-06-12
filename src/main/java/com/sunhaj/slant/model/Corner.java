package com.sunhaj.slant.model;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class Corner {
    private final int x;
    private final int y;
    private final Integer value;

    public Corner(int x, int y, Integer value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Cell getTopLeft() {
        return new Cell(x-1, y-1, CellValue.left, CellValue.right);
    }

    public Cell getTopRight() {
        return new Cell(x-1, y, CellValue.right, CellValue.left);
    }

    public Cell getBottomLeft() {
        return new Cell(x, y-1, CellValue.right, CellValue.left);
    }

    public Cell getBottomRight() {
        return new Cell(x, y, CellValue.left, CellValue.right);
    }

    public List<Cell> getAllCells() {
        return List.of(getTopLeft(), getTopRight(), getBottomLeft(), getBottomRight());
    }

    @Data
    public static class Cell {
        private final int x;
        private final int y;
        private final CellValue incoming;
        private final CellValue away;

        public Cell(int x, int y, CellValue incoming, CellValue away) {
            this.x = x;
            this.y = y;
            this.incoming = incoming;
            this.away = away;
        }
    }

    public enum Direction {
        left(0, -1),
        right(0, 1),
        top(-1, 0),
        bottom(1, 0),
        bottomLeft(bottom.x + left.x, bottom.y + left.y),
        bottomRight(bottom.x + right.x, bottom.y + right.y),
        topLeft(top.x + left.x, top.y + left.y),
        topRight(top.x + right.x, top.y + right.y);

        @Getter
        private final int x;

        @Getter
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
