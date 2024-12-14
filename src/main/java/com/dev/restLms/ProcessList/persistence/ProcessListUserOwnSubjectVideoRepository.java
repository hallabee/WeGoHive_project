package com.dev.restLms.ProcessList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListUserOwnSubjectVideo;
import com.dev.restLms.model.UserOwnSubjectVideo;

import java.util.List;


@Repository
public interface ProcessListUserOwnSubjectVideoRepository extends JpaRepository<UserOwnSubjectVideo, Object> {
    List<ProcessListUserOwnSubjectVideo> findByUosvSessionIdAndUosvOfferedSubjectsId(String uosvSessionId, String uosvOfferedSubjectsId);
}
