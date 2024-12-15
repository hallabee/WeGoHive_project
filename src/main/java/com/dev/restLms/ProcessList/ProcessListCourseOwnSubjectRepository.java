package com.dev.restLms.ProcessList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.Entity.CourseOwnSubject;

import java.util.List;


@Repository
public interface ProcessListCourseOwnSubjectRepository extends JpaRepository<CourseOwnSubject, Object> {
    List<ProcessListCourseOwnSubject> findByCourseIdAndOfficerSessionId(String courseId, String officerSessionId);
    List<ProcessListCourseOwnSubject> findByCourseId(String courseId);
}
