package com.app.dl.networklib.server.exception;

public class ApiException extends RuntimeException {
    private String errorCode;

    public ApiException(String code, String msg) {
        super(msg);
        this.errorCode = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

}