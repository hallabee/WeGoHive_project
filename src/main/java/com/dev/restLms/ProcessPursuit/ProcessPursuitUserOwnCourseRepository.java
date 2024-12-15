package com.dev.restLms.ProcessPursuit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.Entity.UserOwnCourse;

import java.util.List;


@Repository
public interface ProcessPursuitUserOwnCourseRepository extends JpaRepository<UserOwnCourse, String>{
    List<ProcessPursuitUserOwnCourse> findByCourseId(String courseId);
}
