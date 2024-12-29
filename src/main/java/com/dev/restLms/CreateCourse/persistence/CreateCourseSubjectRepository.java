package com.dev.restLms.CreateCourse.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.CreateCourse.projection.CreateCourseSubject;
import com.dev.restLms.entity.Subject;


public interface CreateCourseSubjectRepository extends JpaRepository<Subject, String> {
    Page<CreateCourseSubject> findBySubjectNameContaining(String subjectName, Pageable pageable);
}
