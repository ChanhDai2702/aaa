package com.fpt.intern.bestcv.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.entity.Admin;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.service.AdminService;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.OTPService;
import com.fpt.intern.bestcv.service.RecruiterService;
import com.fpt.intern.bestcv.service.impl.MailService;


@Controller
public class OTPController {
	static String FORM_EDIT_UTV = "edit_UTV";
	static String FORM_EDIT_NTD = "edit_NTD";
	static String FORM_EDIT_OTPPAGE = "components/editInformation/otppage";
	static String FORM_EDIT_OTPPAGEBT3 = "components/editInformation/otppageBT3";
	static String FORM_EDIT_ADMIN = "edit_admin";
	
	@Autowired
	public OTPService otpService;
	@Autowired
	public MailService emailService;
	@Autowired
	public AspNetUsersService aspNetUsersService;
	@Autowired
	public AdminService adminService;
	@Autowired
	public CandidateService candidateService;
	@Autowired
	public RecruiterService recruiterService;

	@GetMapping("/generateOtp")
	public String generateOTP(Model model,HttpServletRequest request,Principal principal) throws MessagingException, UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String username = auth.getName();
		otpService.clearOTP(username);
		int otp = otpService.generateOTP(username);
	
	//	emailService.sendOtpMessage(principal.getName().toString(), "OTP -SpringBoot", "Mã Otp của bạn là: "+String.valueOf(otp)+"");
		emailService.sendEmailOTP(principal.getName().toString(), String.valueOf(otp),principal);
		Recruiter rvc = (Recruiter) model.getAttribute("recr");
		Admin admin = (Admin) model.getAttribute("admin_data");
		Candidate candidate =  (Candidate) model.getAttribute("candi_data");
	
