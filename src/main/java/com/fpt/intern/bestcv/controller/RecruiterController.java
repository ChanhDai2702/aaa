package com.fpt.intern.bestcv.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.BusinessOrganization;
import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.entity.PriorityAddress;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.BusinessOrganzizationService;
import com.fpt.intern.bestcv.service.FileImageService;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.PriorityAddressService;
import com.fpt.intern.bestcv.service.RecruiterService;

@Controller
public class RecruiterController {
	
	static String FORM_EDIT_NTD = "components/editInformation/edit_NTD";
	static String FORM_EDIT_NTDBT3 = "components/editInformation/edit_NTDBT3";
	static String FORM_GENERATEOTP = "generateOtp";
	static String FORM_GENERATEOTPBT3 = "generateOtpBT3";
	
	@Autowired
	RecruiterService recruiterService;
	@Autowired
	private FileImageService fileImage;
	@Autowired
	IndustryService industryService;
	@Autowired
	PriorityAddressService priorityAddressService;
	@Autowired
	BusinessOrganzizationService businessOrganzizationService;
	@Autowired
	AspNetUsersService aspNetUsersService;

	static List<String> listgioitinh = Arrays.asList(
			"Nam",
			"Nữ");
	@PostMapping(value = "save_NTD")
	public String saveNTD(Recruiter re,
			@RequestParam("files") MultipartFile fileImages,Model model,RedirectAttributes redirAttrs,HttpServletRequest request) {
		Recruiter recruiter = recruiterService.getRecruiterbyID(re.getId());
		Industry industry = industryService.getIndustryByName(re.getIndustry().getIndustryName());
		recruiter.setIndustry(industry);
		BusinessOrganization busnOptional = businessOrganzizationService.findByBusinessModelName(re.getBusinessOrganization().getBusinessModelName());
		recruiter.setBusinessOrganization(busnOptional);
		PriorityAddress priOptional = priorityAddressService.findByPriorityAddressName(re.getPriorityAddress().getPriorityAddressName());
		recruiter.setPriorityAddress(priOptional);
		AspNetUsers AspNetUsers = aspNetUsersService.getAspNetUsersByID(re.getUsers().getId());
		String sdt = (String) request.getParameter("phone");
		AspNetUsers.setPhoneNumber(sdt);
		recruiter.setUsers(AspNetUsers);
		if(fileImages.getSize()!=0) {
			recruiter.setImage(fileImage.saveImage(fileImages,UUID.randomUUID().toString()));
		}
		redirAttrs.addFlashAttribute("recr",recruiter);
		return "redirect:/"+FORM_GENERATEOTP;
	}
	
	@PostMapping(value = "save_NTDBT3")
	public String saveNTDBT3(Recruiter re,
			@RequestParam("files") MultipartFile fileImages,Model model,RedirectAttributes redirAttrs,HttpServletRequest request) {
		Recruiter recruiter = recruiterService.getRecruiterbyID(re.getId());
		Industry industry = industryService.getIndustryByName(re.getIndustry().getIndustryName());
		recruiter.setIndustry(industry);
		BusinessOrganization busnOptional = businessOrganzizationService.findByBusinessModelName(re.getBusinessOrganization().getBusinessModelName());
		recruiter.setBusinessOrganization(busnOptional);
		PriorityAddress priOptional = priorityAddressService.findByPriorityAddressName(re.getPriorityAddress().getPriorityAddressName());
		recruiter.setPriorityAddress(priOptional);
		AspNetUsers AspNetUsers = aspNetUsersService.getAspNetUsersByID(re.getUsers().getId());
		String sdt = (String) request.getParameter("phone");
		AspNetUsers.setPhoneNumber(sdt);
		recruiter.setUsers(AspNetUsers);
		if(fileImages.getSize()!=0) {
			recruiter.setImage(fileImage.saveImage(fileImages,UUID.randomUUID().toString()));
		}
		redirAttrs.addFlashAttribute("recr",recruiter);
		return "redirect:/"+FORM_GENERATEOTPBT3;
	}
	
	@RequestMapping(value = "edit_NTD")
	public String EditNTD(Model model,Principal principal) {
		if(principal!=null) {
			List<Industry> dsindustry = industryService.getListIn();
			List<PriorityAddress> address = priorityAddressService.getAllPriorityAddress();
			List<BusinessOrganization> BusinessOrganzization = businessOrganzizationService.getAllBusinessOrganization();
			model.addAttribute("business",BusinessOrganzization);
			model.addAttribute("ind",dsindustry);
			model.addAttribute("address",address);	
			AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString());
			model.addAttribute("asp",recruiterService.getRecruiterByUsersId(aspNetUsers.getId()).getAspNetUsers());
			Recruiter recruiter = recruiterService.getRecruiterByUsersId(aspNetUsers.getId());
			if(recruiter.getImage()==null) {
				recruiter.setImage("emoji.png");
			}
			model.addAttribute("NTD", recruiter);
			model.addAttribute("success");
			return FORM_EDIT_NTD;
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "edit_NTDBT3")
	public String EditNTDBT3(Model model,Principal principal) {
		if(principal!=null) {
			List<Industry> dsindustry = industryService.getListIn();
			List<PriorityAddress> address = priorityAddressService.getAllPriorityAddress();
			List<BusinessOrganization> BusinessOrganzization = businessOrganzizationService.getAllBusinessOrganization();
			model.addAttribute("business",BusinessOrganzization);
			model.addAttribute("ind",dsindustry);
			model.addAttribute("address",address);	
			AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString());
			model.addAttribute("asp",recruiterService.getRecruiterByUsersId(aspNetUsers.getId()).getAspNetUsers());
			Recruiter recruiter = recruiterService.getRecruiterByUsersId(aspNetUsers.getId());
			if(recruiter.getImage()==null) {
				recruiter.setImage("emoji.png");
			}
			model.addAttribute("NTD", recruiter);
			model.addAttribute("success");
			return FORM_EDIT_NTDBT3;
		}
		
		return "redirect:/";
	}



}
