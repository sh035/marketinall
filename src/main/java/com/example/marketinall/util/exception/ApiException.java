package com.example.marketinall.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
