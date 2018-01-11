package com.thoughtworks.mstorderservice.exception;

import org.springframework.http.HttpStatus;

public enum  ErrorCode {

    GoodOrdered(1001, "the good is ordered", HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus httpStatus;
    private Integer code;

    ErrorCode(Integer code, String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
