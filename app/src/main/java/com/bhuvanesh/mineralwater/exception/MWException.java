package com.bhuvanesh.mineralwater.exception;

/**
 * Created by bhuvanesh on 01-02-2017.
 */

public class MWException extends Exception {

    public final static int ERROR_NETWORK_NOT_FOUND = 404;
    private int code;
    private String message;

    public MWException(int code) {
        this(code, "Something wrong!!!.Please try after sometime.");
    }

    public MWException(String message) {
        this(-1, message);
    }

    public MWException(int code, String message) {
        this.code = code;
        this.message = message != null ? message : this.message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
