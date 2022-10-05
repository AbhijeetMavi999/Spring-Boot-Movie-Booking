package com.moviebooking.exceptions;

public class ResourceAlreadyTakenException extends Exception {
    public ResourceAlreadyTakenException() {
        super();
    }

    public ResourceAlreadyTakenException(String message) {
        super(message);
    }

    public ResourceAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyTakenException(Throwable cause) {
        super(cause);
    }

    protected ResourceAlreadyTakenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
