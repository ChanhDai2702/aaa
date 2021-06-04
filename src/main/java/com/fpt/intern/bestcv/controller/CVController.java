package com.fpt.intern.bestcv.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpt.intern.bestcv.model.LoggedUser;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.CurriculumVitaeDetailService;
import com.fpt.intern.bestcv.service.CurriculumVitaeService;
import com.fpt.intern.bestcv.service.EducationService;
import com.fpt.intern.bestcv.service.ExperienceService;
import com.fpt.intern.bestcv.service.FilesStorageService;
import com.fpt.intern.bestcv.service.HobbyDetailService;
import com.fpt.intern.bestcv.service.JobGoalDetailService;
import com.fpt.intern.bestcv.service.JobService;
import com.fpt.intern.bestcv.service.SkillDetailService;

@Controller
@RequestMapping(value = CVController.URL_CV)
public class CVController {
	public static final String URL_CV = "/cv";
	public static String listCVADMIN = "/components/cv/listCV_ADMIN";
	public static String listCVNTD = "/components/cv/listCV_NTD";
	public static String listCVUTV = "/components/cv/listCV_UTV";

	@Autowired
	private CurriculumVitaeDetailService curriculumVitaeDetailService;
	
	@Autowired
	private CurriculumVitaeService curriculumVitaeService;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private AspNetUsersService aspNetUsersService;
	
	@Autowired
	private HobbyDetailService hobbyDetailService;
	
	@Autowired
	private SkillDetailService skillDetailService;
	
	@Autowired
	private JobGoalDetailService jobGoalDetailService;
	
	@Autowired
	private EducationService educationService;
	@Autowired
	
	private ExperienceService experienceService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	FilesStorageService storageService;
	
	@GetMapping(value = "listCV")
	public String getListCV_ADMIN(Model model,Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			model.addAttribute("cvs", curriculumVitaeService.getAllCurriculumVitae());
			return listCVADMIN;
		}else if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_NTD"))){
			model.addAttribute("cvs", curriculumVitaeService.getAllCurriculumVitaeApprove());
			return listCVNTD;
		}
		else {
			model.addAttribute("cvs", curriculumVitaeService.getCurriculumVitaeByCandidateId(candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(principal.getName()).getId()).getId()));
			model.addAttribute("jobCV",jobService.getListAllJob());
			return listCVUTV;
		}
	}
		
	@RequestMapping(value = "searchCV")
	public String searchCVByName(@RequestParam(value = "keyword",required = false) String keyword, Model model,Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			model.addAttribute("cvs", curriculumVitaeService.getAllCurriculumVitaeByKeyWordForAdmin(keyword));
			return listCVADMIN;
		}else if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_NTD"))){
			model.addAttribute("cvs", curriculumVitaeService.getAllCurriculumVitaeByKeyWordForNTD(keyword));
			return listCVNTD;
		}
		else {
			int candidateId = candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(principal.getName()).getId()).getId();
			model.addAttribute("cvs", curriculumVitaeService.getAllCurriculumVitaeByKeyWordForUTV(keyword,candidateId));
			return listCVUTV;
		}
	}
	
	@GetMapping(value = "list/approve")
	public String getListCVApprove(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			model.addAttribute("cvs", curriculumVitaeService.getAllCurriculumVitaeApprove());
			return listCVADMIN;
		}
		else {
			return "401";
		}
	}
	
	@GetMapping(value = "list/dontApprove")
	public String getListCVDontApprove(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			model.addAttribute("cvs", curriculumVitaeService.getAllCurriculumVitaeDontApprove());
			return listCVADMIN;
		}
		else {
			return "401";
		}
	}

	@GetMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") int id, Model model,HttpServletRequest request) {
		int curriculumVitaeDetailId = curriculumVitaeDetailService.findBCurriculumVitaeDetailIdCurriculumVitae(id).getId();
		hobbyDetailService.deleteHobbyDetailByCurriculumVitaeDetailId(curriculumVitaeDetailId);
		educationService.deleteEducationByCurriculumVitaeDetailId(curriculumVitaeDetailId);
		experienceService.deleteExperienceByCurriculumVitaeDetailId(curriculumVitaeDetailId);
		jobGoalDetailService.deleteJobGoalDetailByCurriculumVitaeDetailId(curriculumVitaeDetailId);
		skillDetailService.deleteSkillDetailByCurriculumVitaeDetailId(curriculumVitaeDetailId);
		curriculumVitaeDetailService.deleteByCurriculumVitaeDetailId(curriculumVitaeDetailId);
		curriculumVitaeService.deleteById(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return "redirect:/cv/listCV";
		}
		else {
			return "redirect:/cv/listCV";
		}
	}
	
	@GetMapping(value = "approve/{id}")
	public String approve(@PathVariable("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			curriculumVitaeService.findByIdAndUpdateApprove(id);
			return "redirect:/cv/listCV";
		}
		else {
			return "401";
		}
	}

}

