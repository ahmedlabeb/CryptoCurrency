package com.bitoasis.cryptodemo.api.boundary.helper.dto;

public enum OrderByEnum {

    ASC("ASC"),
    DESC("DESC");

    private String value;

    OrderByEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
