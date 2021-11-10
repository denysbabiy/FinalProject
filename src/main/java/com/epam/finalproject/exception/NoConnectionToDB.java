package com.epam.finalproject.exception;

public class NoConnectionToDB extends RuntimeException {
    public NoConnectionToDB() {
        super();
    }

    public NoConnectionToDB(String message) {
        super(message);
    }

    public NoConnectionToDB(String message, Throwable cause) {
        super(message, cause);
    }
}
