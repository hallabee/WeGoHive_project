package com.dev.restLms.deleteCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.deleteCourse.projection.DeleteCourseOfferedSubjects;
import com.dev.restLms.entity.OfferedSubjects;
import java.util.Optional;


public interface DeleteCourseOfferedSubjectsRepository extends JpaRepository<OfferedSubjects, String> {
    
    Optional<DeleteCourseOfferedSubjects> findBySubjectIdAndCourseIdAndOfficerSessionId(String subjectId, String courseId, String officerSessionId);
    
}
