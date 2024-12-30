package com.dev.restLms.CreateCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.CreateCourse.projection.CreateCourseOwnSubjuct;
import com.dev.restLms.entity.CourseOwnSubject;
import java.util.List;


public interface CreateCourseOwnSubjuctRepository extends JpaRepository<CourseOwnSubject, String> {
    List<CreateCourseOwnSubjuct> findByCourseId(String courseId);
}
