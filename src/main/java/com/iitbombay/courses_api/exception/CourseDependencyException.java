package com.iitbombay.courses_api.exception;

public class CourseDependencyException extends RuntimeException {
    public CourseDependencyException(String message) {
        super(message);
    }
}