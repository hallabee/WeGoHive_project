package com.dev.restLms.QuestionBoard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.QuestionBoard.model.QuestionBoardSubject;
import com.dev.restLms.model.Subject;

import java.util.Optional;


@Repository
public interface QuestionBoardSubjectRepository extends JpaRepository<Subject, String> {
    Optional<QuestionBoardSubject> findBySubjectId(String subjectId);
}
