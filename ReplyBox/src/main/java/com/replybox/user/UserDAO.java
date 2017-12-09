package com.replybox.user;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.replybox.user.User;
@Transactional
@Repository
public class UserDAO implements IUserDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	@Override
	public User getUserById(Integer userId) {
		return entityManager.find(User.class, userId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String hql = "FROM User as user ORDER BY user.userId";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}
	@Override
	public void updateUser(User usr) {
		User user = getUserById(usr.getUserId());
		user.setFirstName(usr.getFirstName());
		user.setLastName(usr.getLastName());
		user.setEmail(usr.getEmail());
		user.setPhoneNumber(usr.getPhoneNumber());
		user.setOrgUnitId(usr.getOrgUnitId());
		entityManager.flush();
	}
	@Override
	public void deleteUser(Integer userId) {
		entityManager.remove(getUserById(userId));
	}
	@Override
	public boolean userExists(String firstName, String lastName) {
		String hql = "FROM User as user WHERE user.firstName = ? and user.lastName = ?";
		Integer count = entityManager.createQuery(hql).setParameter(1, firstName)
		              .setParameter(2, lastName).getResultList().size();
		return count > 0 ? true : false;
	}
	@Override
	public User getUserByPhone(String phoneNumber) {
		String hql = "FROM User as user WHERE user.phoneNumber = ?";
		return (User) entityManager.createQuery(hql).setParameter(1, phoneNumber).getSingleResult();		
	}
} 