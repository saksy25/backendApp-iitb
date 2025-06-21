package com.iitbombay.courses_api.controller;

import com.iitbombay.courses_api.dto.CourseDto;
import com.iitbombay.courses_api.dto.CourseInstanceDto;
import com.iitbombay.courses_api.entity.Course;
import com.iitbombay.courses_api.entity.CourseInstance;
import com.iitbombay.courses_api.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    // Course Endpoints
    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseDto courseDto) {
        Course course = courseService.createCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }
    
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable String courseId) {
        Course course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }
    
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
    
    // Course Instance Endpoints
    @PostMapping("/instances")
    public ResponseEntity<CourseInstance> createCourseInstance(@Valid @RequestBody CourseInstanceDto instanceDto) {
        CourseInstance instance = courseService.createCourseInstance(instanceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(instance);
    }
    
    @GetMapping("/instances/{year}/{semester}")
    public ResponseEntity<List<CourseInstance>> getCourseInstances(
            @PathVariable Integer year, 
            @PathVariable Integer semester) {
        List<CourseInstance> instances = courseService.getCourseInstancesByYearAndSemester(year, semester);
        return ResponseEntity.ok(instances);
    }
    
    @GetMapping("/instances/{year}/{semester}/{courseId}")
    public ResponseEntity<CourseInstance> getCourseInstance(
            @PathVariable Integer year,
            @PathVariable Integer semester,
            @PathVariable String courseId) {
        CourseInstance instance = courseService.getCourseInstance(year, semester, courseId);
        return ResponseEntity.ok(instance);
    }
    
    @DeleteMapping("/instances/{year}/{semester}/{courseId}")
    public ResponseEntity<Void> deleteCourseInstance(
            @PathVariable Integer year,
            @PathVariable Integer semester,
            @PathVariable String courseId) {
        courseService.deleteCourseInstance(year, semester, courseId);
        return ResponseEntity.noContent().build();
    }
}