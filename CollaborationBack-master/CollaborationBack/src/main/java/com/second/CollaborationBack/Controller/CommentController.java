package com.second.CollaborationBack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.second.CollaborationBack.DAO.CommentDAO;
import com.second.CollaborationBack.model.Comment;

@RestController
public class CommentController {

	@Autowired
	private CommentDAO commentDAO;

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	
	@GetMapping("/comments")
	public ResponseEntity<List> getComments() {
		List listcomment = commentDAO.list();
		return new ResponseEntity(listcomment,HttpStatus.OK);
	}
	
	@GetMapping("/comments/{forumId}")
	public ResponseEntity getComment(@PathVariable("forumId") String forumId) {

		Comment comment = commentDAO.getForumComments(forumId);
		if (comment == null) {
			return new ResponseEntity("No Comment found for ID " + forumId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(comment, HttpStatus.OK);
	}
	
	@PostMapping(value = "/comments")
	public ResponseEntity createComment(@RequestBody Comment comment) {

		commentDAO.saveOrUpdate(comment);

		return new ResponseEntity(comment, HttpStatus.OK);
	}

	@DeleteMapping("/comments/{id}")
	public ResponseEntity deleteComment(@PathVariable String id) {
		Comment comment=commentDAO.getComment(id);
 		if (comment==null) {
			return new ResponseEntity("No Comment found for ID " + id, HttpStatus.NOT_FOUND);
		}
 		commentDAO.delete(id);
		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/comments/{id}")
	public ResponseEntity updateComment(@PathVariable String id, @RequestBody Comment comment) {

		comment = commentDAO.saveOrUpdate(comment);

		if (null == comment) {
			return new ResponseEntity("No Comment found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(comment, HttpStatus.OK);
	}
}
