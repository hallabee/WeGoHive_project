package com.dev.restLms.FreeBulletinBoardPost;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.restLms.entity.BoardPost;
import com.dev.restLms.entity.Comment;
import com.dev.restLms.entity.UserOwnPermissionGroup;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/freeBulletinBoardPost")
@Tag(name = "FreeBulletinBoardPostController", description = "자유게시판")
public class FreeBulletinBoardPostController {

    @Autowired
    FreeBulletinBoardPostRepository freeBulletinBoardPostRepository;

    @Autowired
    FreeBulletinBoardPostCommentRepository freeBulletinBoardPostCommentRepository;

    @Autowired
    FreeBulletinBoardPostUserRepository freeBulletinBoardPostUserRepository;

    @Autowired
    FreeBulletinBoardPostUserOwnPermissionGroupRepository freeBulletinBoardPostUserOwnPermissionGroupRepository;

    @PostMapping("/post")
    @Operation(summary = "자유게시판 게시글 작성", description = "자유게시판에서 게시글을 작성합니다.")
    public ResponseEntity<?> postBoardPost(
        @RequestParam String sessionId,
        @RequestParam String boardId,
        @RequestBody BoardPost userBoardPost
        ) {
            // 작성자의 닉네임 확인 
            Optional<FreeBulletinBoardPostUser> findUserNickName = freeBulletinBoardPostUserRepository.findBySessionId(sessionId);

            userBoardPost.setAuthorNickname(findUserNickName.get().getNickname());
            // PostId는 UUID로 자동 할당받기 때문에 null로 삽입 
            userBoardPost.setPostId(null);
            userBoardPost.setSessionId(sessionId);
            userBoardPost.setBoardId(boardId);
            userBoardPost.setCreatedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            // 공지사항이면 T 아니면 F 
            if(userBoardPost.getIsNotice().equals("true")){
                userBoardPost.setIsNotice("T");
            }else{
                userBoardPost.setIsNotice("F");
            }
            // 파일 첨부는 일단 보류 
            userBoardPost.setFileNo(null);

            BoardPost savePost = freeBulletinBoardPostRepository.save(userBoardPost);
        
        return ResponseEntity.ok().body(savePost);
    }

    @PostMapping("/comment")
    @Operation(summary = "자유게시판 게시글의 댓글 작성", description = "자유게시판의 게시글에서 댓글을 작성합니다.")
    public ResponseEntity<?> postComment(
        @RequestParam String sessionId,
        @RequestParam String postId,
        @RequestBody Comment userComment
    ) {
        // 작석자의 닉네임 확인 
        Optional<FreeBulletinBoardPostUser> findUserNickname = freeBulletinBoardPostUserRepository.findBySessionId(sessionId);

        userComment.setAuthorNickname(findUserNickname.get().getNickname());
        userComment.setCommentId(null);
        userComment.setPostId(postId);
        userComment.setSessionId(sessionId);
        userComment.setCreatedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        // 비밀글인지 확인 
        if(userComment.getIsSecret().equals("true")){
            userComment.setIsSecret("T");
        }else{
            userComment.setIsSecret("F");
        }
        
        Comment saveComment = freeBulletinBoardPostCommentRepository.save(userComment);
        
        userComment.setRootCommentId(userComment.getCommentId());
        freeBulletinBoardPostCommentRepository.save(saveComment);

        return ResponseEntity.ok().body(saveComment);
    }

    @PostMapping("/comment/reply")
    @Operation(summary = "자유게시판 게시글 댓글의 대댓글 작성", description = "자유게시판의 게시글 댓글의 대댓글을 작성합니다.")
    public ResponseEntity<?> postCommentReply(
        @RequestParam String sessionId,
        @RequestParam String postId,
        @RequestParam String commentId,
        @RequestBody Comment userCommentReply
        ) {
            // 작성자의 닉네임 확인 
            Optional<FreeBulletinBoardPostUser> findUserNickname = freeBulletinBoardPostUserRepository.findBySessionId(sessionId);

            userCommentReply.setAuthorNickname(findUserNickname.get().getNickname());
            userCommentReply.setCommentId(null);
            userCommentReply.setSessionId(sessionId);
            userCommentReply.setPostId(postId);
            userCommentReply.setRootCommentId(commentId);
            userCommentReply.setCreatedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            // 비밀글인지 확인 
            if(userCommentReply.getIsSecret().equals("true")){
                userCommentReply.setIsSecret("T");
            }else{
                userCommentReply.setIsSecret("F");
            }

            // 해당 댓글의 목록 가져오기 
            List<Comment> commentReplys = freeBulletinBoardPostCommentRepository.findByRootCommentId(commentId);

            // 작성한 댓글의 이전 댓글의 고유 코드를 찾기 
            Long createdDate = 0L;
            String previousId = null;
            for(Comment commentReply : commentReplys){
                String commentCreatedDate = commentReply.getCreatedDate();
                if(createdDate < Long.parseLong(commentCreatedDate)){
                    createdDate = Long.parseLong(commentCreatedDate);
                    previousId = commentReply.getCommentId();
                }
            }
            userCommentReply.setPreviousCommentId(previousId);

            Comment saveCommentReply = freeBulletinBoardPostCommentRepository.save(userCommentReply);

        return ResponseEntity.ok().body(saveCommentReply);
    }
    
    @GetMapping()
    public ResponseEntity<?> getboardPost(
        @RequestParam String sessionId,
        @RequestParam String postId
        ) {

            Optional<UserOwnPermissionGroup> user = freeBulletinBoardPostUserOwnPermissionGroupRepository.findBySessionId(sessionId);

            if(user.isPresent()){

                // 해당 게시글 내용 확인 
                Optional<FreeBulletinBoardPosts> boardPost = freeBulletinBoardPostRepository.findByPostId(postId);

                List<Map<String, Object>> resuList = new ArrayList<>();

                HashMap<String, Object> posts = new HashMap<>();
                posts.put("postSessionId", boardPost.get().getSessionId());
                posts.put("postId", boardPost.get().getPostId());
                posts.put("postAuthorNickname", boardPost.get().getAuthorNickname());
                posts.put("postCreatedDate", boardPost.get().getCreatedDate());
                posts.put("postTitle", boardPost.get().getTitle());
                posts.put("postContent", boardPost.get().getContent());

                resuList.add(posts);

                // 해당 게시글의 댓글들 확인 
                List<Comment> comment = freeBulletinBoardPostCommentRepository.findByPostId(postId);

            }
            
        return null;
    }
    


    
}
