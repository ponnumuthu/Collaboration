package com.second.CollaborationBack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.second.CollaborationBack.DAO.FriendListDAO;

@RestController
public class FriendController {

	
	@Autowired
	private FriendListDAO friendListDAO;

	public FriendListDAO getFriendListDAO() {
		return friendListDAO;
	}

	public void setFriendListDAO(FriendListDAO friendListDAO) {
		this.friendListDAO = friendListDAO;
	}
	
	@GetMapping("/friendsList/{userId}")
	public ResponseEntity<List> getFriendList(@PathVariable ("userId") String userId) {
		
		List listuser = friendListDAO.friendList(userId);
		return new ResponseEntity(listuser,HttpStatus.OK);
	}
	@GetMapping("/friendRequest/{userId}")
	public ResponseEntity<List> getFriendRequests(@PathVariable ("userId") String userId) {
		
		List listuser = friendListDAO.friendRequestList(userId);
		return new ResponseEntity(listuser,HttpStatus.OK);
	}
	
}
