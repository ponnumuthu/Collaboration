package com.second.CollaborationBack.DAO;

import java.util.List;

import com.second.CollaborationBack.model.Chat;

public interface ChatDAO {

	public List<Chat> list();
	
	public Chat get(String sender);
	
	public void saveOrUpdate(Chat chat);
		
	public void delete(String sender);
}
