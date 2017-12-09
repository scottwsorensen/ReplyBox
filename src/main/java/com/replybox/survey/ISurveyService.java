package com.replybox.survey;

import java.util.List;
import com.replybox.survey.Survey;
import com.replybox.question.Question;
import com.replybox.user.User;
import com.replybox.useraudience.UserAudience;
public interface ISurveyService {
     List<Survey> getAllSurveys();
     Survey getSurveyById(Integer surveyId);
     boolean addSurvey(Survey survey);
     void updateSurvey(Survey survey);
     void deleteSurvey(Integer surveyId);
     List<Survey> getSurveysToProcess();
     Integer processSurveys();
     void processQuestionsForSurvey(Survey survey);
     Integer sendSurveyQuestion(Integer surveyQuestionId, List<UserAudience> usersForSurvey);
} 