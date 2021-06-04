package com.fpt.intern.bestcv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpt.intern.bestcv.dto.RecommendDTO;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.CurriculumVitae;
import com.fpt.intern.bestcv.entity.Job;
import com.fpt.intern.bestcv.entity.RecruimentNews;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.CurriculumVitaeService;
import com.fpt.intern.bestcv.service.JobService;
import com.fpt.intern.bestcv.service.RecruimentNewsService;
import com.fpt.intern.bestcv.service.RecruiterService;



@Controller
@RequestMapping("/recommend")
public class RecommendController {
	private static final String DISPLAY_FEATURED_CV = "components/recommend/featuredCV";
	private static final String DISPLAY_FEATURED_RECRUITER = "components/recommend/featuredRecruiter";
	private static final String DISPLAY_RECOMMEND_RECRUITER = "components/recommend/recruiterRecommend";
	@Autowired
	RecruiterService recruiterService;
	
	@Autowired
	CandidateService candidateService;
	
	@Autowired
	CurriculumVitaeService curriculumViteaService;
	
	@PreAuthorize("hasRole('ROLE_NTD')")
	@RequestMapping(value = "/featuredCV")
	public String showFeaturedCVPage(Model model) {
		List<CurriculumVitae> curriculumVitae = curriculumViteaService.getAllCurriculumVitae();
		model.addAttribute("cvs", curriculumVitae);
		List<RecommendDTO> recommendDTO = recruiterService.getRecommendDTO();
		model.addAttribute("recommendDTOs", recommendDTO);
		return DISPLAY_FEATURED_CV;		
	}
	
	@RequestMapping(value = "/featured_recruiter")
	public String showFeaturedRecruiterPage(Model model) {
		List<RecommendDTO> recommendDTO = recruiterService.getRecommendDTO();
		model.addAttribute("recommendDTOs", recommendDTO);
			return DISPLAY_FEATURED_RECRUITER;		
	}
	
	@PreAuthorize("hasRole('ROLE_UTV')")
	@RequestMapping(value = "/recruiter_recommend")
	public String showRecruiterRecommendPa(Model model) {
		List<RecommendDTO> recommendDTO = recruiterService.getRecommendDTO();
		model.addAttribute("recommendDTOs", recommendDTO);
		return DISPLAY_RECOMMEND_RECRUITER;
	}
}
