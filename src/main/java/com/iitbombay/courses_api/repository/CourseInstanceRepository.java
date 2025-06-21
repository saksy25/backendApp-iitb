package com.iitbombay.courses_api.repository;

import com.iitbombay.courses_api.entity.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
    List<CourseInstance> findByYearAndSemester(Integer year, Integer semester);
    Optional<CourseInstance> findByYearAndSemesterAndCourseCourseId(Integer year, Integer semester, String courseId);
}