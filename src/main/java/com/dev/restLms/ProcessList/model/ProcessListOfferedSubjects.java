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
@Table(name = "offeredsubjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessListOfferedSubjects {
	
	@Id
	@Column(name = "COURSE_ID")
	private String courseId ;
	
	@Column(name = "TEACHER_SESSION_ID")
	private String teacherSessionId;
	
}
