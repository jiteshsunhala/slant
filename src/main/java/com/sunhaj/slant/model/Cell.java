package com.sunhaj.slant.model;

public record Cell (Corner topLeft, Corner topRight, Corner bottomLeft, Corner bottomRight, CellValue cellValue) {

    public Cell(Corner topLeft, Corner topRight, Corner bottomLeft, Corner bottomRight) {
        this(topLeft, topRight, bottomLeft, bottomRight, null);
    }
}
