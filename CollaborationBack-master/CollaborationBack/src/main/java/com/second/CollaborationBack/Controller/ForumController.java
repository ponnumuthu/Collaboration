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

import com.second.CollaborationBack.DAO.ForumDAO;
import com.second.CollaborationBack.model.Forum;

@RestController
public class ForumController {

	@Autowired
	private ForumDAO forumDAO;
	
	public ForumDAO getForumDAO() {
		return forumDAO;
	}

	public void setForumDAO(ForumDAO forumDAO) {
		this.forumDAO = forumDAO;
	}

	@GetMapping("/forums")
	public ResponseEntity<List> getForums() {
		List listforum = forumDAO.list();
		return new ResponseEntity(listforum,HttpStatus.OK);
	}
	
	@GetMapping("/forums/{id}")
	public ResponseEntity getForum(@PathVariable("id") String id) {

		Forum forum = forumDAO.get(id);
		if (forum == null) {
			return new ResponseEntity("No Forum found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(forum, HttpStatus.OK);
	}
	
	@PostMapping(value = "/forums")
	public ResponseEntity createForum(@RequestBody Forum forum) {

		forumDAO.saveOrUpdate(forum);

		return new ResponseEntity(forum, HttpStatus.OK);
	}

	@DeleteMapping("/forums/{id}")
	public ResponseEntity deleteForum(@PathVariable String id) {
		Forum forum=forumDAO.get(id);
 		if (forum==null) {
			return new ResponseEntity("No Forum found for ID " + id, HttpStatus.NOT_FOUND);
		}
 		forumDAO.delete(id);
		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/forums/{id}")
	public ResponseEntity updateForum(@PathVariable String id, @RequestBody Forum forum) {

		forum = forumDAO.saveOrUpdate(forum);

		if (null == forum) {
			return new ResponseEntity("No Forum found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(forum, HttpStatus.OK);
	}

}
