package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.entity.Job;

public interface JobService {
	List<Job> getListAllJob();
	List<Job> getListJob();

	boolean saveJob(Job x);
	boolean  deleteJob(int id);
	void addJob(Job j) ;
	Job getJobbyID(int id);
	Job getJobbyIndustry(String name);
	Job findJobByName(String jobName);
}
