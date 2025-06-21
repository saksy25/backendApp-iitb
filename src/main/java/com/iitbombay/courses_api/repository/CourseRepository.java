package com.iitbombay.courses_api.repository;

import com.iitbombay.courses_api.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseId(String courseId);
    boolean existsByCourseId(String courseId);
    
    @Query("SELECT c FROM Course c JOIN c.prerequisites p WHERE p = :courseId")
    List<Course> findCoursesWithPrerequisite(@Param("courseId") String courseId);
}