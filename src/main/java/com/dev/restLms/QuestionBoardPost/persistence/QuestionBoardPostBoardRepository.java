package com.dev.restLms.QuestionBoardPost.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.QuestionBoardPost.model.QuestionBoardPostBoard;
// import java.util.List;
import java.util.Optional;


@Repository
public interface QuestionBoardPostBoardRepository extends JpaRepository<QuestionBoardPostBoard, String> {
    // Optional<QuestionBoardPostBoard> findByBoardId(String boardId);
}
