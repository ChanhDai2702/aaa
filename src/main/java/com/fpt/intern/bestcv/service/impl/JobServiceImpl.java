package com.fpt.intern.bestcv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.Job;
import com.fpt.intern.bestcv.reposistory.JobReposistory;
import com.fpt.intern.bestcv.service.JobService;

@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	private JobReposistory jobReposistory;

	@Override
	public List<Job> getListAllJob() {
		return jobReposistory.findAll();
	}
	@Override
	public List<Job> getListJob() {
		
		return jobReposistory.findAll();
	}


	@Override
	public boolean deleteJob(int id) {
		if (jobReposistory.checkExistsJobinOtherTable(id)!=null) {
			return false;
		}
		if (jobReposistory.deleteJob(id)>0) {
			return true;
		}
		return false;
	}
    @Override
	public boolean saveJob(Job job) {
    	 Job x = jobReposistory.findJobByName(job.getJobName());
		if (x==null) {
			jobReposistory.save(job);
			return true;
		}
		else if (job.getId()== x.getId()) {
			x.setIndustry(job.getIndustry());
			jobReposistory.save(x);
			return true;
		}	
		return false;		
	}
	@Override
	public Job findJobByName(String jobName) {
		Optional<Job> job = jobReposistory.findByJobName(jobName);
		if(!job.isPresent())
			return null;
		return job.get();
	}
	@Override
	public void addJob(Job j) {
		// TODO Auto-generated method stub
		jobReposistory.save(j);
	}

	

	

	@Override
	public Job getJobbyID(int id) {
		Optional<Job> job =  jobReposistory.findById(id);
		if(!job.isPresent())
			return null;
		return job.get();
		
	}

	@Override
	public Job getJobbyIndustry(String name) {
		Optional<Job> job =  jobReposistory.findByJobName(name);
		if(!job.isPresent())
			return null;
		return job.get();
		
	}
}
