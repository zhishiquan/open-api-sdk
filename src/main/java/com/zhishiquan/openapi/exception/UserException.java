package com.zhishiquan.openapi.exception;


public class UserException extends Exception {

    private String code;
    private String message;

    public UserException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public UserException(String message) {
        this.message = message;
    }

    public UserException() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
