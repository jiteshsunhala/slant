package com.sunhaj.slant.model;

import lombok.Getter;

@Getter
public enum CellValue {
    backward("\\"), forward("/"), none(" "), invalid("~");

    private final String value;

    CellValue(String value) {
        this.value = value;
    }

}
