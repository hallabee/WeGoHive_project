package com.dev.restLms.ProcessList.model;

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
public class ProcessListUserOwnPermissionGroup {

    private String permissionGroupUuid2;

    private String sessionId;
    
}
