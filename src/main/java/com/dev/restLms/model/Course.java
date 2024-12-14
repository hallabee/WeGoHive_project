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
public class Course {

    @Id
    private String courseId;

    private String sessionId;
    private String courseTitle;
    private String courseBoundary;
    private String courseCompleted;
    private String courseCapacity;
    private String courseProgressStatus;
    private String courseStartDate;
    private String courseEndDate;
    private String enrollStartDate;
    private String enrollEndDate;
    private String courseImg;
    
}