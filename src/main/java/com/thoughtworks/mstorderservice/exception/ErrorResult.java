package com.thoughtworks.mstorderservice.exception;

import lombok.Data;

@Data
public class ErrorResult {

    private Integer errorCode;
    private String message;

    public ErrorResult(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
