package com.dev.restLms.ProcessList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.ProcessList.model.ProcessListPermissionGroup;
import com.dev.restLms.model.PermissionGroup;

import java.util.Optional;



@Repository
public interface ProcessListPermissionGroupRepository extends JpaRepository<PermissionGroup, String> {
    Optional<ProcessListPermissionGroup> findByPermissionName(String permissionName);
    Optional<ProcessListPermissionGroup> findByPermissionGroupUuid(String permissionGroupUuid);
}
