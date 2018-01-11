package com.thoughtworks.mstorderservice.exception;

import org.springframework.http.HttpStatus;

public class MstException extends RuntimeException {

    private ErrorCode errorCode;

    public MstException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return this.errorCode.getHttpStatus();
    }

    public Integer getCode() {
        return this.errorCode.getCode();
    }
}
