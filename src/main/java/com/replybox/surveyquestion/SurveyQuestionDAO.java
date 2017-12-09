package com.replybox.surveyquestion;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.replybox.question.Question;
import com.replybox.surveyquestion.SurveyQuestion;
@Transactional
@Repository
public class SurveyQuestionDAO implements ISurveyQuestionDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	@Override
	public SurveyQuestion getSurveyQuestionById(Integer surveyQuestionId) {
		return entityManager.find(SurveyQuestion.class, surveyQuestionId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyQuestion> getAllSurveyQuestions() {
		String hql = "FROM SurveyQuestion as surveyQuestion ORDER BY surveyQuestion.surveyQuestionId";
		return (List<SurveyQuestion>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addSurveyQuestion(SurveyQuestion surveyQuestion) {
		entityManager.persist(surveyQuestion);
	}
	@Override
	public void updateSurveyQuestion(SurveyQuestion svcqstn) {
		SurveyQuestion surveyQuestion = getSurveyQuestionById(svcqstn.getSurveyQuestionId());
		surveyQuestion.setSurveyId(svcqstn.getSurveyId());
		surveyQuestion.setQuestionId(svcqstn.getQuestionId());
		surveyQuestion.setSendOrder(svcqstn.getSendOrder());
		surveyQuestion.setSendSuccess(svcqstn.getSendSuccess());
		entityManager.flush();
	}
	@Override
	public void deleteSurveyQuestion(Integer surveyQuestionId) {
		entityManager.remove(getSurveyQuestionById(surveyQuestionId));
	}
	@Override
	public boolean surveyQuestionExists(Integer surveyId, Integer questionId) {
		String hql = "FROM SurveyQuestion as surveyQuestion WHERE surveyQuestion.surveyId = ? and surveyQuestion.questionid = ?";
		Integer count = entityManager.createQuery(hql).setParameter(1, surveyId)
		              .setParameter(2, questionId).getResultList().size();
		return count > 0 ? true : false;
	}
	@SuppressWarnings("unchecked")
	@Override
    public List<SurveyQuestion> getQuestionsForSurvey(Integer survId) {
		//{SWS} need to use the deprecated way for now until I can make it work in an approved way
		String hql = "From SurveyQuestion as surveyQuestion WHERE surveyQuestion.surveyId = ? ORDER BY surveyQuestion.sendOrder";
		List<SurveyQuestion> survList = (List<SurveyQuestion>) entityManager.createQuery(hql).setParameter(1, survId).getResultList();
		return survList;
	}

} 