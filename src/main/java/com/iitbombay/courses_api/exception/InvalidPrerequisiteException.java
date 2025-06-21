package com.iitbombay.courses_api.exception;

public class InvalidPrerequisiteException extends RuntimeException {
    public InvalidPrerequisiteException(String message) {
        super(message);
    }
}