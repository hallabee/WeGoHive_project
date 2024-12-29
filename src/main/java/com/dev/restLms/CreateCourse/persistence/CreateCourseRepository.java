package com.dev.restLms.CreateCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.Course;

public interface CreateCourseRepository extends JpaRepository<Course, String> {
    
}
