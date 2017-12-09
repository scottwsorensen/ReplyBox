package com.replybox.surveyquestion;

import java.util.List;

import com.replybox.question.Question;
import com.replybox.surveyquestion.SurveyQuestion;
public interface ISurveyQuestionDAO {
    List<SurveyQuestion> getAllSurveyQuestions();
    SurveyQuestion getSurveyQuestionById(Integer surveyQuestionId);
    void addSurveyQuestion(SurveyQuestion surveyQuestion);
    void updateSurveyQuestion(SurveyQuestion surveyQuestion);
    void deleteSurveyQuestion(Integer surveyQuestionId);
    boolean surveyQuestionExists(Integer surveyId, Integer questionId);
	List<SurveyQuestion> getQuestionsForSurvey(Integer surveyId);
} 
