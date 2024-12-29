package com.dev.restLms.deleteCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.UserOwnAssignment;

public interface DeleteCourseUserOwnAssignmentRepository extends JpaRepository<UserOwnAssignment, String> {
    boolean existsByOfferedSubjectsId(String offeredSubjectsId);
    void deleteByOfferedSubjectsId(String offeredSubjectsId);
}
