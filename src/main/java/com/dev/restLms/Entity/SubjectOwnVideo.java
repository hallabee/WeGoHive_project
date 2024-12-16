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
public class SubjectOwnVideo {

    @Id
    private String episodeId;

    private String sovOfferedSubjectsId;
    private String videoSortIndex;
    private String sovVideoId;

    @PrePersist
    public void generateUUID() {
        if (episodeId == null) {
            episodeId = UUID.randomUUID().toString();
        }
    }
    
}
