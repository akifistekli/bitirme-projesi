package com.mobilapi.exception;


public class AppExceptions extends RuntimeException {

    public AppExceptions(String message, Throwable throwable) {
        super(message, throwable);
    }
}
