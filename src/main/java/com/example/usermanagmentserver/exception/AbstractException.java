package com.example.usermanagmentserver.exception;

public abstract class AbstractException extends Exception{
    protected String message;


    public AbstractException(String message) {
        super(message);
        this.message = message;
    }

    public AbstractException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
}
