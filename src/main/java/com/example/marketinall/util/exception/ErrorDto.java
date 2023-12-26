package com.example.marketinall.util.exception;

import com.example.marketinall.util.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorDto {

    private String cause;
    private String code;
    private String description;
    private HttpStatus status;

    @Builder
    public ErrorDto(String cause, String code, String description, HttpStatus status) {
        this.cause = cause;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public ErrorDto(ErrorCode errorCode) {
        this.cause = cause;
        this.code = code;
        this.description = description;
        this.status = status;
    }
}
