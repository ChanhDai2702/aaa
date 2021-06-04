package com.fpt.intern.bestcv.controller;

import java.security.Principal;
import java.util.Arrays;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;

import com.fpt.intern.bestcv.entity.Education;
import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.entity.Job;
import com.fpt.intern.bestcv.entity.PriorityAddress;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.CurriculumVitaeDetailService;
import com.fpt.intern.bestcv.service.CurriculumVitaeService;
import com.fpt.intern.bestcv.service.EducationService;
import com.fpt.intern.bestcv.service.FileImageService;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.JobService;
import com.fpt.intern.bestcv.service.PriorityAddressService;
@Controller
public class CandidateController {
	static String FORM_EDIT_UTV = "components/editInformation/edit_UTV";
	static String FORM_EDIT_UTVBT3 = "components/editInformation/edit_UTVBT3";
	static String FORM_GENERATEOTP = "generateOtp";
	static String FORM_GENERATEOTPBT3 = "generateOtpBT3";
	static List<String> listgioitinh = Arrays.asList(
			"Nam",
			"Nữ");
	static List<String> listhocvan = Arrays.asList(
			"Chính",
			"Sự miêu tả",
			"Ngày bắt đầu",
			"Ngày kết thúc",
			"Tên trường",
			"Điểm trung bình");
	private List<Education> educations;
	private List<Job> cv;
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private CurriculumVitaeService curriculumVitaeService;
	@Autowired
	private CurriculumVitaeDetailService curriculumVitaeDetailService;
	@Autowired
	private EducationService educationService;
	@Autowired
	private FileImageService fileImage;
	@Autowired
	private AspNetUsersService aspNetUsersService;
	@Autowired
	IndustryService industryService;
	@Autowired
	PriorityAddressService priorityAddressService;
	@Autowired
	private JobService jobService;

	@SuppressWarnings("unused")
	@RequestMapping(value = "edit_UTV")
	public String EditUTV(Model model,Principal principal) {
		
		if(principal!=null) {
			Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString()).getId());
			List<Industry> dsindustry = industryService.getListIn();
			List<PriorityAddress> address = priorityAddressService.getAllPriorityAddress();
			if(candidate.getImage()==null) {
				candidate.setImage("emoji.png");
			}
			if(candidate!=null) {
				model.addAttribute("UTV",candidate);
			}else {
				model.addAttribute("UTV",new Candidate() );
			}
			model.addAttribute("ind",dsindustry);
			model.addAttribute("address",address);	
			model.addAttribute("ABC",listgioitinh);
			model.addAttribute("success");
			model.addAttribute("listHocVan", listhocvan);
//			List<Job> jobs = jobService.getListAllJob();
//			model.addAttribute("job", jobs);
			
			return FORM_EDIT_UTV;
		}
			
		

		return "redirect:/"+FORM_EDIT_UTV;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "edit_UTVBT3")
	public String EditUTVBT3(Model model,Principal principal) {
		
		if(principal!=null) {
			Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString()).getId());
			List<Industry> dsindustry = industryService.getListIn();
			List<PriorityAddress> address = priorityAddressService.getAllPriorityAddress();
			if(candidate.getImage()==null) {
				candidate.setImage("emoji.png");
			}
			if(candidate!=null) {
				model.addAttribute("UTV",candidate);
			}else {
				model.addAttribute("UTV",new Candidate() );
			}
			model.addAttribute("ind",dsindustry);
			model.addAttribute("address",address);	
			model.addAttribute("ABC",listgioitinh);
			model.addAttribute("success");
			model.addAttribute("listHocVan", listhocvan);
//			List<Job> jobs = jobService.getListAllJob();
//			model.addAttribute("job", jobs);
			
			return FORM_EDIT_UTVBT3;
		}
			
		

		return "redirect:/";
	}

	@PostMapping(value = "save_utv")
	public String updateUTV(Candidate candidate,@RequestParam("files") MultipartFile fileImages,RedirectAttributes redirAttrs) {
		Industry industry = industryService.getIndustryByName(candidate.getIndustry().getIndustryName());
		PriorityAddress priorityAddress = priorityAddressService.findByPriorityAddressName(candidate.getPriorityAddress().getPriorityAddressName());
		candidate.setIndustry(industry);
		candidate.setPriorityAddress(priorityAddress);
		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByID(candidate.getUsers().getId());
		aspNetUsers.setPhoneNumber(candidate.getUsers().getPhoneNumber());
		candidate.setUsers(aspNetUsers);
		if(fileImages.getSize()!=0) {
			candidate.setImage(fileImage.saveImage(fileImages,UUID.randomUUID().toString()));
		}
		redirAttrs.addFlashAttribute("candi_data", candidate);
		return "redirect:/"+FORM_GENERATEOTP;

	}
	@PostMapping(value = "save_utvbt3")
	public String updateUTVBT3(Candidate candidate,@RequestParam("files") MultipartFile fileImages,RedirectAttributes redirAttrs) {
		Industry industry = industryService.getIndustryByName(candidate.getIndustry().getIndustryName());
		PriorityAddress priorityAddress = priorityAddressService.findByPriorityAddressName(candidate.getPriorityAddress().getPriorityAddressName());
		candidate.setIndustry(industry);
		candidate.setPriorityAddress(priorityAddress);
		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByID(candidate.getUsers().getId());
		aspNetUsers.setPhoneNumber(candidate.getUsers().getPhoneNumber());
		candidate.setUsers(aspNetUsers);
		if(fileImages.getSize()!=0) {
			candidate.setImage(fileImage.saveImage(fileImages,UUID.randomUUID().toString()));
		}
		redirAttrs.addFlashAttribute("candi_data", candidate);
		return "redirect:/"+FORM_GENERATEOTPBT3;

	}
	
//	@PostMapping(value = "capnhatjob")
//	public String chonnghe(Industry i) {
//		sysout
//		System.out.println(i);
//		return FORM_EDIT_UTV;
//		
//	}
}
