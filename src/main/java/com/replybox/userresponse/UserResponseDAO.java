package com.replybox.userresponse;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.replybox.userresponse.UserResponse;
@Transactional
@Repository
public class UserResponseDAO implements IUserResponseDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	@Override
	public UserResponse getUserResponseById(Integer userResponseId) {
		return entityManager.find(UserResponse.class, userResponseId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserResponse> getAllUserResponses() {
		String hql = "FROM UserResponse as userResponse ORDER BY userResponse.userResponseId";
		return (List<UserResponse>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addUserResponse(UserResponse userResponse) {
		entityManager.persist(userResponse);
	}
	@Override
	public void updateUserResponse(UserResponse resp) {
		UserResponse userResponse = getUserResponseById(resp.getUserResponseId());
		userResponse.setUserId(resp.getUserId());
		userResponse.setSurveyQuestionId(resp.getSurveyQuestionId());
		userResponse.setQuestionResponseId(resp.getQuestionResponseId());
		userResponse.setAskTime(resp.getAskTime());
		userResponse.setAnswerTime(resp.getAnswerTime());
		userResponse.setCustomAnswer(resp.getCustomAnswer());
		entityManager.flush();
	}
	@Override
	public void deleteUserResponse(Integer userResponseId) {
		entityManager.remove(getUserResponseById(userResponseId));
	}
	@Override
	public boolean userResponseExists(Integer userId, Integer surveyQuestionId) {
		String hql = "FROM UserResponse as userResponse WHERE userResponse.userId = ? and userResponse.surveyQuestionId = ?";
		Integer count = entityManager.createQuery(hql).setParameter(1, userId)
		              .setParameter(2, surveyQuestionId).getResultList().size();
		return count > 0 ? true : false;
	}
    @Override
    public List<UserResponse> getUserResponsesByUserId(Integer userId) {
		String hql = "FROM UserResponse as userResponse WHERE userResponse.userId = ?";
		return (List<UserResponse>) entityManager.createQuery(hql).setParameter(1, userId).getResultList();
    }

} 