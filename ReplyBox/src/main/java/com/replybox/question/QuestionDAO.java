package com.replybox.question;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.replybox.question.Question;
@Transactional
@Repository
public class QuestionDAO implements IQuestionDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	@Override
	public Question getQuestionById(Integer questionId) {
		return entityManager.find(Question.class, questionId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllQuestions() {
		String hql = "FROM Question as question ORDER BY question.questionId";
		return (List<Question>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addQuestion(Question question) {
		entityManager.persist(question);
	}
	@Override
	public void updateQuestion(Question qstn) {
		Question question = getQuestionById(qstn.getQuestionId());
		question.setQuestionText(qstn.getQuestionText());
		question.setCategoryId(qstn.getCategoryId());
		question.setTestData(qstn.getTestData());
		entityManager.flush();
	}
	@Override
	public void deleteQuestion(Integer questionId) {
		entityManager.remove(getQuestionById(questionId));
	}
	@Override
	public boolean questionExists(String questionText, Integer categoryId) {
		//{SWS} position parameters are deprecated.
		String hql = "FROM Question as question WHERE question.questionText = ? and question.categoryId = ?";
		Integer count = entityManager.createQuery(hql).setParameter(1, questionText)
		              .setParameter(2, categoryId).getResultList().size();
		return count > 0 ? true : false;
	}
} 