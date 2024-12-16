package com.dev.restLms.FreeBulletinBoardPost;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.Comment;
import java.util.List;


public interface FreeBulletinBoardPostCommentRepository extends JpaRepository<Comment, String> {
List<Comment> findByRootCommentId(String rootCommentId);
List<Comment> findByPostId(String postId);
}
