package com.dev.restLms.ProcessPursuit.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessPursuit.model.ProcessPursuitCourseOwnSubject;
import java.util.List;

@Repository
public interface ProcessPursuitCourseOwnSubjectRepository extends JpaRepository<ProcessPursuitCourseOwnSubject, String>{
    List<ProcessPursuitCourseOwnSubject> findByCourseId(String courseId);
}
