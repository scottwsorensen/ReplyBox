package com.replybox.survey;
import java.util.List;

import com.replybox.question.Question;
import com.replybox.survey.Survey;
import com.replybox.user.User;
import com.replybox.useraudience.UserAudience;
public interface ISurveyDAO {
    List<Survey> getAllSurveys();
    Survey getSurveyById(Integer surveyId);
    void addSurvey(Survey survey);
    void updateSurvey(Survey survey);
    void deleteSurvey(Integer surveyId);
    boolean surveyExists(Integer companyId, String surveyName);
	List<Survey> getSurveysToProcess();
} 