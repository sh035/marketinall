package com.example.marketinall.domain.enums;

public enum ItemStatus {
    SELL("판매중"), SOLD_OUT("판매완료");

    private String value;

    public String getValue() {
        return value;
    }

    ItemStatus(String value) {
        this.value = value;
    }
}
