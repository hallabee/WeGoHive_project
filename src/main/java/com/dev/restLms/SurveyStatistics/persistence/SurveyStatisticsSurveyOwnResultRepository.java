package com.dev.restLms.SurveyStatistics.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.SurveyStatistics.projection.SurveyStatisticsSurveyOwnResult;
import com.dev.restLms.entity.SurveyOwnResult;
import java.util.List;


public interface SurveyStatisticsSurveyOwnResultRepository extends JpaRepository<SurveyOwnResult, String> {
    List<SurveyStatisticsSurveyOwnResult> findBySurveyExecutionId(String surveyExecutionId);
}
