package com.cla.assignment.demo.exception;

public class NotFoundException extends RuntimeException{

    private String msg;

    public NotFoundException() {
        this.msg = "Employee does not exist";
    }

    public NotFoundException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
