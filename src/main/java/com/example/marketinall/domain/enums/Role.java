package com.example.marketinall.domain.enums;

public enum Role {

    USER("일반 사용자"), ADMIN("관리자");

    private String value;

    public String getValue() {
        return value;
    }

    Role(String value) {
        this.value = value;
    }
}
