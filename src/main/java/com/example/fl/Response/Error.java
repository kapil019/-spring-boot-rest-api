package com.example.fl.Response;

public class Error {

    private Integer code;

    private String message;

    public Error(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
