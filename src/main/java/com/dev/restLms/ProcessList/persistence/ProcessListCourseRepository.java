package com.dev.restLms.ProcessList.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListCourse;
import com.dev.restLms.model.Course;

@Repository
public interface ProcessListCourseRepository extends JpaRepository<Course,String> {
    List<ProcessListCourse> findBycourseId(String courseId);
}
