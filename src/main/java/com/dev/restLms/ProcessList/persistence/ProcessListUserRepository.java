package com.dev.restLms.ProcessList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListUser;
import java.util.List;


@Repository
public interface ProcessListUserRepository extends JpaRepository<ProcessListUser, String>{
    List<ProcessListUser> findBySessionId(String sessionId);
}
