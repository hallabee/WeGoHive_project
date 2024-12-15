package com.dev.restLms.userSubjects;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.Entity.OfferedSubjects;

import java.util.Optional;


public interface userSubjectsOfferedSubjectsRepository extends JpaRepository<OfferedSubjects, String> {
    Optional<userSubjectsOfferedSubjects> findByOfferedSubjectsId(String offeredSubjectsId);
}
