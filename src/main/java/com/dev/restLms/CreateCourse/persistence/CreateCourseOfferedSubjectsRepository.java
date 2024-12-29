package com.dev.restLms.CreateCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.OfferedSubjects;

public interface CreateCourseOfferedSubjectsRepository extends JpaRepository<OfferedSubjects, String> {
    
}