		if(rvc!=null) {
			request.getSession().setAttribute("data",rvc );
			
		}else if(admin!=null) {
			request.getSession().setAttribute("data",admin );
		}else if(candidate!=null) {
			request.getSession().setAttribute("data",candidate );
		}
		return FORM_EDIT_OTPPAGE;
	}
	
	@GetMapping("/generateOtpBT3")
	public String generateOTPBT3(Model model,HttpServletRequest request,Principal principal) throws MessagingException, UnsupportedEncodingException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String username = auth.getName();
		otpService.clearOTP(username);
		int otp = otpService.generateOTP(username);
	//	emailService.sendOtpMessage(principal.getName().toString(), "OTP -SpringBoot", "Mã Otp của bạn là: "+String.valueOf(otp)+"");
		emailService.sendEmailOTP(principal.getName().toString(), String.valueOf(otp),principal);
		Recruiter rvc = (Recruiter) model.getAttribute("recr");
		Admin admin = (Admin) model.getAttribute("admin_data");
		Candidate candidate =  (Candidate) model.getAttribute("candi_data");
	
		if(rvc!=null) {
			request.getSession().setAttribute("data",rvc );
			
		}else if(admin!=null) {
			request.getSession().setAttribute("data",admin );
		}else if(candidate!=null) {
			request.getSession().setAttribute("data",candidate );
		}
		return FORM_EDIT_OTPPAGEBT3;
	}
	
	@RequestMapping(value = "otppage")
	public String loadOtppage(Model model,HttpServletRequest request,Principal principal) throws UnsupportedEncodingException, MessagingException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String username = auth.getName();
		otpService.clearOTP(username);
		int otp = otpService.generateOTP(username);

	//	emailService.sendOtpMessage(principal.getName().toString(), "OTP -SpringBoot", "Mã Otp của bạn là: "+String.valueOf(otp)+"");
		emailService.sendEmailOTP(principal.getName().toString(), String.valueOf(otp),principal);
		Recruiter rvc = (Recruiter) model.getAttribute("recr");
		Admin admin = (Admin) model.getAttribute("admin_data");
		Candidate candidate =  (Candidate) model.getAttribute("candi_data");
	
		if(rvc!=null) {
			request.getSession().setAttribute("data",rvc );
			
		}else if(admin!=null) {
			request.getSession().setAttribute("data",admin );
		}else if(candidate!=null) {
			request.getSession().setAttribute("data",candidate );
		}
		return FORM_EDIT_OTPPAGE;

	}
	
	@RequestMapping(value = "otppageBT3")
	public String loadOtppageBT3(Model model,HttpServletRequest request,Principal principal) throws UnsupportedEncodingException, MessagingException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String username = auth.getName();
		otpService.clearOTP(username);
		int otp = otpService.generateOTP(username);

	//	emailService.sendOtpMessage(principal.getName().toString(), "OTP -SpringBoot", "Mã Otp của bạn là: "+String.valueOf(otp)+"");
		emailService.sendEmailOTP(principal.getName().toString(), String.valueOf(otp),principal);
		Recruiter rvc = (Recruiter) model.getAttribute("recr");
		Admin admin = (Admin) model.getAttribute("admin_data");
		Candidate candidate =  (Candidate) model.getAttribute("candi_data");
	
		if(rvc!=null) {
			request.getSession().setAttribute("data",rvc );
			
		}else if(admin!=null) {
			request.getSession().setAttribute("data",admin );
		}else if(candidate!=null) {
			request.getSession().setAttribute("data",candidate );
		}
		return FORM_EDIT_OTPPAGEBT3;

	}


	@PostMapping("/validateOtp")
	public String postgenerateOtp(@RequestParam("otpnum") int otpnum,Principal principal,Recruiter re,Model model,HttpServletRequest request,RedirectAttributes redirAttrs) throws MessagingException{
		Admin admin = new Admin();
		Recruiter recruiter = new Recruiter();
		Candidate candidate = new Candidate();

		//aspNetUsers = (AspNetUsers) request.getSession().getAttribute("data");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String username = auth.getName();
		int serverOtp = otpService.getOtp(username);

		String email = principal.getName().toString();
		AspNetUsers users = aspNetUsersService.getAspNetUsersByEmail(email);


		
		if(otpnum>=0) {
			if(serverOtp > 0) {
				if(otpnum == serverOtp) {
					otpService.clearOTP(username);
					if(adminService.getUserId(users.getId())!=null) {
						admin =  (Admin) request.getSession().getAttribute("data");
						if(admin.getAspNetUsers()!=null) {
							aspNetUsersService.addAspNetUsers(admin.getAspNetUsers());
						}
						adminService.editAdmin(admin);
						redirAttrs.addFlashAttribute("success", "success");
						request.getSession().invalidate();
						return "redirect:/"+FORM_EDIT_ADMIN;
					}else if(recruiterService.getRecruiterByUsersId(users.getId())!=null) {
						recruiter = (Recruiter) request.getSession().getAttribute("data");
						if(recruiter.getAspNetUsers()!=null) {
							aspNetUsersService.addAspNetUsers(recruiter.getAspNetUsers());
						}
						recruiterService.editRecruiter(recruiter);
						redirAttrs.addFlashAttribute("success", "success");
						request.getSession().invalidate();
						return "redirect:/"+FORM_EDIT_NTD;
					}else if(candidateService.getCandidateByUsersId(users.getId())!=null){
						candidate = (Candidate) request.getSession().getAttribute("data");
						if(candidate.getUsers()!=null) {
							aspNetUsersService.addAspNetUsers(candidate.getUsers());
						}
						candidateService.saveCandidate(candidate);
						redirAttrs.addFlashAttribute("success", "success");
						request.getSession().invalidate();
						return  "redirect:/"+FORM_EDIT_UTV;
					}
				}
			}
		}
		model.addAttribute("error", "miss");
		return FORM_EDIT_OTPPAGE;
	}
	@PostMapping("/validateOtpBT3")
	public String postgenerateOtpBT3(@RequestParam("otpnum") int otpnum,Principal principal,Recruiter re,Model model,HttpServletRequest request,RedirectAttributes redirAttrs) throws MessagingException{
		Admin admin = new Admin();
		Recruiter recruiter = new Recruiter();
		Candidate candidate = new Candidate();

		//aspNetUsers = (AspNetUsers) request.getSession().getAttribute("data");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String username = auth.getName();
		int serverOtp = otpService.getOtp(username);

		String email = principal.getName().toString();
		AspNetUsers users = aspNetUsersService.getAspNetUsersByEmail(email);


		
		if(otpnum>=0) {
			if(serverOtp > 0) {
				if(otpnum == serverOtp) {
					otpService.clearOTP(username);
					if(adminService.getUserId(users.getId())!=null) {
						admin =  (Admin) request.getSession().getAttribute("data");
						aspNetUsersService.addAspNetUsers(admin.getAspNetUsers());
						adminService.editAdmin(admin);
						return "redirect:/";
					}else if(recruiterService.getRecruiterByUsersId(users.getId())!=null) {
						recruiter = (Recruiter) request.getSession().getAttribute("data");
						aspNetUsersService.addAspNetUsers(recruiter.getAspNetUsers());
						recruiterService.editRecruiter(recruiter);
						return "redirect:/";
					}else if(candidateService.getCandidateByUsersId(users.getId())!=null){
						candidate = (Candidate) request.getSession().getAttribute("data");
						aspNetUsersService.addAspNetUsers(candidate.getUsers());
						candidateService.saveCandidate(candidate);
						return  "redirect:/";
					}
				}
			}
		}
		model.addAttribute("error", "miss");
		return FORM_EDIT_OTPPAGE;
	}
}
