package com.second.CollaborationBack.DAO;

import java.util.List;

import com.second.CollaborationBack.model.Jobs;

public interface JobDAO {

	public List<Jobs> list();
	
	public Jobs get(String jobsId);
	
	public Jobs saveOrUpdate(Jobs jobs);
		
	public void delete(String jobsId);
}
