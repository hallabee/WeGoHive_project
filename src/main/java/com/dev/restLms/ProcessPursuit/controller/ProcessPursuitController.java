// ProcessPursuitController.java
package com.dev.restLms.ProcessPursuit.controller;

import com.dev.restLms.ProcessPursuit.model.ProcessPursuitCourse;
import com.dev.restLms.ProcessPursuit.model.ProcessPursuitUserOwnCourse;
import com.dev.restLms.ProcessPursuit.persistence.ProcessPursuitCourseRepository;
import com.dev.restLms.ProcessPursuit.persistence.ProcessPursuitUserOwnCourseRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("course")
@Tag(name = "ProcessPursuitController", description = "과정 목록 및 해당 과정의 수강자 수")
public class ProcessPursuitController {

    @Autowired
    private ProcessPursuitCourseRepository courseRepository;

    @Autowired
    private ProcessPursuitUserOwnCourseRepository processPursuitUserOwnCourseRepository;

    @GetMapping("/titles")
    @Operation(summary = "모든 과정 조회", description = "전체 과정 목록을 반환합니다.")
    public List<Map<String, Object>> getAllCoursesWithTeachers() {
        // 모든 과정 정보 가져오기
        List<ProcessPursuitCourse> courses = courseRepository.findAll();
        
        // 결과를 저장할 리스트
        List<Map<String, Object>> resultList = new ArrayList<>();
        
        for (ProcessPursuitCourse course : courses) {
            
            // 수강자 수 조회
            List<ProcessPursuitUserOwnCourse> userCount = processPursuitUserOwnCourseRepository.findByCourseId(course.getCourseId());
            int studentCount = userCount.size();
            
            // 각 과정 정보와 수강자 수를 HashMap에 추가
            HashMap<String, Object> cousereMap = new HashMap<>();
            cousereMap.put("coureId", course.getCourseId());
            cousereMap.put("courseTitle", course.getCourseTitle());
            cousereMap.put("courseCapacity", course.getCourseCapacity());
            cousereMap.put("enrollStartDate", course.getEnrollStartDate());
            cousereMap.put("enrollEndDate", course.getEnrollEndDate());
            cousereMap.put("studentCount", studentCount);

            // 결과를 리스트에 추가
            resultList.add(cousereMap);
        }

        return resultList;
    }
}
