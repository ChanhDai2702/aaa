package com.fpt.intern.bestcv.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.dto.RecommendDTO;
import com.fpt.intern.bestcv.entity.Admin;
import com.fpt.intern.bestcv.entity.AspNetRoles;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.CurriculumVitae;
import com.fpt.intern.bestcv.entity.CurriculumVitaeDetail;
import com.fpt.intern.bestcv.entity.Notification;
import com.fpt.intern.bestcv.entity.RecruimentDetail;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.model.LoggedUser;
import com.fpt.intern.bestcv.service.AdminService;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.CurriculumVitaeDetailService;
import com.fpt.intern.bestcv.service.CurriculumVitaeService;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.PriorityAddressService;
import com.fpt.intern.bestcv.service.RecruimentDetailService;
import com.fpt.intern.bestcv.service.RecruimentNewsCandidatesService;
//import com.fpt.intern.bestcv.service.RecruimentNewsService;
import com.fpt.intern.bestcv.service.RecruiterService;
import com.fpt.intern.bestcv.utility.PageURLUtility;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private AspNetUsersService aspNetUsersService;

	@Autowired
	private RecruiterService recruiterService;

	@Autowired
	private IndustryService industryService;

	@Autowired
	private PriorityAddressService priorityAddressService;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private RecruimentDetailService recruimentDetailService;

	//	@Autowired
	//	private RecruimentNewsService recruimentNewsService;

	@Autowired
	private RecruimentNewsCandidatesService recruimentNewsCandidatesService;

	@Autowired
	private CurriculumVitaeDetailService curriculumVitaeDetailService;

	@Autowired
	private CurriculumVitaeService curriculumVitaeService;
	@Autowired
	private AdminService adminService;

	@Autowired
	CurriculumVitaeService curriculumViteaService;

	@GetMapping
	public String getHomePage(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1")String strPageNum) {
		model.addAttribute("industry", industryService.getListAllIndustry());
		model.addAttribute("priorityAddress", priorityAddressService.findAll());
		String redirect="home";
		int pageNum = Integer.parseInt(strPageNum);
		if (pageNum <= 0)
			pageNum = 1;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_NTD"))) {
			redirect=featuredCV(model,strPageNum,auth); 
		}
		else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_UTV"))){
			redirect=recruiterRecommend(model,strPageNum, auth);
		}

		else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
			redirect=recommendForAdmin(model,strPageNum); 
		}
		else {
			redirect=recommendForViewer(model,strPageNum);
		}
		model.addAttribute("pageNum", pageNum);
		return redirect;
	}

	// Need delete after test
	@RequestMapping("/autologin")
	public String doLoginWithAdmin(@RequestParam(name = "email") String email) {
		AspNetUsers user = aspNetUsersService.getAspNetUsersByEmail(email);
		LoggedUser loggedUser = new LoggedUser();
		loggedUser.setUsers(user);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Set<AspNetRoles> roles = user.getRoles();
		for (AspNetRoles role : roles) {
			switch (role.getName()) {
			case "ROLE_ADMIN":
				Admin admin = adminService.getAdminByUsersId(user.getId());
				loggedUser.setInfo(admin);
				break;
			case "ROLE_NTD":
				Recruiter recruiter = recruiterService.getRecruiterByUsersId(user.getId());
				loggedUser.setInfo(recruiter);
				break;
			case "ROLE_UTV":
				Candidate candidate = candidateService.getCandidateByUsersId(user.getId());
				loggedUser.setInfo(candidate);
				break;
			default:
				break;
			}
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
			break;
		}
		Authentication authentication = new UsernamePasswordAuthenticationToken(loggedUser, null, grantedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}

	// Need delete after test
	@RequestMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	public String recruiterRecommend(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1")String strPageNum, Principal principal) {
		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName());
		Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsers.getId());

		int pageNum = Integer.parseInt(strPageNum);
		if (pageNum <= 0)
			pageNum = 1;


		Page<Recruiter> pages= recruiterService.getRecruiterByIndustryIds(candidate.getIndustry().getId(), pageNum, 9);


		if (pages.getTotalPages() != 0 && pageNum > pages.getTotalPages()) {
			return PageURLUtility.PAGE_404;
		}
		else
		{
			HashMap<Integer, List<RecommendDTO>> maps= new HashMap<Integer, List<RecommendDTO>>();
			List<Recruiter> recruiters = pages.getContent();
			for (Recruiter recruiter : pages) {
				maps.put(recruiter.getId(), recruiterService.getRecommendDTO(recruiter.getId()));
			}
			model.addAttribute("recruiters", recruiters);
			model.addAttribute("jobs", maps);
			List<RecommendDTO> recommendDTO = recruiterService.getRecommendDTO();
			model.addAttribute("recommendDTOs", recommendDTO);
			model.addAttribute("currentPage", pageNum);
			model.addAttribute("totalPages", pages.getTotalPages());
			model.addAttribute("totalItems", pages.getTotalElements());
			return "recruiterRecommend";
		}	
	}
	
	public String featuredCV(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1")String strPageNum, Principal principal){

		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName());
		Recruiter recruiter = recruiterService.getRecruiterByUsersId(aspNetUsers.getId());
		int pageNum = Integer.parseInt(strPageNum);
		if (pageNum <= 0)
			pageNum = 1;
		
		Page<CurriculumVitae> pages = curriculumViteaService.getCVByIndustryID(recruiter.getIndustry().getId(), pageNum,9);

		if (pages.getTotalPages() != 0 && pageNum > pages.getTotalPages()) {
			return PageURLUtility.PAGE_404;
		}
		else
		{
			model.addAttribute("cvs", pages);
			List<RecommendDTO> recommendDTO = recruiterService.getRecommendDTO();
			List<CurriculumVitae> cvInID = curriculumVitaeService.getCVByIndustryID(recruiter.getIndustry().getId());
			model.addAttribute("cvInIDs", cvInID);	 
			model.addAttribute("currentPage", pageNum);
			model.addAttribute("totalPages", pages.getTotalPages());
			model.addAttribute("totalItems", pages.getTotalElements());
			model.addAttribute("recommendDTOs", recommendDTO);
			return "featuredCV";
		}
	}
	
	public String recommendForViewer(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1")String strPageNum){
		int pageNum = Integer.parseInt(strPageNum);
		if (pageNum <= 0)
			pageNum = 1;

		Page<Recruiter> pages = recruiterService.getAllRecruiters(pageNum, 9);
		if (pages.getTotalPages() != 0 && pageNum > pages.getTotalPages()) {
			return PageURLUtility.PAGE_404;
		}
		else
		{
			List<CurriculumVitae> curriculumVitae = curriculumViteaService.getAllCvApproved();
			model.addAttribute("cvs", curriculumVitae); 
			model.addAttribute("recruiters", pages); 
			HashMap<Integer, List<RecommendDTO>> maps = new HashMap<Integer, List<RecommendDTO>>(); 
			for (Recruiter recruiter : pages) {
				maps.put(recruiter.getId(), recruiterService.getRecommendDTO(recruiter.getId())); 
			}
			model.addAttribute("jobs", maps); 
			List<RecommendDTO> recommendDTO = recruiterService.getRecommendDTO(); 
			model.addAttribute("recommendDTOs", recommendDTO);
			model.addAttribute("currentPage", pageNum);
			model.addAttribute("totalPages", pages.getTotalPages());
			model.addAttribute("totalItems", pages.getTotalElements());
			return "recommendForViewer";
		}
	}


	public String recommendForAdmin(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1")String strPageNum){
		int pageNum = Integer.parseInt(strPageNum);
		if (pageNum <= 0)
			pageNum = 1;

		Page<CurriculumVitae> pages = curriculumVitaeService.getAllCVPages(pageNum, 9);
		if (pages.getTotalPages() != 0 && pageNum > pages.getTotalPages()) {
			return PageURLUtility.PAGE_404;
		}
		else
		{
			List<Recruiter> recruiter = recruiterService.getAllByRecruiter();
			model.addAttribute("cvs", pages); 
			model.addAttribute("recruiters", recruiter); 
			List<RecommendDTO> recommendDTO = recruiterService.getRecommendDTO(); 
			model.addAttribute("recommendDTOs", recommendDTO);
			model.addAttribute("currentPage", pageNum);
			model.addAttribute("totalPages", pages.getTotalPages());
			model.addAttribute("totalItems", pages.getTotalElements());
			return "recommendForAdmin";
		}
	}
}
