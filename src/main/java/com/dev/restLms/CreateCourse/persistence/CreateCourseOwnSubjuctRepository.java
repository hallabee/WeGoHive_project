package com.dev.restLms.CreateCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.CourseOwnSubject;

public interface CreateCourseOwnSubjuctRepository extends JpaRepository<CourseOwnSubject, String> {
    
}
