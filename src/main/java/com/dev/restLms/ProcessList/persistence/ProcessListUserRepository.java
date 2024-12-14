package com.dev.restLms.ProcessList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListUser;
import com.dev.restLms.model.User;

// import java.util.List;
import java.util.Optional;


@Repository
public interface ProcessListUserRepository extends JpaRepository<User, String> {
    // List<ProcessListUser> findBySessionId(String sessionId);
    Optional<ProcessListUser> findBySessionId(String sessionId);
}
