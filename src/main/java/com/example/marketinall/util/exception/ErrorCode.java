package com.example.marketinall.util.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // Member
    DUPLICATE_EMAIL("email", "duplicate-email", "이미 존재하는 이메일입니다.", HttpStatus.BAD_REQUEST),  //400
    DUPLICATE_NICKNAME("nickname", "duplicate-nickname", "이미 존재하는 닉네임입니다.", HttpStatus.BAD_REQUEST), //400
    INVAILD_PASSWORD("password", "invalid-password", "유효하지 않은 비밀번호입니다.", HttpStatus.UNAUTHORIZED),
    NOT_FOUND_EMAIL("email", "not_found_email", "올바르지 않은 이메일입니다.", HttpStatus.NOT_FOUND),
    // item
    NOT_FOUND_ID("id","not_found_id","해당 아이템을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DENY_EDIT_ACCESS("member", "deny_edit_access", "수정 권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    DENY_DELETE_ACCESS("member", "deny_delete_access", "삭제 권한이 없습니다.", HttpStatus.UNAUTHORIZED)
    ;

    private final String cause;
    private final String code;
    private final String description;
    private final HttpStatus status;

    ErrorCode(String cause, String code, String description, HttpStatus status) {
        this.cause = cause;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public String getCause() {
        return cause;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
