package com.niklamix.graduateworkrest.exception.handing;

public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String message) {
        super(message);
    }
}
