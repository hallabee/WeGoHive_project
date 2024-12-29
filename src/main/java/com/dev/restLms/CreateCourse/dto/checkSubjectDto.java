package com.dev.restLms.CreateCourse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class checkSubjectDto {
    String subjectId;
    String subjectName;
    String teacherSessionId;
}
