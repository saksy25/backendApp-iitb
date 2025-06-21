package com.iitbombay.courses_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "course_instances")
public class CourseInstance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Year is required")
    @Min(value = 2020, message = "Year must be at least 2020")
    @Max(value = 2030, message = "Year must not exceed 2030")
    private Integer year;
    
    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be 1 or 2")
    @Max(value = 2, message = "Semester must be 1 or 2")
    private Integer semester;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    // Constructors
    public CourseInstance() {}
    
    public CourseInstance(Integer year, Integer semester, Course course) {
        this.year = year;
        this.semester = semester;
        this.course = course;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    
    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }
    
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
