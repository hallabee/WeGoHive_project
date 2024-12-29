package com.dev.restLms.deleteCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.UserOwnCourse;
import java.util.List;


public interface DeleteCourseUserOwnCourseRepository extends JpaRepository<UserOwnCourse, String> {
    List<UserOwnCourse> findByCourseIdAndOfficerSessionId(String courseId, String officerSessionId);
    
    boolean existsByCourseIdAndOfficerSessionId(String courseId, String officerSessionId);

    void deleteByCourseId(String courseId);
}
