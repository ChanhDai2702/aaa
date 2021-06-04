package com.fpt.intern.bestcv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.entity.Job;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.JobService;

@org.springframework.web.bind.annotation.RestController
public class IndustryJobRestController {
	@Autowired
	private JobService jobService;
	@Autowired
	private IndustryService industryService;

	@GetMapping("listJob")
	public List<Job> getListJob() {
		List<Job> jobs = jobService.getListJob();
		jobs.forEach(x -> {
			x.getIndustry().setJobs(null);
		});
		return jobs;
	}

	@GetMapping("listIns")
	public List<Industry> getListIns() {
		List<Industry> industries = industryService.getListIn();
		industries.forEach(x -> {
			x.setJobs(null);
		});
		return industries;
	}
}
