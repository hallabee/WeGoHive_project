package com.dev.restLms.ProcessPursuit.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessPursuit.model.ProcessPursuitUserOwnCourse;
import com.dev.restLms.model.UserOwnCourse;

import java.util.List;


@Repository
public interface ProcessPursuitUserOwnCourseRepository extends JpaRepository<UserOwnCourse, String>{
    List<ProcessPursuitUserOwnCourse> findByCourseId(String courseId);
}
