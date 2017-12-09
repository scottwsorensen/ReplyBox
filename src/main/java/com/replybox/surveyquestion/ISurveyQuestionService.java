package com.replybox.surveyquestion;

import java.util.List;

import com.replybox.surveyquestion.SurveyQuestion;
public interface ISurveyQuestionService {
     List<SurveyQuestion> getAllSurveyQuestions();
     SurveyQuestion getSurveyQuestionById(Integer surveyQuestionId);
     boolean addSurveyQuestion(SurveyQuestion surveyQuestion);
     void updateSurveyQuestion(SurveyQuestion surveyQuestion);
     void deleteSurveyQuestion(Integer surveyQuestionId);
     List<SurveyQuestion>getQuestionsForSurvey(Integer surveyId);
} 