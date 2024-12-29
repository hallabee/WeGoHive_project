package com.dev.restLms.CourseCorrection.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.restLms.CourseCorrection.persistence.CourseCorrectionCourseRepository;
import com.dev.restLms.CourseCorrection.projection.CourseCorrectionCourse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/courseCorrection")
@Tag(name = "CourseCorrectionController", description = "관리자 과정 수정 페이지")
public class CourseCorrectionController {

    @Autowired
    private CourseCorrectionCourseRepository courseCorrectionCourseRepository;

    @GetMapping("/Courses")
    @Operation(summary = "책임자의 과정 목록", description = "책임자의 과정 목록을 불러옵니다.")
    public ResponseEntity<?> getCourses(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "7") int size
    ) {

        try {

            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder
                                .getContext().getAuthentication();
            // 유저 세션아이디 보안 컨텍스트에서 가져오기
            String sessionId = auth.getPrincipal().toString();

            List<Map<String, Object>> resultList = new ArrayList<>();

            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "courseTitle"));
            Page<CourseCorrectionCourse> findCourses = courseCorrectionCourseRepository.findBySessionId(sessionId, pageable);

            for(CourseCorrectionCourse findCourse : findCourses){
                
                HashMap<String, Object> courseMap = new HashMap<>();
                courseMap.put("courseTitle", findCourse.getCourseTitle());
                courseMap.put("courseId", findCourse.getCourseId());

                resultList.add(courseMap);

            }

            Map<String, Object> response = new HashMap<>();
            response.put("content", resultList);
            response.put("currentPage", findCourses.getNumber());
            response.put("totalItems", findCourses.getTotalElements());
            response.put("totalPages", findCourses.getTotalPages());

            return ResponseEntity.ok().body(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("오류 발생 : " +e.getMessage());
        }
    }

    @PostMapping("/sreachCourse")
    @Operation(summary = "책임자의 과정 검색", description = "검색한 책임자의 과정 목록을 불러옵니다.")
    public ResponseEntity<?> sreachCourse(
        @RequestParam String courseTitle,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "7") int size
        ) {

            try {

                UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder
                                .getContext().getAuthentication();
                // 유저 세션아이디 보안 컨텍스트에서 가져오기
                String sessionId = auth.getPrincipal().toString();

                List<Map<String, Object>> resultList = new ArrayList<>();

                Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "courseTitle"));
                Page<CourseCorrectionCourse> findCourses = courseCorrectionCourseRepository.findBySessionIdAndCourseTitleContaining(sessionId, courseTitle, pageable);

                for(CourseCorrectionCourse findCourse : findCourses){
                    
                    HashMap<String, Object> courseMap = new HashMap<>();
                    courseMap.put("courseTitle", findCourse.getCourseTitle());
                    courseMap.put("courseId", findCourse.getCourseId());

                    resultList.add(courseMap);

                }

                Map<String, Object> response = new HashMap<>();
                response.put("content", resultList);
                response.put("currentPage", findCourses.getNumber());
                response.put("totalItems", findCourses.getTotalElements());
                response.put("totalPages", findCourses.getTotalPages());

                return ResponseEntity.ok().body(response);
                
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("오류 발생 : " +e.getMessage());
            }
    }
    
}
