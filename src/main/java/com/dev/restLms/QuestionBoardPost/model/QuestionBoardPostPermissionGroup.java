package com.dev.restLms.QuestionBoardPost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionBoardPostPermissionGroup {
    private String permissionGroupUuid;

    private String permissionName;
    
}
