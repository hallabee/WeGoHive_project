package com.dev.restLms.ProcessList.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "courseownsubject")
public class ProcessListCourseOwnSubject {

    @Id
    @Column(name = "COURSE_ID")
    private String courseId;

    
    @Column(name = "OFFICER_SESSION_ID")
	private String officerSessionId ;
	
	@Column(name = "SUBJECT_ID")
	private String subjectId;
    
}
