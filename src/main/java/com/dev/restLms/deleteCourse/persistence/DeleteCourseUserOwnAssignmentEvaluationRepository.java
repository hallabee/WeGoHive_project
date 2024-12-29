package com.dev.restLms.deleteCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.UserOwnAssignmentEvaluation;

public interface DeleteCourseUserOwnAssignmentEvaluationRepository extends JpaRepository<UserOwnAssignmentEvaluation, String> {
    boolean existsByAssignmentId(String assignmentId);
    void deleteByAssignmentId(String assignmentId);
}
