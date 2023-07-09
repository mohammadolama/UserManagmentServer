package com.example.usermanagmentserver.exception;

public class UpdateFailedException extends AbstractException {


    public UpdateFailedException(String message) {
        super(message);
    }

    public UpdateFailedException(Throwable cause, String message) {
        super(cause, message);
    }
}
