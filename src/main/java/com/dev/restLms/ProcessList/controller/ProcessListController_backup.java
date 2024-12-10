// // ProcessPursuitController.java
// package com.dev.restLms.ProcessList.controller;

// import com.dev.restLms.ProcessList.model.ProcessListCourse;
// import com.dev.restLms.ProcessPursuit.model.ProcessPursuitCourse;
// import com.dev.restLms.ProcessPursuit.model.ProcessPursuitUser;
// import com.dev.restLms.ProcessPursuit.model.ProcessPursuitUserOwnCourse;
// import com.dev.restLms.ProcessPursuit.persistence.ProcessPursuitCourseRepository;
// import com.dev.restLms.ProcessPursuit.persistence.ProcessPursuitUserOwnCourseRepository;
// import com.dev.restLms.ProcessPursuit.persistence.ProcessPursuitUserRepository;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.web.bind.annotation.*;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// @RestController
// @RequestMapping("course")
// @Tag(name = "ProcessListController", description = "과정 목록 및 해당 과정의 수강자 수, 과정 책임자, 페이징")
// public class ProcessListController_backup {

//     @Autowired
//     private ProcessPursuitCourseRepository courseRepository;

//     @Autowired
//     private ProcessPursuitUserOwnCourseRepository processPursuitUserOwnCourseRepository;

//     @Autowired
//     private ProcessPursuitUserRepository processPursuitUserRepository;

//     @GetMapping("/allTitles")
//     @Operation(summary = "모든 과정 조회", description = "전체 과정 목록을 반환합니다.")
//     public List<Map<String, Object>> getAllCoursesWithTeachers(
//             @RequestParam(defaultValue = "0") int page,
//             @RequestParam(defaultValue = "10") int size) {
        
//         // 페이징 요청
//         Pageable pageable = PageRequest.of(page, size);
//         Page<ProcessPursuitCourse> coursePage = courseRepository.findAll(pageable);
        
//         // 결과를 저장할 리스트
//         List<Map<String, Object>> resultList = new ArrayList<>();
        
//         for (ProcessPursuitCourse course : coursePage) {
//             // 수강자 수 조회
//             List<ProcessPursuitUserOwnCourse> userCount = processPursuitUserOwnCourseRepository.findByCourseId(course.getCourseId());
//             int studentCount = userCount.size();

//             // 과정 책임자 정보 조회
//             List<ProcessPursuitUser> processPursuitUsers = processPursuitUserRepository.findBySessionId(course.getSessionId());

//             // 각 과정 정보와 수강자 수를 HashMap에 추가
//             HashMap<String, Object> courseMap = new HashMap<>();
//             courseMap.put("courseId", course.getCourseId());
//             courseMap.put("courseTitle", course.getCourseTitle());
//             courseMap.put("courseCapacity", course.getCourseCapacity());
//             courseMap.put("enrollStartDate", course.getEnrollStartDate());
//             courseMap.put("enrollEndDate", course.getEnrollEndDate());
//             courseMap.put("studentCount", studentCount);

//             // 책임자 정보 가져오기
//             if (!processPursuitUsers.isEmpty()) {
//                 ProcessPursuitUser officer = processPursuitUsers.get(0);
//                 courseMap.put("courseOfficerSessionId", officer.getSessionId());
//                 courseMap.put("courseOfficerUserName", officer.getUserName());
//             }

//             // 결과를 리스트에 추가
//             resultList.add(courseMap);
//         }

//         return resultList;
//     }
// }
