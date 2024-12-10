package com.dev.restLms.ProcessList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListUserOwnAssignment;

@Repository
public interface ProcessListUserOwnAssignmentRepository extends JpaRepository<ProcessListUserOwnAssignment, String> {
    
}
