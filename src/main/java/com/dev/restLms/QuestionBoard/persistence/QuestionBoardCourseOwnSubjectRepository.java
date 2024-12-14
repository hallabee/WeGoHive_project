package com.dev.restLms.QuestionBoard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.QuestionBoard.model.QuestionBoardCourseOwnSubject;
import com.dev.restLms.model.CourseOwnSubject;

import java.util.Optional;


@Repository
public interface QuestionBoardCourseOwnSubjectRepository extends JpaRepository<CourseOwnSubject, Object> {
    Optional<QuestionBoardCourseOwnSubject> findBySubjectId(String subjectId);
}
