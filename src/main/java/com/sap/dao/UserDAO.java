package com.sap.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.exceptions.UserAlreadyExistsException;
import com.sap.models.User;

@Repository
@Transactional
public class UserDAO {

	private static final Logger logger = Logger.getLogger(UserDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void addUser(User u) throws UserAlreadyExistsException {
		boolean userAlreadyExist = this.getUserByName(u.getUsername()) != null;
		if (userAlreadyExist) {
			throw new UserAlreadyExistsException();
		}

		u.setPassword(passwordEncoder.encode(u.getPassword()));
		 this.sessionFactory.getCurrentSession().persist(u);
		logger.info("User saved successfully: " + u);
	}

	public User getUserByName(String username) {
		User u = (User) this.sessionFactory.getCurrentSession().createQuery("from User where username = '" + username + "'").uniqueResult();
		logger.info("User loaded successfully: " + u);
		return u;
	}
	
	public User getUserById(int id) {
		User u = (User) this.sessionFactory.getCurrentSession().get(User.class, new Integer(id));
		logger.info("User loaded successfully: " + u);
		return u;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserList() {
		List<User> userList = this.sessionFactory.getCurrentSession().createCriteria(User.class).list();
		logger.info("User List loaded.");
		return userList;
	}

	public int getUserIdByName(String name) {
		return this.getUserByName(name).getId();
	}

	public boolean checkCredentials(User user) {
		User u = (User) this.sessionFactory.getCurrentSession().get(User.class, user.getId());
		if (u != null && u.getPassword().equals(user.getPassword())) {
			return true;
		}
		logger.info("User loaded successfully: " + u);
		return false;
	}
}
