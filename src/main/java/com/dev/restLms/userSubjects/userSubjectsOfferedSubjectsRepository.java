package com.dev.restLms.userSubjects;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.OfferedSubjects;

import java.util.Optional;


public interface userSubjectsOfferedSubjectsRepository extends JpaRepository<OfferedSubjects, String> {
    Optional<userSubjectsOfferedSubjects> findByOfferedSubjectsId(String offeredSubjectsId);
}
