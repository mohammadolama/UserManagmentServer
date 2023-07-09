package com.example.usermanagmentserver.exception;

public class CreationFailedException extends AbstractException{

    public CreationFailedException(String s) {
        super("Your Create Request Failed: " +s);
    }
}
