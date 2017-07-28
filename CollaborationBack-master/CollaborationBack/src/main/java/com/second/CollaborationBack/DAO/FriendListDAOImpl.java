package com.second.CollaborationBack.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.second.CollaborationBack.model.FriendList;



public class FriendListDAOImpl implements FriendListDAO {

	
	@Autowired
	private SessionFactory sessionFactory;

	public FriendListDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public List<FriendList> friendList(String userId) {
		String hql = "from FriendList where userId=" + "'" + userId + "'   and status = " + "'A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<FriendList> list = (List<FriendList>) query.list();
		
		return list;
	}
	@Transactional
	public List<FriendList> friendRequestList(String userId) {
		String hql = "from FriendList where userId=" + "'" + userId + "'   and status = " + "'N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<FriendList> list = (List<FriendList>) query.list();
		
		return list;
	}
	@Transactional
	public FriendList get(String friendId) {
		String hql = "from FriendList where friendId ='" + friendId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<FriendList> listFriendList = (List<FriendList>) query.list();
		if (listFriendList != null && !listFriendList.isEmpty()) {
			return listFriendList.get(0);
		}
		return null;
	}
	@Transactional
	public void saveOrUpdate(FriendList friendList) {
		sessionFactory.getCurrentSession().saveOrUpdate(friendList);

	}
	@Transactional
	public void delete(String friendId) {
		FriendList friendToDelete = new FriendList();
		friendToDelete.setFriendId(friendId);
		sessionFactory.getCurrentSession().delete(friendToDelete);
	}

}
