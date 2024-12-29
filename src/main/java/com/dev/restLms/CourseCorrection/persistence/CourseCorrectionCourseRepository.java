package com.dev.restLms.CourseCorrection.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.CourseCorrection.projection.CourseCorrectionCourse;
import com.dev.restLms.entity.Course;


public interface CourseCorrectionCourseRepository extends JpaRepository<Course, String> {
    Page<CourseCorrectionCourse> findBySessionId(String sessionId, Pageable pageable);
    Page<CourseCorrectionCourse> findBySessionIdAndCourseTitleContaining(String sessionId, String courseTitle, Pageable pageable);
}
