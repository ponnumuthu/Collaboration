package com.second.CollaborationBack.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.second.CollaborationBack.model.Chat;



public class ChatDAOImpl implements ChatDAO {

	
	
	@Autowired
	private SessionFactory sessionFactory;

	public ChatDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Chat> list() {
		@SuppressWarnings({ "unchecked" })
		List<Chat> listChat = (List<Chat>) sessionFactory.getCurrentSession().createCriteria(Chat.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listChat;
	}
	@Transactional
	public Chat get(String sender) {
		String hql = "from Chat where sender ='" + sender + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Chat> listChat = (List<Chat>) query.list();
		if (listChat != null && !listChat.isEmpty()) {
			return listChat.get(0);
		}
		return null;
	}
	@Transactional
	public void saveOrUpdate(Chat chat) {
		sessionFactory.getCurrentSession().saveOrUpdate(chat);

	}
	@Transactional
	public void delete(String sender) {
		Chat chatToDelete = new Chat();
		chatToDelete.setSender(sender);
		sessionFactory.getCurrentSession().delete(chatToDelete);

	}

}
