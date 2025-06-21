package com.iitbombay.courses_api.exception;

public class CourseInstanceNotFoundException extends RuntimeException {
    public CourseInstanceNotFoundException(String message) {
        super(message);
    }
}
