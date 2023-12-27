package com.glsi.xpress.Exceptions;

public class EmailAlreadyInUse extends RuntimeException{
    public EmailAlreadyInUse(String message) {
        super(message);
    }

    public EmailAlreadyInUse(){
        super("Email already taken!");
    }
}
