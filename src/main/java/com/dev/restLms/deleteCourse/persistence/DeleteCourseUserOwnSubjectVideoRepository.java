package com.dev.restLms.deleteCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.UserOwnSubjectVideo;

public interface DeleteCourseUserOwnSubjectVideoRepository extends JpaRepository<UserOwnSubjectVideo, String> {
    void deleteByUosvOfferedSubjectsId(String offeredSubjectsId);

    boolean existsByUosvOfferedSubjectsId(String uosvOfferedSubjectsId);
}
