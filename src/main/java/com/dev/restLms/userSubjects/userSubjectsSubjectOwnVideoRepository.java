package com.dev.restLms.userSubjects;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.Entity.SubjectOwnVideo;

import java.util.List;


public interface userSubjectsSubjectOwnVideoRepository extends JpaRepository <SubjectOwnVideo, String >  {
    List<userSubjectsSubjectOwnVideo> findBySovOfferedSubjectsId(String sovOfferedSubjectsId);
}
