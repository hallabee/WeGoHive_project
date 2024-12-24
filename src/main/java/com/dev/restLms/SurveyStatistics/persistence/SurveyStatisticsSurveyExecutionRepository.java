package com.dev.restLms.SurveyStatistics.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.SurveyStatistics.projection.SurveyStatisticsSurveyExecution;
import com.dev.restLms.entity.SurveyExecution;
import java.util.Optional;
import java.util.List;




public interface SurveyStatisticsSurveyExecutionRepository extends JpaRepository<SurveyExecution, String> {
    
    Optional<SurveyStatisticsSurveyExecution> findBySessionIdAndCourseId(String sessionId, String courseId);

    List<SurveyStatisticsSurveyExecution> findByCourseIdAndSessionId(String courseId, String sessionId);
    
}
