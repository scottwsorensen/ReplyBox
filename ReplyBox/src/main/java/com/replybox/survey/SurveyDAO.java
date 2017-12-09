package com.replybox.survey;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.replybox.survey.Survey;
import com.replybox.user.User;
@Transactional
@Repository
public class SurveyDAO implements ISurveyDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	@Override
	public Survey getSurveyById(Integer surveyId) {
		return entityManager.find(Survey.class, surveyId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> getAllSurveys() {
		String hql = "FROM Survey as survey ORDER BY survey.surveyId";
		return (List<Survey>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addSurvey(Survey survey) {
		entityManager.persist(survey);
	}
	@Override
	public void updateSurvey(Survey surv) {
		Survey survey = getSurveyById(surv.getSurveyId());
		survey.setCampaignId(surv.getCampaignId());
		survey.setCompanyId(surv.getCompanyId());
		survey.setSurveyName(surv.getSurveyName());
		survey.setStartDate(surv.getStartDate());
		survey.setEndDate(surv.getEndDate());
		survey.setIntervalDays(surv.getIntervalDays());
		survey.setAudienceId(surv.getAudienceId());
		entityManager.flush();
	}
	@Override
	public void deleteSurvey(Integer surveyId) {
		entityManager.remove(getSurveyById(surveyId));
	}
	@Override
	//{SWS} check over all oof these exists functions with Bri to make sure they match database.
	public boolean surveyExists(Integer companyId, String surveyName) {
		String hql = "FROM Survey as survey WHERE survey.companyId = ? and survey.surveyName = ?";
		Integer count = entityManager.createQuery(hql).setParameter(1, companyId)
		              .setParameter(2, surveyName).getResultList().size();
		return count > 0 ? true : false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> getSurveysToProcess() {
//		String hql = "select * from surveys where start_date <= CURDATE()  and end_date is null"; with createeNativeQuery(hql)
		String hql = "FROM Survey as survey WHERE survey.startDate <= CURDATE() AND survey.endDate = NULL";
		return (List<Survey>) entityManager.createQuery(hql).getResultList();
	}
} 