package com.diet.backend.exception;

public class UnAuthorizeException extends RuntimeException{
    public UnAuthorizeException(String message){
        super(message);
    }
}
