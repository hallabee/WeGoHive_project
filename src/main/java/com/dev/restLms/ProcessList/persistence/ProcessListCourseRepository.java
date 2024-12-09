package com.dev.restLms.ProcessList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListCourse;
import java.util.List;


@Repository
public interface ProcessListCourseRepository extends JpaRepository<ProcessListCourse, String> {
    List<ProcessListCourse> findByCourseId(String courseId);
}
