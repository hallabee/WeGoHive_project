package com.dev.restLms.ProcessList.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "userowncourse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessListUserOwnCourse {

	@Id
    private String sessionId;

	@Column(name = "COURSE_ID")
    private String courseId;

    @Column(name = "OFFICER_SESSION_ID")
    private String officerSessionId;
}
