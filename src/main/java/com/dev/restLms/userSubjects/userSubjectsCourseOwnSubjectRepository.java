package com.dev.restLms.userSubjects;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.Entity.CourseOwnSubject;

import java.util.Optional;


public interface userSubjectsCourseOwnSubjectRepository extends JpaRepository<CourseOwnSubject, Object> {
    Optional<userSubjectsCourseOwnSubject> findBySubjectId(String subjectId);
}
