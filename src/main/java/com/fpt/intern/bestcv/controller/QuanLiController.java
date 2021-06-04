package com.fpt.intern.bestcv.controller;

import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.dto.CandidateDTO;
import com.fpt.intern.bestcv.dto.CandidateUserRoleDTO;
import com.fpt.intern.bestcv.dto.RecruiterUserRoleDTO;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Display;
import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.entity.PriorityAddress;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.reposistory.AspNetUsersReposistory;
import com.fpt.intern.bestcv.reposistory.CandidateReposistory;
import com.fpt.intern.bestcv.service.AspNetRolesService;
import com.fpt.intern.bestcv.service.AspNetUserLoginsServcie;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateDTOservice;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.CandidateUserRoleDTOService;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.PriorityAddressService;
import com.fpt.intern.bestcv.service.RecruiterDTOservice;
import com.fpt.intern.bestcv.service.RecruiterService;
import com.fpt.intern.bestcv.service.RecruiterUserRoleDTOService;

@Controller

public class QuanLiController {

	@Autowired
	CandidateService candidateService;
	
	
	@Autowired RecruiterService recruiterService;

	@Autowired
	CandidateDTOservice candidateDTOservice;

	@Autowired
	AspNetUsersService aspNetUsersService;

	@Autowired
	RecruiterDTOservice recruiterDTOservice;

	@Autowired
	AspNetUserLoginsServcie AspNetUserLoginsServcie;

	@Autowired
	AspNetRolesService aspNetRolesService;

	@Autowired
	CandidateUserRoleDTOService candidateUserRoleDTOService;

	@Autowired
	RecruiterUserRoleDTOService recruiterUserRoleDTOService;

	@Autowired
	CandidateReposistory candidateReposistory;

	@Autowired
	AspNetUsersReposistory aspNetUsersReposistory;

	@Autowired
	PriorityAddressService priorityAddressService;

	@Autowired
	IndustryService industryService;

	
	

	@RequestMapping(value = "/formupdateUCV")
	public String showadminNTD(Model model, Model model2) {
		return "FormInfoAcount";
	}
	
	static List<String> listgioitinh = Arrays.asList("Nam", "Nữ");
	static List<String> trangthai = Arrays.asList("TRUE","FALSE");

	@RequestMapping(value = "/CandidateAccount")
	public String showadminUCV(Model model, HttpServletRequest request, RedirectAttributes redirect) {
	
		model.addAttribute("listAcount", candidateUserRoleDTOService.getAllAcountCandidatesUser());
		return "CandidateAcount";
	}

	//danh sach tai khoan NTD
	@RequestMapping(value = "/RecruiterAccount")
	public String showaAcountRecruiter(Model model,HttpServletRequest request, RedirectAttributes redirect) {
		model.addAttribute("listAcountRecruiter",recruiterUserRoleDTOService.getAllAcountRecruiterUser());
		return "RecruiterAcount";
	}
//tim kiem UCV
	@RequestMapping("/timkiem")
	public String showSearchUCV(Model model, HttpServletRequest request, RedirectAttributes redirect,
			@RequestParam("name") String name, Principal principal) {
		System.out.println("Name " + name);
		List<CandidateUserRoleDTO> candidateUserRoleDTOs = candidateUserRoleDTOService.searchCandidate(name);
		System.out.println("CANDIDATE -----------" + candidateUserRoleDTOs);
		model.addAttribute("listAcount",candidateUserRoleDTOs);
		model.addAttribute("name",name);
		return "CandidateAcount";
	}
	
