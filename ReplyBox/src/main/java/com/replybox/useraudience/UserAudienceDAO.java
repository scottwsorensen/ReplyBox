package com.replybox.useraudience;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.replybox.useraudience.UserAudience;
@Transactional
@Repository
public class UserAudienceDAO implements IUserAudienceDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	@Override
	public UserAudience getUserAudienceById(Integer userAudienceId) {
		return entityManager.find(UserAudience.class, userAudienceId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserAudience> getAllUserAudiences() {
		String hql = "FROM UserAudience as userAudience ORDER BY userAudience.userAudienceId";
		return (List<UserAudience>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addUserAudience(UserAudience userAudience) {
		entityManager.persist(userAudience);
	}
	@Override
	public void updateUserAudience(UserAudience usrAud) {
		UserAudience userAudience = getUserAudienceById(usrAud.getUserAudienceId());
		userAudience.setUserId(usrAud.getUserId());
		userAudience.setAudienceId(usrAud.getAudienceId());
		entityManager.flush();
	}
	@Override
	public void deleteUserAudience(Integer userAudienceId) {
		entityManager.remove(getUserAudienceById(userAudienceId));
	}
	@Override
	public boolean userAudienceExists(Integer userId, Integer audienceId) {
		String hql = "FROM UserAudience as userAudience WHERE userAudience.surveyId = ? and userAudience.questionid = ?";
		Integer count = entityManager.createQuery(hql).setParameter(1, userId)
		              .setParameter(2, audienceId).getResultList().size();
		return count > 0 ? true : false;
	}
	@SuppressWarnings("unchecked")
	@Override
    public List<UserAudience> getUsersForSurvey(Integer audienceId) {
		String hql = "From UserAudience as userAudience WHERE userAudience.audienceId = ?";
		return (List<UserAudience>) entityManager.createQuery(hql).setParameter(1, audienceId).getResultList();
	}

} 