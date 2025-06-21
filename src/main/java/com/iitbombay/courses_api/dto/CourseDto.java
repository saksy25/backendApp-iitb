package com.iitbombay.courses_api.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class CourseDto {
    @NotBlank(message = "Course ID is required")
    private String courseId;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    private List<String> prerequisites;
    
    // Constructors
    public CourseDto() {}
    
    public CourseDto(String courseId, String title, String description, List<String> prerequisites) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.prerequisites = prerequisites;
    }
    
    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<String> getPrerequisites() { return prerequisites; }
    public void setPrerequisites(List<String> prerequisites) { this.prerequisites = prerequisites; }
}
