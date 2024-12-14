package com.dev.restLms.ProcessList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListSubjectOwnVideo;
import com.dev.restLms.model.SubjectOwnVideo;

import java.util.List;


@Repository
public interface ProcessListSubjectOwnVideoRepository extends JpaRepository<SubjectOwnVideo, Object> {
    List<ProcessListSubjectOwnVideo> findBySovOfferedSubjectsId(String sovOfferedSubjectsId);
}
