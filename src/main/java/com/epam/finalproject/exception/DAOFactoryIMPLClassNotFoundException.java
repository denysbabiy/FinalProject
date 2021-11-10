package com.epam.finalproject.exception;

public class DAOFactoryIMPLClassNotFoundException extends RuntimeException{
    public DAOFactoryIMPLClassNotFoundException() {
        super();
    }

    public DAOFactoryIMPLClassNotFoundException(String message) {
        super(message);
    }

    public DAOFactoryIMPLClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
