package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for ResourceNotFoundException
     * @param message The exception message to describe the missing resource
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
