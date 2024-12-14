package com.dev.restLms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    private String commentId;

    private String authorNickname;
    private String createdDate;
    private String content;
    private String rootCommentId;
    private String postId;
    private String sessionId;
    private String previousCommentId;
    private String isSecret;
    
}
