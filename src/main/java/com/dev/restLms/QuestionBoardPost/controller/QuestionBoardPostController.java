package com.dev.restLms.QuestionBoardPost.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.restLms.QuestionBoardPost.model.QuestionBoardPostBoardPost;
import com.dev.restLms.QuestionBoardPost.model.QuestionBoardPostComment;
import com.dev.restLms.QuestionBoardPost.persistence.QuestionBoardPostBoardPostRepository;
import com.dev.restLms.QuestionBoardPost.persistence.QuestionBoardPostCommentRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/quesionBoard")
@Tag(name = "QuestionBoardPostController", description = "과목별 질문 게시판의 게시글")
public class QuestionBoardPostController {

    @Autowired
    private QuestionBoardPostBoardPostRepository questionBoardPostBoardPostRepository;

    @Autowired
    private QuestionBoardPostCommentRepository questionBoardPostCommentRepository;

    @GetMapping("/boradPost")
    @Operation(summary = "사용자의 질문과 답변", description = "질문과 답변을 반환합니다")
    public ResponseEntity<?> getboardPost(
        @RequestParam String sessionId,
        @RequestParam String postId
        ) {

            // 게시글을 작성한 사용자와 로그인한 사용자의 세션아이디가 일치하는지 확인
            // 게시글 테이블에서 게시글이 있는지 확인
            Optional<QuestionBoardPostBoardPost> boardPost = questionBoardPostBoardPostRepository.findBySessionIdAndPostId(sessionId, postId);

            if(boardPost.isEmpty()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("비밀글 입니다");
            }

            Optional<QuestionBoardPostComment> comment = questionBoardPostCommentRepository.findByPostId(boardPost.get().getPostId());

            if(boardPost.isPresent()){

                 // 결과를 저장할 리스트
                List<Map<String, String>> resultList = new ArrayList<>();

                HashMap<String, String> posts = new HashMap<>();
                posts.put("postSessionId", sessionId);
                posts.put("postID", postId);
                posts.put("postAuthorNickname", boardPost.get().getAuthorNickname());
                posts.put("postCreatedDate", boardPost.get().getCreatedDate());
                posts.put("postTitle", boardPost.get().getTitle());
                posts.put("postContent", boardPost.get().getContent());

                resultList.add(posts);

                if(comment.isPresent()){
                    
                    HashMap<String, String> comments = new HashMap<>();
                    comments.put("commentId", comment.get().getCommentId());
                    comments.put("commentAuthorNickname", comment.get().getAuthorNickname());
                    comments.put("commentCreatedDate", comment.get().getCreatedDate());
                    comments.put("comment", comment.get().getContent());
                    comments.put("commentSessionId", comment.get().getSessionId());
    
                    resultList.add(comments);

                }

                return ResponseEntity.ok().body(resultList);

            }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("비밀글 입니다.");
    }
    
    
}
