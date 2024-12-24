package com.dev.restLms.SurveyStatistics.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.SurveyOwnAnswer;

public interface SurveyStatisticsSurveyOwnAnswerRepository extends JpaRepository<SurveyOwnAnswer, String> {
    
}
