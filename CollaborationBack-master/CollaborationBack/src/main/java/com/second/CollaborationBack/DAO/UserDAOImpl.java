package com.second.CollaborationBack.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.second.CollaborationBack.model.User;

@Component
public class UserDAOImpl implements UserDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public UserDAOImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List list() {
		// TODO Auto-generated method stub
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<User> listUser = (List<User>)
		sessionFactory.getCurrentSession().createCriteria(User.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUser;
		 
	}

	
	@Transactional	
	public User create(User users) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(users);
		return users;
	}
	@Transactional	
	public void delete(Long id) {
		User usertoDelete=new User();
		usertoDelete.setUserId(id);
		sessionFactory.getCurrentSession().delete(usertoDelete);
		}
	@Transactional	
	public User update(User users) {
		sessionFactory.getCurrentSession().saveOrUpdate(users);
		return users;
	}
	@Transactional	
	public User get(Long id) {
		String hql = "from User where user_id =" + "'"+ id +"'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		
		if (listUser != null && !listUser.isEmpty()){
			return listUser.get(0);
		}
		return null;
}

	@Transactional
	public User getbyUsername(String username) {
		// TODO Auto-generated method stub
		String hql = "from User where userName =" + "'"+ username +"'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		
		if (listUser != null && !listUser.isEmpty()){
			return listUser.get(0);
		}
		return null;
	}
}