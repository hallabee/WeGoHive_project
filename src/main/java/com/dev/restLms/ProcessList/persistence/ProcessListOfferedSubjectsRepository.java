package com.dev.restLms.ProcessList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListOfferedSubjects;
import java.util.List;


@Repository
public interface ProcessListOfferedSubjectsRepository extends JpaRepository<ProcessListOfferedSubjects, String> {
    List<ProcessListOfferedSubjects> findByCourseIdAndOfficerSessionIdAndSubjectId(String courseId, String officerSessionId, String subjectId);
}
