package com.dev.restLms.userSubjects;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.Entity.UserOwnSubjectVideo;

import java.util.Optional;


public interface userSubjectsUserOwnSubjectVideoRepository extends JpaRepository<UserOwnSubjectVideo, String> {
    Optional<userSubjectsUserOwnSubjectVideo> findByUosvSessionIdAndUosvEpisodeId(String uosvSessionId, String uoseEpisodeId);
}
