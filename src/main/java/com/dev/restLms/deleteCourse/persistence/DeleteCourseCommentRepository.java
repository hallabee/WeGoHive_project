package com.dev.restLms.deleteCourse.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.Comment;

public interface DeleteCourseCommentRepository extends JpaRepository<Comment, String> {
    boolean existsByPostId(String postId);
    void deleteByPostId(String postId);
}
