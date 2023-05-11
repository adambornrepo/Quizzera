package com.abtech.exception;

public class UniqueValueAlreadyExistException extends RuntimeException{
    public UniqueValueAlreadyExistException(String message) {
        super(message);
    }
}
