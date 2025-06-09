package com.sunhaj.slant.model;

public enum CellValue {
    left("\\"), right("/"), none(" ");

    private final String value;

    CellValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
