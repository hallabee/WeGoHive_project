package com.dev.restLms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    
}
