package com.iitbombay.courses_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class CourseInstanceDto {
    @NotNull(message = "Year is required")
    @Min(value = 2020, message = "Year must be at least 2020")
    @Max(value = 2030, message = "Year must not exceed 2030")
    private Integer year;
    
    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be 1 or 2")
    @Max(value = 2, message = "Semester must be 1 or 2")
    private Integer semester;
    
    @NotBlank(message = "Course ID is required")
    private String courseId;
    
    // Constructors
    public CourseInstanceDto() {}
    
    public CourseInstanceDto(Integer year, Integer semester, String courseId) {
        this.year = year;
        this.semester = semester;
        this.courseId = courseId;
    }
    
    // Getters and Setters
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    
    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }
    
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
}