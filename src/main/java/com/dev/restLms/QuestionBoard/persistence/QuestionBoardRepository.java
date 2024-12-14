package com.dev.restLms.QuestionBoard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.restLms.QuestionBoard.model.QuestionBoard;
import com.dev.restLms.model.Board;

import java.util.Optional;


@Repository
public interface QuestionBoardRepository extends JpaRepository<Board, String> {
    Optional<QuestionBoard> findByOfferedSubjectsId(String offeredSubjectsId);
}
