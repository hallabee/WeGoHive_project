package com.dev.restLms.ProcessList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListUserOwnCourse;

import java.util.List;


@Repository
public interface ProcessListUserOwnCourseRepository extends JpaRepository<ProcessListUserOwnCourse, String>{
    List<ProcessListUserOwnCourse> findByCourseId(String courseId);
    boolean existsByCourseIdAndSessionIdAndOfficerSessionId(String courseId, String sessionId, String officerSessionId);
}
