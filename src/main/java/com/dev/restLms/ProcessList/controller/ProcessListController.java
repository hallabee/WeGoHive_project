package com.dev.restLms.ProcessList.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.restLms.ProcessList.model.ProcessListCourse;
import com.dev.restLms.ProcessList.model.ProcessListCourseOwnSubject;
import com.dev.restLms.ProcessList.model.ProcessListOfferedSubjects;
import com.dev.restLms.ProcessList.model.ProcessListUser;
import com.dev.restLms.ProcessList.model.ProcessListUserOwnCourse;
import com.dev.restLms.ProcessList.persistence.ProcessListCourseOwnSubjectRepository;
import com.dev.restLms.ProcessList.persistence.ProcessListCourseRepository;
import com.dev.restLms.ProcessList.persistence.ProcessListOfferedSubjectsRepository;
import com.dev.restLms.ProcessList.persistence.ProcessListUserOwnCourseRepository;
import com.dev.restLms.ProcessList.persistence.ProcessListUserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("course")
@Tag(name = "ProcessPursuitController", description = "과정 목록 및 강사 이름 및 수강자 수")
public class ProcessListController {

    @Autowired
    private ProcessListCourseRepository processListCourseRepository;

    @Autowired
    private ProcessListCourseOwnSubjectRepository processListCourseOwnSubjectRepository;

    @Autowired
    private ProcessListOfferedSubjectsRepository processListOfferedSubjectsRepository;

    @Autowired
    private ProcessListUserRepository processListUserRepository;

    @Autowired
    private ProcessListUserOwnCourseRepository processListUserOwnCourseRepository;

    @GetMapping("/allTitles")
    @Operation(summary = "모든 과정 조회", description = "전체 과정 목록을 반환합니다.")
    public List<Map<String, Object>> getAllCoursesWithTeachers() {
        // 모든 과정 정보 가져오기
        List<ProcessListCourse> courses = processListCourseRepository.findAll();
        
        // 결과를 저장할 리스트
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (ProcessListCourse course : courses) {
            // 과정 소유 과목 가져오기
            List<ProcessListCourseOwnSubject> courseOwnSubjects = processListCourseOwnSubjectRepository.findByCourseId(course.getCourseId());

            // 수강자 수 조회
            List<ProcessListUserOwnCourse> userCount = processListUserOwnCourseRepository.findByCourseId(course.getCourseId());
            int studentCount = userCount.size();

            // 강사 정보 저장 리스트
            List<ProcessListUser> teachers = new ArrayList<>();

            for (ProcessListCourseOwnSubject ownSubject : courseOwnSubjects) {
                // 해당 과목의 제공 과목 정보 가져오기
                List<ProcessListOfferedSubjects> offeredSubjects = processListOfferedSubjectsRepository.findByCourseId(ownSubject.getCourseId());
                
                for (ProcessListOfferedSubjects offeredSubject : offeredSubjects) {
                    // 강사 정보를 가져오기
                    ProcessListUser teacher = processListUserRepository.findById(offeredSubject.getTeacherSessionId()).orElse(null);
                    if (teacher != null) {
                        teachers.add(teacher);
                    }
                }
            }

            // 각 강사 정보를 과정 정보와 결합하여 HashMap에 추가
            Map<String, Object> subInfoMap = new HashMap<>();
            for(ProcessListUser teacher : teachers){
                subInfoMap.put("coureId", course.getCourseId());
                subInfoMap.put("courseTitle", course.getCourseTitle());
                subInfoMap.put("courseCapacity", course.getCourseCapacity());
                subInfoMap.put("enrollStartDate", course.getEnrollStartDate());
                subInfoMap.put("enrollEndDate", course.getEnrollEndDate());
                subInfoMap.put("studentCount", studentCount);
                subInfoMap.put("userName", teacher.getUserName());

                // 결과 리스트에 추가
                resultList.add(subInfoMap);
            }
        }

        return resultList;
    }
}
