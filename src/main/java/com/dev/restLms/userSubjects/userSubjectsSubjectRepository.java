package com.dev.restLms.userSubjects;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.Subject;

import java.util.Optional;


public interface userSubjectsSubjectRepository extends JpaRepository <Subject, String>{
    Optional<userSubjectsSubject> findBySubjectId(String subjectId);
}
