package com.dev.restLms.Entity;

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
public class UserOwnSubjectVideo {

    @Id
    private String increaseId;

    private String uosvSessionId;
    private String uosvEpisodeId;
    private String uosvOfferedSubjectsId;
    private String progress;
    private String uosvFinal;

    @PrePersist
    public void generateUUID() {
        if (increaseId == null) {
            increaseId = UUID.randomUUID().toString();
        }
    }
    
}
