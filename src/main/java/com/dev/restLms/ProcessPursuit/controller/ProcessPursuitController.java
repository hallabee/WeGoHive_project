// ProcessPursuitController.java
package com.dev.restLms.ProcessPursuit.controller;

import com.dev.restLms.ProcessPursuit.model.ProcessPursuitCourse;
import com.dev.restLms.ProcessPursuit.model.ProcessPursuitCourseOwnSubject;
import com.dev.restLms.ProcessPursuit.model.ProcessPursuitOfferedSubjects;
import com.dev.restLms.ProcessPursuit.model.ProcessPursuitUser;
import com.dev.restLms.ProcessPursuit.model.ProcessPursuitUserOwnCourse;
import com.dev.restLms.ProcessPursuit.persistence.ProcessPursuitCourseOwnSubjectRepository;
import com.dev.restLms.ProcessPursuit.persistence.ProcessPursuitCourseRepository;
import com.dev.restLms.ProcessPursuit.persistence.ProcessPursuitOfferedSubjectsRepository;
import com.dev.restLms.ProcessPursuit.persistence.ProcessPursuitUserOwnCourseRepository;
import com.dev.restLms.ProcessPursuit.persistence.ProcessPursuitUserRepository;

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
@Tag(name = "ProcessPursuitController", description = "과정 목록 및 강사 이름 및 수강자 수")
public class ProcessPursuitController {

    @Autowired
    private ProcessPursuitCourseRepository courseRepository;

    @Autowired
    private ProcessPursuitCourseOwnSubjectRepository courseOwnSubjectRepository;

    @Autowired
    private ProcessPursuitOfferedSubjectsRepository offeredSubjectsRepository;

    @Autowired
    private ProcessPursuitUserRepository userRepository;

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
            // 과정 소유 과목 가져오기
            List<ProcessPursuitCourseOwnSubject> courseOwnSubjects = courseOwnSubjectRepository.findByCourseId(course.getCourseId());

            // 수강자 수 조회
            List<ProcessPursuitUserOwnCourse> userCount = processPursuitUserOwnCourseRepository.findByCourseId(course.getCourseId());
            int studentCount = userCount.size();

            // 강사 정보 저장 리스트
            List<ProcessPursuitUser> teachers = new ArrayList<>();

            for (ProcessPursuitCourseOwnSubject ownSubject : courseOwnSubjects) {
                // 해당 과목의 제공 과목 정보 가져오기
                List<ProcessPursuitOfferedSubjects> offeredSubjects = offeredSubjectsRepository.findByCourseId(ownSubject.getCourseId());
                
                for (ProcessPursuitOfferedSubjects offeredSubject : offeredSubjects) {
                    // 강사 정보를 가져오기
                    ProcessPursuitUser teacher = userRepository.findById(offeredSubject.getTeacherSessionId()).orElse(null);
                    if (teacher != null) {
                        teachers.add(teacher);
                    }
                }
            }

            // 각 강사 정보를 과정 정보와 결합하여 HashMap에 추가
            Map<String, Object> subInfoMap = new HashMap<>();
            for(ProcessPursuitUser teacher : teachers){
                subInfoMap.put("coureId", course.getCourseId());
                subInfoMap.put("courseTitle", course.getCourseTitle());
                subInfoMap.put("courseCapacity", course.getCourseCapacity());
                subInfoMap.put("enrollStartDate", course.getEnrollStartDate());
                subInfoMap.put("enrollEndDate", course.getEnrollEndDate());
                subInfoMap.put("studentCount", studentCount);
                subInfoMap.put("userName", teacher.getUserName());
                subInfoMap.put("fileNo", teacher.getFileNo());
                subInfoMap.put("seqNo", teacher.getSeqNo());

                // 결과 리스트에 추가
                resultList.add(subInfoMap);
            }
        }

        return resultList;
    }
}
