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

import com.second.CollaborationBack.DAO.JobDAO;
import com.second.CollaborationBack.model.Jobs;

@RestController
public class JobController {

	@Autowired
	private JobDAO jobDAO;

	public JobDAO getJobDAO() {
		return jobDAO;
	}
 
	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}
	
	@GetMapping("/jobs")
	public List getJobs() {
		return jobDAO.list();
	}
	
	@GetMapping("/jobs/{id}")
	public ResponseEntity getJob(@PathVariable("id") String id) {

		Jobs job = jobDAO.get(id);
		if (job == null) {
			return new ResponseEntity("No Job found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(job, HttpStatus.OK);
	}
	
	@PostMapping("/jobs")
	public ResponseEntity createjob(@RequestBody Jobs job) {

		jobDAO.saveOrUpdate(job);

		return new ResponseEntity(job, HttpStatus.OK);
	}
	@DeleteMapping("/jobs/{id}")
	public ResponseEntity deleteJob(@PathVariable String id) {
		Jobs job=jobDAO.get(id);
 		if (job==null) {
			return new ResponseEntity("No Job found for ID " + id, HttpStatus.NOT_FOUND);
		}
 		jobDAO.delete(id);
		return new ResponseEntity(id, HttpStatus.OK);

	}
	@PutMapping("/jobs/{id}")
	public ResponseEntity updateJob(@PathVariable String id, @RequestBody Jobs job) {

		job = jobDAO.saveOrUpdate(job);

		if (null == job) {
			return new ResponseEntity("No Job found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(job, HttpStatus.OK);
	}

	
}
