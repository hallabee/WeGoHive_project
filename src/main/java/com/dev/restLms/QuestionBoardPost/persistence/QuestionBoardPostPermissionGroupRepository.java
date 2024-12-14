package com.dev.restLms.QuestionBoardPost.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.QuestionBoardPost.model.QuestionBoardPostPermissionGroup;
import com.dev.restLms.model.PermissionGroup;

import java.util.Optional;


@Repository
public interface QuestionBoardPostPermissionGroupRepository extends JpaRepository<PermissionGroup, String> {
    Optional<QuestionBoardPostPermissionGroup> findByPermissionGroupUuid(String permissionGroupUuid);
}
