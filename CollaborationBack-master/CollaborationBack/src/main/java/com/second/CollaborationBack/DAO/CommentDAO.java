package com.second.CollaborationBack.DAO;

import java.util.List;

import com.second.CollaborationBack.model.Comment;

public interface CommentDAO {

	public List<Comment> list();
	
	public Comment getForumComments(String forumId);
	
	public Comment getComment(String CommentId);
	
	public Comment saveOrUpdate(Comment comment);
		
	public void delete(String CommentId);

	
}
