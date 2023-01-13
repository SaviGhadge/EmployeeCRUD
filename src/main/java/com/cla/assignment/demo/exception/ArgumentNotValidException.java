package com.cla.assignment.demo.exception;

public class ArgumentNotValidException extends Exception {
    private String msg;

    public ArgumentNotValidException() {
    }

    public ArgumentNotValidException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
