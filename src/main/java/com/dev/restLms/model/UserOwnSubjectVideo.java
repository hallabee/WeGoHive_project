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
public class UserOwnSubjectVideo {

    @Id
    private String increaseId;

    private String uosvSessionId;
    private String uosvEpisodeId;
    private String uosvOfferedSubjectsId;
    private String progress;
    private String uosvFinal;
    
}
