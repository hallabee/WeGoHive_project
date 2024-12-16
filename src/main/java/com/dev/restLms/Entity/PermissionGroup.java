package com.dev.restLms.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionGroup {

    @Id
    private String permissionGroupUuid;

    private String permissionName;
    private String componentName;
    private String endpointUrl;

    @PrePersist
    public void generateUUID() {
        if (permissionGroupUuid == null) {
            permissionGroupUuid = UUID.randomUUID().toString();
        }
    }
    
}
