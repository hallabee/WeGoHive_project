package com.dev.restLms.QuestionBoardPost.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "boardpost")
public class QuestionBoardPostBoardPost {

    @Id
    @Column(name = "POST_ID")
    private String postId;

    @Column(name = "AUTHOR_NICKNAME")
    private String authorNickname;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "BOARD_ID")
    private String boardId;

    private String sessionId;

}
