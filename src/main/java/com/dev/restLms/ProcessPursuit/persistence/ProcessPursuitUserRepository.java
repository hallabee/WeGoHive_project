package com.dev.restLms.ProcessPursuit.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessPursuit.model.ProcessPursuitUser;
import com.dev.restLms.model.User;

// import java.util.List;
import java.util.Optional;


@Repository
public interface ProcessPursuitUserRepository extends JpaRepository<User, String> {
    // List<ProcessPursuitUser> findBySessionId(String sessionId);
    Optional<ProcessPursuitUser> findBySessionId(String sessionId);
}
