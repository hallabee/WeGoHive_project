package com.dev.restLms.sechan.subjectListPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.restLms.entity.OfferedSubjects;
import com.dev.restLms.entity.Subject;
import com.dev.restLms.entity.User;
import com.dev.restLms.sechan.subjectListPage.projection.S_Projection;
import com.dev.restLms.sechan.subjectListPage.projection.SLP_U_Projection;
import com.dev.restLms.sechan.subjectListPage.repository.SLP_OS_Repository;
import com.dev.restLms.sechan.subjectListPage.repository.SLP_S_Repository;
import com.dev.restLms.sechan.subjectListPage.repository.SLP_U_Repository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "과목 조회", description = "개설된 전체 과목 조회")
public class SLP_Controller {

    @Autowired
    private SLP_OS_Repository slp_os_repository;

    @Autowired
    private SLP_S_Repository slp_s_repository;

    @GetMapping("/subjectInfo")
    @Operation(summary = "전체 개설 과목 조회", description = "개설이 완료된 전체 과목을 조회합니다.")
    public List<Map<String, String>> getAllSubjects() {

        // OfferredSubjects에서 모든 개설 과목 조회
        List<OfferedSubjects> offeredSubjectsList = slp_os_repository.findAll();

        // OfferredSubjects에서 subjectId 목록 추출
        List<String> subjectIds = new ArrayList<>();
        for (OfferedSubjects os : offeredSubjectsList) {
            subjectIds.add(os.getSubjectId());
        }

        // Subject 데이터 조회 (S_Projection 활용)
        List<S_Projection> projections = slp_s_repository.findBySubjectIdIn(subjectIds);

        // 결과 생성
        List<Map<String, String>> result = new ArrayList<>();
        for (S_Projection projection : projections) {
            Map<String, String> subjectInfo = new HashMap<>();
            subjectInfo.put("subjectId", projection.getSubjectId());
            subjectInfo.put("subjectName", projection.getSubjectName());
            subjectInfo.put("subjectDesc", projection.getSubjectDesc());
            subjectInfo.put("subjectImage", projection.getSubjectImageLink());
            result.add(subjectInfo);
        }

        return result;
    }

    @Autowired
    private SLP_U_Repository slp_u_repository;

    @GetMapping("/getTeacherName")
    @Operation(summary = "개설 과목마다 강사 이름 조회", description = "개설된 과목의 강사 이름을 볼 수 있음")
    public List<Map<String, String>> getTeachersForAllSubjects() {

        // OfferredSubjects에서 모든 개설 과목 조회
        List<OfferedSubjects> offeredSubjectsList = slp_os_repository.findAll();

        // OfferredSubjects에서 teacherSessionId 목록 추출
        List<String> teacherSessionIds = new ArrayList<>();
        for (OfferedSubjects os : offeredSubjectsList) {
            teacherSessionIds.add(os.getTeacherSessionId());
        }

        // User 데이터 조회 (SLP_U_Projection 활용)
        List<SLP_U_Projection> teacherProjections = slp_u_repository.findBySessionIdIn(teacherSessionIds);

        // 결과 생성
        List<Map<String, String>> result = new ArrayList<>();
        for (int i = 0; i < offeredSubjectsList.size(); i++) {
            Map<String, String> teacherDetails = new HashMap<>();
            teacherDetails.put("subjectId", offeredSubjectsList.get(i).getSubjectId());
            teacherDetails.put("teacherName", teacherProjections.get(i).getUserName());
            result.add(teacherDetails);
        }

        return result;
    }

    @GetMapping("/getAllSubjectInfo")
    @Operation(summary = "전체 개설 과목 및 강사 조회", description = "개설된 모든 과목의 상세 정보와 강사 이름을 조회합니다")
    public List<Map<String, String>> getAllSubjectInfo() {

        // OfferredSubjects에서 모든 개설 과목 조회
        List<OfferedSubjects> offeredSubjectsList = slp_os_repository.findAll();

        // 결과 리스트 초기화
        List<Map<String, String>> result = new ArrayList<>();

        for (OfferedSubjects os : offeredSubjectsList) {
            Map<String, String> details = new HashMap<>();

            // OfferedSubjects ID 추가
            details.put("offeredSubjectsId", os.getOfferedSubjectsId());

            // 과목 정보 조회
            Subject subject = slp_s_repository.findById(os.getSubjectId()).orElse(null);
            if (subject != null) {
                details.put("subjectName", subject.getSubjectName());
                details.put("subjectDesc", subject.getSubjectDesc());
                details.put("subjectImage", subject.getSubjectImageLink());
            } else {
                details.put("subjectName", "과목 이름 없음");
                details.put("subjectDesc", "과목 설명 없음");
                details.put("subjectImage", "이미지 없음");
            }

            // 강사 정보 조회
            User teacher = slp_u_repository.findById(os.getTeacherSessionId()).orElse(null);
            if (teacher != null) {
                details.put("teacherName", teacher.getUserName());
            } else {
                details.put("teacherName", "강사 정보 없음");
            }

            // 결과 추가
            result.add(details);
        }

        return result;
    }
}
