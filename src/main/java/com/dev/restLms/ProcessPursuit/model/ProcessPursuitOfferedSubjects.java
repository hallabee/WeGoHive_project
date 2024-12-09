package com.dev.restLms.ProcessPursuit.model;
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
public class ProcessPursuitOfferedSubjects {
	
	@Id
	private String courseId ;
	
	private String teacherSessionId;
	
}
