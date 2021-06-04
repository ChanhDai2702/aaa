package com.fpt.intern.bestcv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.intern.bestcv.entity.Admin;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.service.AdminService;
import com.fpt.intern.bestcv.service.CandidateService;

@RestController
public class ad {
	@Autowired
	private CandidateService adminService;
	
	@RequestMapping(value = "/dsutv",method = RequestMethod.GET)
	public List<Candidate> getAdmins(){
		return adminService.getAllAcountCandidates();
		
	}
	
}
