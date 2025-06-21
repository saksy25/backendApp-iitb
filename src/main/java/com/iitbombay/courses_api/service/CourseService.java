package com.iitbombay.courses_api.service;

import com.iitbombay.courses_api.dto.CourseDto;
import com.iitbombay.courses_api.dto.CourseInstanceDto;
import com.iitbombay.courses_api.entity.Course;
import com.iitbombay.courses_api.entity.CourseInstance;
import com.iitbombay.courses_api.exception.*;
import com.iitbombay.courses_api.repository.CourseRepository;
import com.iitbombay.courses_api.repository.CourseInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private CourseInstanceRepository courseInstanceRepository;
    
    // Course CRUD Operations
    public Course createCourse(CourseDto courseDto) {
        // Check if course already exists
        if (courseRepository.existsByCourseId(courseDto.getCourseId())) {
            throw new InvalidPrerequisiteException("Course with ID " + courseDto.getCourseId() + " already exists");
        }
        
        // Validate prerequisites exist
        if (courseDto.getPrerequisites() != null && !courseDto.getPrerequisites().isEmpty()) {
            for (String prereqId : courseDto.getPrerequisites()) {
                if (!courseRepository.existsByCourseId(prereqId)) {
                    throw new InvalidPrerequisiteException("Prerequisite course with ID " + prereqId + " does not exist");
                }
            }
        }
        
        Course course = new Course(
            courseDto.getCourseId(),
            courseDto.getTitle(),
            courseDto.getDescription(),
            courseDto.getPrerequisites()
        );
        
        return courseRepository.save(course);
    }
    
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    public Course getCourseById(String courseId) {
        return courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + courseId + " not found"));
    }
    
    public void deleteCourse(String courseId) {
        Course course = getCourseById(courseId);
        
        // Check if course is a prerequisite for other courses
        List<Course> dependentCourses = courseRepository.findCoursesWithPrerequisite(courseId);
        if (!dependentCourses.isEmpty()) {
            throw new CourseDependencyException(
                "Course cannot be deleted as it is a prerequisite for other courses: " + 
                dependentCourses.stream().map(Course::getCourseId).toList()
            );
        }
        
        courseRepository.delete(course);
    }
    
    // Course Instance Operations
    public CourseInstance createCourseInstance(CourseInstanceDto instanceDto) {
        Course course = getCourseById(instanceDto.getCourseId());
        
        // Check if instance already exists
        Optional<CourseInstance> existingInstance = courseInstanceRepository
                .findByYearAndSemesterAndCourseCourseId(
                    instanceDto.getYear(), 
                    instanceDto.getSemester(), 
                    instanceDto.getCourseId()
                );
        
        if (existingInstance.isPresent()) {
            throw new InvalidPrerequisiteException(
                "Course instance already exists for " + instanceDto.getCourseId() + 
                " in " + instanceDto.getYear() + " semester " + instanceDto.getSemester()
            );
        }
        
        CourseInstance instance = new CourseInstance(
            instanceDto.getYear(),
            instanceDto.getSemester(),
            course
        );
        
        return courseInstanceRepository.save(instance);
    }
    
    public List<CourseInstance> getCourseInstancesByYearAndSemester(Integer year, Integer semester) {
        return courseInstanceRepository.findByYearAndSemester(year, semester);
    }
    
    public CourseInstance getCourseInstance(Integer year, Integer semester, String courseId) {
        return courseInstanceRepository.findByYearAndSemesterAndCourseCourseId(year, semester, courseId)
                .orElseThrow(() -> new CourseInstanceNotFoundException(
                    "Course instance not found for course " + courseId + 
                    " in " + year + " semester " + semester
                ));
    }
    
    public void deleteCourseInstance(Integer year, Integer semester, String courseId) {
        CourseInstance instance = getCourseInstance(year, semester, courseId);
        courseInstanceRepository.delete(instance);
    }
}
