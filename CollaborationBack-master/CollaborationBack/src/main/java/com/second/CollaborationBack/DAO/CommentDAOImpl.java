package com.second.CollaborationBack.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.second.CollaborationBack.model.Comment;



public class CommentDAOImpl implements CommentDAO {

	
	
	@Autowired
	private SessionFactory sessionFactory;

	public CommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Comment> list() {
		@SuppressWarnings({ "unchecked" })
		List<Comment> listComment = (List<Comment>) sessionFactory.getCurrentSession().createCriteria(Comment.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listComment;
	}
	@Transactional
	public Comment getForumComments(String forumId) {
		String hql = "from Comment where forumId ='" + forumId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Comment> listComment = (List<Comment>) query.list();
		if (listComment != null && !listComment.isEmpty()) {
			return listComment.get(0);
		}
		return null;
	}
	@Transactional
	public Comment saveOrUpdate(Comment comment) {
		sessionFactory.getCurrentSession().saveOrUpdate(comment);
		return comment;

	}
	@Transactional
	public void delete(String CommentId) {
		Comment commentToDelete = new Comment();
		commentToDelete.setCommentId(CommentId);
		sessionFactory.getCurrentSession().delete(commentToDelete);

	}

	@Transactional
	public Comment getComment(String CommentId) {
		String hql = "from Comment where CommentId ='" + CommentId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Comment> listComment = (List<Comment>) query.list();
		if (listComment != null && !listComment.isEmpty()) {
			return listComment.get(0);
		}
		return null;
	}

}
