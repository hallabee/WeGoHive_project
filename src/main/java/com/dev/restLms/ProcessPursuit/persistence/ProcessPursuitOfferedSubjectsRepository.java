package com.dev.restLms.ProcessPursuit.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessPursuit.model.ProcessPursuitOfferedSubjects;
import java.util.List;

@Repository
public interface ProcessPursuitOfferedSubjectsRepository extends JpaRepository<ProcessPursuitOfferedSubjects, String> {
    List<ProcessPursuitOfferedSubjects> findByCourseId(String courseId);
}
