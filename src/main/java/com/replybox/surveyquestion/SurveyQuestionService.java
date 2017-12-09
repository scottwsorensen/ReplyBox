package com.replybox.surveyquestion;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.replybox.surveyquestion.ISurveyQuestionDAO;
import com.replybox.surveyquestion.SurveyQuestion;
@Service
public class SurveyQuestionService implements ISurveyQuestionService {
	@Autowired
	private ISurveyQuestionDAO surveyQuestionDAO;
	@Override
	public SurveyQuestion getSurveyQuestionById(Integer surveyQuestionId) {
		SurveyQuestion obj = surveyQuestionDAO.getSurveyQuestionById(surveyQuestionId);
		return obj;
	}	
	@Override
	public List<SurveyQuestion> getAllSurveyQuestions(){
		return surveyQuestionDAO.getAllSurveyQuestions();
	}
	@Override
	public synchronized boolean addSurveyQuestion(SurveyQuestion surveyQuestion){
        if (surveyQuestionDAO.surveyQuestionExists(surveyQuestion.getSurveyId(), surveyQuestion.getQuestionId())) {
            return false;
        } else {
            surveyQuestionDAO.addSurveyQuestion(surveyQuestion);
            return true;
        }
	}
	@Override
	public void updateSurveyQuestion(SurveyQuestion surveyQuestion) {
		surveyQuestionDAO.updateSurveyQuestion(surveyQuestion);
	}
	@Override
	public void deleteSurveyQuestion(Integer surveyQuestionId) {
		surveyQuestionDAO.deleteSurveyQuestion(surveyQuestionId);
	}
	@Override
    public List<SurveyQuestion> getQuestionsForSurvey(Integer surveyId) {
		List<SurveyQuestion> questionsForSurvey = surveyQuestionDAO.getQuestionsForSurvey(surveyId);
		return questionsForSurvey;
	}
} 