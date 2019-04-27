package com.zhishiquan.openapi.exception;


public class SystemException extends RuntimeException {

    private String code;
    private String message;

    public SystemException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public SystemException(String message) {
        this.message = message;
    }

    public SystemException() {
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
