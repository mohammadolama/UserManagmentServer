package com.example.usermanagmentserver.exception;

public class EntityNotFoundException extends AbstractException{


    public EntityNotFoundException(String message) {
        super("Entity does not exists " +message);
    }

    public EntityNotFoundException(Throwable cause, String message) {
        super(cause, message);
    }
}
