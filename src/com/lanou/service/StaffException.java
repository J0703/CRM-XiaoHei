package com.lanou.service;

/**
 * Created by dllo on 17/10/27.
 */
public class StaffException extends Exception{

    public StaffException() {
        super();
    }

    public StaffException(String message) {
        super(message);
    }

    public StaffException(String message, Throwable cause) {
        super(message, cause);
    }

    public StaffException(Throwable cause) {
        super(cause);
    }

    protected StaffException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
