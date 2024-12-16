package com.dev.restLms.userSubjects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.UserOwnAssignment;

import java.util.Optional;


public interface userSubjectsUserOwnAssignmentRepository extends JpaRepository <UserOwnAssignment, String> {
    Page<userSubjectsUserOwnAssignment> findByUserSessionIdAndSubjectAcceptCategory(String userSessionId, String subjectAcceptCategory, Pageable pageable);  
    Optional<userSubjectsUserOwnAssignment> findBySubjectAcceptCategory(String subjectAcceptCategory); 
}
