package com.dev.restLms.ProcessList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListCourseOwnSubject;
import java.util.List;


@Repository
public interface ProcessListCourseOwnSubjectRepository extends JpaRepository<ProcessListCourseOwnSubject, String> {
    List<ProcessListCourseOwnSubject> findByCourseIdAndOfficerSessionId(String courseId, String officerSessionId);
}
