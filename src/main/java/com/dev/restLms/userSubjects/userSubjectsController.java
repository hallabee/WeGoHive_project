package com.dev.restLms.userSubjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/userSubjects")
@Tag(name = "userSubjectsController", description = "사용자의 과정에 속하지 않은 과목 목록 확인")
public class userSubjectsController {

    @Autowired
    private userSubjectsCourseOwnSubjectRepository userSubjectsCourseOwnSubjectRepository;

    @Autowired
    private userSubjectsOfferedSubjectsRepository userSubjectsOfferedSubjectsRepository;

    @Autowired
    private userSubjectsSubjectOwnVideoRepository userSubjectsSubjectOwnVideoRepository;

    @Autowired
    private userSubjectsSubjectRepository userSubjectsSubjectRepository;

    @Autowired
    private userSubjectsUserOwnAssignmentRepository userSubjectsUserOwnAssignmentRepository;

    @Autowired
    private userSubjectsUserOwnSubjectVideoRepository userSubjectsUserOwnSubjectVideoRepository;

    @GetMapping()
    public ResponseEntity<?> getAllUserSubjects(
        @RequestParam String sessionId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
        ) {

        // 사용자의 과정에 속하지 않은 수강중인 과목 확인 
        Pageable pageable = PageRequest.of(page, size);
        Page<userSubjectsUserOwnAssignment> userOwnAssignments = userSubjectsUserOwnAssignmentRepository.findByUserSessionIdAndSubjectAcceptCategory(sessionId, "T", pageable);

        for(userSubjectsUserOwnAssignment userOwnAssignment : userOwnAssignments){

            List<Map<String, String>> resultList = new ArrayList<>();

            HashMap<String, String> subject = new HashMap<>();

            // 과목의 개설과목 코드 확인 
            Optional<userSubjectsOfferedSubjects> userSubjectId = userSubjectsOfferedSubjectsRepository.findByOfferedSubjectsId(userOwnAssignment.getOfferedSubjectsId());

            // 개설과목 코드에 대한 과목 코드 확인 
            Optional<userSubjectsCourseOwnSubject> findUserSubjectId = userSubjectsCourseOwnSubjectRepository.findBySubjectId(userSubjectId.get().getSubjectId());

            // 과목 코드에 대한 과목 이름 확인 
            Optional<userSubjectsSubject> findUserSubjectName = userSubjectsSubjectRepository.findBySubjectId(findUserSubjectId.get().getSubjectId());

            subject.put("offeredSubjectsId", userSubjectId.get().getOfferedSubjectsId());
            subject.put("subjectName", findUserSubjectName.get().getSubjectName());
            subject.put("subjectImageLink", findUserSubjectName.get().getSubjectImageLink());
            subject.put("subjectPromotion", findUserSubjectName.get().getSubjectPromotion());

            // ------------------------------------------------- 과목 이름, 이미지, 소개 

            // 사용자가 듣는 과정의 영상 목록 확인 
            List<userSubjectsSubjectOwnVideo> subjectOwnVideos = userSubjectsSubjectOwnVideoRepository.findBySovOfferedSubjectsId(userOwnAssignment.getOfferedSubjectsId());
            
            // 과목의 수강률 
            int subjectCompletion = 0;

            // 과목의 영상을 수강했는지 확인 
            boolean finishVideo = true;

            for(userSubjectsSubjectOwnVideo subjectOwnVideo : subjectOwnVideos){

                // 과정 영상의 에피소드 아이디 확인 
                Optional<userSubjectsUserOwnSubjectVideo> userOwnSubjectVideo = userSubjectsUserOwnSubjectVideoRepository.findByUosvSessionIdAndUosvEpisodeId(sessionId, subjectOwnVideo.getEpisodeId());

                if(Integer.parseInt(userOwnSubjectVideo.get().getProgress())>100){
                    subjectCompletion += 1;
                }else{
                    subjectCompletion += 0;
                }

                if(Integer.parseInt(userOwnSubjectVideo.get().getProgress())<100){
                    finishVideo = false;
                    break;
                }else{
                    finishVideo = true;
                }
                
            }

            // 수료한 영상 / 영상 수 * 100
            int subjectCompletionPercent = (subjectCompletion/subjectOwnVideos.size())*100;

            subject.put("subjectCompletion", Integer.toString(subjectCompletionPercent));

            if(finishVideo){
                subject.put("userlistenSubject", "수강완료");
            }else{
                subject.put("userlistenSubject", "수강 중");
            }

            // // ---------------------------------------------- 해당 수강 중인지 수료 했는지 확인

            resultList.add(subject);

            return ResponseEntity.ok().body(resultList);           
        }        
        return ResponseEntity.status(HttpStatus.CONFLICT).body("수강 중인 과목이 없습니다.");
    }
    
}