	//tim kiem NTD
	@RequestMapping("/timkiemNTD")
	public String showSearchNTD(Model model, HttpServletRequest request, RedirectAttributes redirect,
			@RequestParam("name") String name, Principal principal) {
		System.out.println("name " + name);
		List<RecruiterUserRoleDTO> recruiterUserRoleDTOs = recruiterUserRoleDTOService.searchRecruiter(name);
		System.out.println("Tag candidateUserRoleDTOs " + recruiterUserRoleDTOs);
		model.addAttribute("listAcountRecruiter", recruiterUserRoleDTOs);
		model.addAttribute("name",name);
		return "RecruiterAcount";
	}
	//xem thong tin de cap nhat trang thai UCV
	@GetMapping(value = "editUCV/{id}")
	public String viewUCV(@PathVariable(name = "id") int id, Model model, String name) {
		
		CandidateDTO candidateDTOs = candidateDTOservice.findCandidateById(id);
		List<PriorityAddress> address = priorityAddressService.getAllPriorityAddress();
		List<Industry> dsindustry = industryService.getListIn();
		
		SimpleDateFormat sf= new SimpleDateFormat("dd/MM/yyyy");

		model.addAttribute("ngaysinh",sf.format(candidateDTOs.getNgaySinh()));
		model.addAttribute("ind", dsindustry);
		model.addAttribute("candidateUserRoleDTOs", candidateDTOs);
		model.addAttribute("ABC", listgioitinh);
		model.addAttribute("address", address);
		model.addAttribute("trangthai", trangthai);
		return "FormeditAcountCan";
	}
	//cap nhat trang thai UCV
	@RequestMapping(value = "/editUCV", method = RequestMethod.POST)
	public String updateUTV(CandidateDTO candidate, RedirectAttributes redirAttrs,HttpServletRequest request) {
		   Candidate candidate2 = candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(candidate.getMail()).getId());
		   System.out.println(candidate.toString());
		   AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(candidate.getMail());
		   aspNetUsers.setPhoneNumber(candidate2.getUsers().getPhoneNumber());
		   aspNetUsers.setEmail(candidate2.getUsers().getEmail());
		   System.out.println(candidate.isTrangthai());
		   aspNetUsers.setLockoutEnabled(candidate.isTrangthai());
		   aspNetUsersService.addAspNetUsers(aspNetUsers);
		return "redirect:/CandidateAccount";
	}
	
	//cap nha trang thai NTD
	@RequestMapping(value = "/editNTD", method = RequestMethod.POST)
	public String updateNTD(CandidateDTO candidate, RedirectAttributes redirAttrs,HttpServletRequest request) {
		   Recruiter recruiter = recruiterService.getRecruiterByUsersId(aspNetUsersService.getAspNetUsersByEmail(candidate.getMail()).getId());
		   System.out.println(candidate.toString());
		   AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(candidate.getMail());
		   aspNetUsers.setPhoneNumber(recruiter.getUsers().getPhoneNumber());
		   aspNetUsers.setEmail(recruiter.getUsers().getEmail());
		   System.out.println(candidate.isTrangthai());
		   aspNetUsers.setLockoutEnabled(candidate.isTrangthai());
		   aspNetUsersService.addAspNetUsers(aspNetUsers);
		return "redirect:/RecruiterAccount";
	}
	
	//xem thong tin chi tiet UCV
	@GetMapping(value = "thongtinUCV/{id}")
	public String updateUCVv(@PathVariable(name = "id") int id, Model model, String name) {
		CandidateDTO candidateDTOs = candidateDTOservice.findCandidateById(id);
		SimpleDateFormat sf= new SimpleDateFormat("dd/MM/yyyy");

		model.addAttribute("ngaysinh",sf.format(candidateDTOs.getNgaySinh()));
		
		List<PriorityAddress> address = priorityAddressService.getAllPriorityAddress();
		List<Industry> dsindustry = industryService.getListIn();
		model.addAttribute("ind", dsindustry);
		model.addAttribute("candidateUserRoleDTOs", candidateDTOs);
		model.addAttribute("ABC", listgioitinh);
		model.addAttribute("address", address);
		model.addAttribute("trangthai", trangthai);
		return "FormInfoAcounCan";
	}
	
	//xem thong tin chi tiet NTD
	@GetMapping(value = "thongtinNTD/{id}")
	public String updateNTD(@PathVariable(name = "id") int id, Model model, String name) {
		CandidateDTO candidateDTOs = recruiterDTOservice.findRecruiterById(id);//tim 
		List<PriorityAddress> address = priorityAddressService.getAllPriorityAddress();
		List<Industry> dsindustry = industryService.getListIn();
		model.addAttribute("address", address);
		model.addAttribute("ABC", listgioitinh);
		model.addAttribute("ind", dsindustry);
		model.addAttribute("trangthai", trangthai);
		model.addAttribute("recruitterUserRoleDTOs", candidateDTOs);
		return "FormInfoAcountRe";
	}
	//xem thong tin de cap nhat trang thai NTD
	@GetMapping(value = "editNTD/{id}")
	public String updateNTD2(@PathVariable(name = "id") int id, Model model, String name) {
		CandidateDTO candidateDTOs = recruiterDTOservice.findRecruiterById(id);
		List<PriorityAddress> address = priorityAddressService.getAllPriorityAddress();
		List<Industry> dsindustry = industryService.getListIn();
		model.addAttribute("address", address);
		model.addAttribute("ABC", listgioitinh);
		model.addAttribute("ind", dsindustry);
		model.addAttribute("recruitterUserRoleDTOs", candidateDTOs);
		model.addAttribute("trangthai", trangthai);
		return "FormInfoUpdateAcountRe";
	}		
}
