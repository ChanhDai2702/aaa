package com.fpt.intern.bestcv.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.entity.Admin;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.CurriculumVitae;
import com.fpt.intern.bestcv.entity.CurriculumVitaeDetail;
import com.fpt.intern.bestcv.entity.Education;
import com.fpt.intern.bestcv.entity.Experience;
import com.fpt.intern.bestcv.entity.Job;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.entity.SkillDetail;
import com.fpt.intern.bestcv.service.AdminService;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.CurriculumVitaeDetailService;
import com.fpt.intern.bestcv.service.CurriculumVitaeService;
import com.fpt.intern.bestcv.service.EducationService;
import com.fpt.intern.bestcv.service.ExperienceService;
import com.fpt.intern.bestcv.service.JobService;
import com.fpt.intern.bestcv.service.RecruiterService;
import com.fpt.intern.bestcv.service.SkillDetailService;

@Controller
public class EditUserController {
	static String FORM_EDIT_UTV = "edit_UTV";
	static String FORM_EDIT_UTVBT3 = "edit_UTVBT3";
	static String FORM_GENERATEOTP = "generateOtp";
	static String FORM_EDIT_CAPNHATUTV = "capnhat_UTV";
	static String FORM_EDIT_NTD = "edit_NTD";
	static String FORM_EDIT_NTDBT3 = "edit_NTDBT3";
	static String FORM_EDIT_OTPPAGE = "otppage";
	static String FORM_EDIT_ADMIN = "edit_admin";
	static String FORM_EDIT_UPDATEPASSWORD = "components/editInformation/UpdatePassword";
	static String FORM_EDIT_EDUCATION = "components/editInformation/edit_education";
	static String FORM_EDIT_EXP = "components/editInformation/edit_EXP";
	static String FORM_EDIT_SKILL= "components/editInformation/edit_skill";
	static String FORM_ADD_EDUCATION = "components/editInformation/add_education";
	static String FORM_ADD_EXP = "components/editInformation/add_Experince";
	static String FORM_ADD_SKILL= "components/editInformation/add_skill";
	
	@Autowired
	public AspNetUsersService aspNetUsersService;
	@Autowired
	public CandidateService candidateService;
	@Autowired
	public RecruiterService recruiterService;
	@Autowired
	public AdminService adminService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JobService jobService;
	@Autowired
	private EducationService educationService;
	@Autowired
	private CurriculumVitaeService curriculumVitaeService;
	@Autowired 
	private CurriculumVitaeDetailService curriculumVitaeDetailService;
	@Autowired
	private ExperienceService experienceService;
	@Autowired
	private SkillDetailService skillDetailService;
	
	
	@RequestMapping(value = "/upload_capnhat")
	public String CapNhat(Principal principal) {
		String redirect ="";
		
		
		if(principal !=null) {
			String email = principal.getName().toString();
			if(candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(email).getId())!=null) {
				redirect = "redirect:/"+FORM_EDIT_UTV;
			}else if(recruiterService.getRecruiterByUsersId(aspNetUsersService.getAspNetUsersByEmail(email).getId())!=null) {
				redirect = "redirect:/"+FORM_EDIT_NTD;
			}else if(adminService.getAdminByUsersId(aspNetUsersService.getAspNetUsersByEmail(email).getId())!=null){
				redirect = "redirect:/"+FORM_EDIT_ADMIN;
			}else {
				redirect = "redirect:/";
			}
		}else {
			redirect = "redirect:/";
		}

		return redirect;

	}
	@RequestMapping(value = "/upload_capnhatBT3")
	public String CapNhatBT3(Principal principal) {
		String redirect ="";
		
		
		if(principal !=null) {
			String email = principal.getName().toString();
			if(candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(email).getId())!=null) {
				redirect = "redirect:/"+FORM_EDIT_UTVBT3;
			}else if(recruiterService.getRecruiterByUsersId(aspNetUsersService.getAspNetUsersByEmail(email).getId())!=null) {
				redirect = "redirect:/"+FORM_EDIT_NTDBT3;
			}else if(adminService.getAdminByUsersId(aspNetUsersService.getAspNetUsersByEmail(email).getId())!=null){
				redirect = "redirect:/"+FORM_EDIT_ADMIN;
			}else {
				redirect = "redirect:/";
			}
		}else {
			redirect = "redirect:/";
		}

		return redirect;

	}
	@RequestMapping(value = "/updatepassword")
	public String getNganh(Model model,Principal principal) {
		String email = principal.getName().toString();
		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(email);
		
		if(recruiterService.getRecruiterByUsersId(aspNetUsers.getId())!=null) {
			model.addAttribute("Data",recruiterService.getRecruiterByUsersId(aspNetUsers.getId()));
		}else if(candidateService.getCandidateByUsersId(aspNetUsers.getId())!=null) {
			model.addAttribute("Data",candidateService.getCandidateByUsersId(aspNetUsers.getId()));
		}else {
			model.addAttribute("Data",adminService.getUserId(aspNetUsers.getId()));
		}
		return FORM_EDIT_UPDATEPASSWORD;
	}
	@PostMapping(value = "luupassword")
	public String doipass( HttpServletRequest request,RedirectAttributes redirAttrs,Model model) {
		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByID(request.getParameter("userid"));
		String passcu = request.getParameter("pass_cu");
		
		String passmoi = (String) request.getParameter("pass_moi");
		
		if(passwordEncoder.matches(passcu, aspNetUsers.getPasswordHash())) {
			aspNetUsers.setPasswordHash(passwordEncoder.encode(passmoi));
			Admin admin = adminService.getUserId(aspNetUsers.getId());
			Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsers.getId());
			Recruiter recruiter = recruiterService.getRecruiterByUsersId(aspNetUsers.getId());
			if(admin!=null) {
				admin.setAspNetUsers(aspNetUsers);
				System.out.println(admin);
				System.out.println(admin.getAspNetUsers());
				redirAttrs.addFlashAttribute("admin_data",admin);
			}else if(candidate!=null) {
				candidate.setUsers(aspNetUsers);
				
				redirAttrs.addFlashAttribute("candi_data",candidate);
			}else if(recruiter !=null) {
				recruiter.setAspNetUsers(aspNetUsers);
				redirAttrs.addFlashAttribute("recr",recruiter);
			}
			return "redirect:/"+FORM_EDIT_OTPPAGE;
		}else {
			redirAttrs.addFlashAttribute("error","error");
			return "redirect:/updatepassword";
		}
	}
	@RequestMapping(value = "/QuayLai")
	public String QuayLai(Principal principal,Model model) {

		String email = principal.getName().toString();
		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(email);
		if(recruiterService.getRecruiterByUsersId(aspNetUsers.getId())!=null) {

			return FORM_EDIT_NTD;
		}else if(candidateService.getCandidateByUsersId(aspNetUsers.getId())!=null) {
			model.addAttribute("NTD",candidateService.getCandidateByUsersId(aspNetUsers.getId()));
			return FORM_EDIT_UTV;
		}else {
			return FORM_EDIT_ADMIN;
		}
	}
	@RequestMapping(value = "capnhat_UTV")
	public String updataAuto(Principal principal,Model model) {

		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString());
		Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsers.getId());

		//Industry industry = candidate.getIndustry();
		List<Job> dsJobs = new ArrayList<Job>();
		List<Job> jobs =  jobService.getListAllJob();
		jobs.forEach(x->{
			if(x.getIndustry().getId()==candidate.getIndustry().getId()) {
				dsJobs.add(x);
			}
		});

		CurriculumVitae curriculumVitae = curriculumVitaeService.findByCurriculumVitaeIdCandidate(candidate.getId());

		if(curriculumVitae==null) {


			curriculumVitae = new CurriculumVitae(new Date(), new Date(), 0, "", 0, "");
			curriculumVitae.setCandidate(candidate);

			CurriculumVitaeDetail curriculumVitaeDetail = new CurriculumVitaeDetail();
			curriculumVitaeDetail.setCurriculumVitae(curriculumVitae);
			List<Education> education = new ArrayList<Education>();
			List<Experience> exceptions = new ArrayList<Experience>();
			List<SkillDetail> details = new ArrayList<SkillDetail>();
			model.addAttribute("job",dsJobs);
			model.addAttribute("CV",curriculumVitae);
			model.addAttribute("education",education);
			model.addAttribute("exception",exceptions);
			model.addAttribute("skill",details);
		
			return "components/editInformation/capnhat_UTV";
		}else {
			CurriculumVitaeDetail curriculumVitaeDetail = curriculumVitaeDetailService.findBCurriculumVitaeDetailIdCurriculumVitae(curriculumVitae.getId());
			List<Education> educations = educationService.findByEducationIdCurriculumVitaeDetail(curriculumVitaeDetail.getId());
			List<Experience> experiences = experienceService.findByExperienceIdCurriculumVitaeDetail(curriculumVitaeDetail.getId());
			List<SkillDetail> skillDetails = skillDetailService.findByEducationIdCurriculumVitaeDetail(curriculumVitaeDetail.getId());
			model.addAttribute("job",dsJobs);
			model.addAttribute("CV",curriculumVitae);
			model.addAttribute("education",educations);
			model.addAttribute("experience",experiences);
			model.addAttribute("skill",skillDetails);
			return "components/editInformation/capnhat_UTV";
		}
	}
	@PostMapping(value = "capnhat")
	public String capNhat(CurriculumVitae curriculumVitae,Principal principal) {
		System.out.println(curriculumVitae.getId());
		if(curriculumVitae.getId()==0) {
			AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString());
			Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsers.getId());
			CurriculumVitae curriculumVitae1 = new CurriculumVitae(new Date(), new Date(), 0, "", 0, "");
			curriculumVitae1.setJob(jobService.findJobByName(curriculumVitae.getJob().getJobName()));
			System.out.println(curriculumVitae1.getJob());
			curriculumVitae1.setCandidate(candidate);
			curriculumVitaeService.editCurriculumVitae(curriculumVitae1);
			CurriculumVitaeDetail curriculumVitaeDetail = new CurriculumVitaeDetail();
			curriculumVitaeDetail.setCurriculumVitae(curriculumVitae1);
			curriculumVitaeDetailService.editCurriculumVitaeDetail(curriculumVitaeDetail);
		}else {
			CurriculumVitae vitae = curriculumVitaeService.getCurriculumVitaeByID(curriculumVitae.getId());
			vitae.setJob(jobService.findJobByName(curriculumVitae.getJob().getJobName()));
			curriculumVitaeService.editCurriculumVitae(vitae);
		}
		return "redirect:/"+FORM_EDIT_CAPNHATUTV;
	}
	@RequestMapping(value = "/add_hocvan")
	private String addEducation( Model model,HttpServletRequest request,CurriculumVitae vitae) {
		model.addAttribute("education",new Education());
		return FORM_ADD_EDUCATION;
	}

	@PostMapping(value = "save_hocvan")
	public String saveHocVan(Education education,Principal principal) {
		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString());
		Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsers.getId());
		CurriculumVitae curriculumVitae = curriculumVitaeService.findByCurriculumVitaeIdCandidate(candidate.getId());

		CurriculumVitaeDetail curriculumVitaeDetail = curriculumVitaeDetailService.findBCurriculumVitaeDetailIdCurriculumVitae(curriculumVitae.getId());
		education.setCurriculumVitaeDetail(curriculumVitaeDetail);
		educationService.editEducation(education);
		return "redirect:/"+FORM_EDIT_CAPNHATUTV;

	}
	@RequestMapping(value = "edit_hv")
	public String edit_HV(@RequestParam("id") Integer id,Model model) {
		model.addAttribute("education",educationService.getEducationbyID(id));
		return FORM_EDIT_EDUCATION;
	}
	@RequestMapping(value = "/delete_hocvan")
	public String delete_HV(@RequestParam("id") Integer id) {

		educationService.removeEducation(educationService.getEducationbyID(id));
		return "redirect:/"+FORM_EDIT_CAPNHATUTV;

	}
	@RequestMapping(value = "/delete_EXP")
	public String delete_EXP(@RequestParam("id") Integer id) {

		experienceService.removeExperience(id);
		return "redirect:/"+FORM_EDIT_CAPNHATUTV;

	}
	@RequestMapping(value = "/add_EXP")
	private String addExperince(String job, Model model) {
		model.addAttribute("experience",new Experience());
		return FORM_ADD_EXP;
	}
	@RequestMapping(value = "edit_EXP")
	public String edit_EXP(@RequestParam("id") Integer id,Model model) {
		model.addAttribute("experience",experienceService.getExperiencebyid(id));
		return FORM_EDIT_EXP;
	}
	@PostMapping(value = "save_EXP")
	public String saveEXP(Experience experience,Principal principal) {
		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString());
		Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsers.getId());
		CurriculumVitae curriculumVitae = curriculumVitaeService.findByCurriculumVitaeIdCandidate(candidate.getId());
		CurriculumVitaeDetail curriculumVitaeDetail = curriculumVitaeDetailService.findBCurriculumVitaeDetailIdCurriculumVitae(curriculumVitae.getId());
		experience.setCurriculumVitaeDetail(curriculumVitaeDetail);
		experienceService.editExperience(experience);
		System.out.println(experience.getId());
		return "redirect:/"+FORM_EDIT_CAPNHATUTV;
	}
	@RequestMapping(value = "/add_skill")
	private String addSkill(String job, Model model) {
		model.addAttribute("skill",new SkillDetail());
		return FORM_ADD_SKILL;
	}
	@PostMapping(value = "save_skill")
	public String saveskill(SkillDetail skillDetail, Principal principal) {
		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString());
		Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsers.getId());
		CurriculumVitae curriculumVitae = curriculumVitaeService.findByCurriculumVitaeIdCandidate(candidate.getId());
		CurriculumVitaeDetail curriculumVitaeDetail = curriculumVitaeDetailService.findBCurriculumVitaeDetailIdCurriculumVitae(curriculumVitae.getId());
		skillDetail.setCurriculumVitaeDetail(curriculumVitaeDetail);
		skillDetailService.saveSkillDetail(skillDetail);
		return "redirect:/"+FORM_EDIT_CAPNHATUTV;
	}
	@RequestMapping(value = "/delete_Skill")
	public String delete_skill(@RequestParam("id") Integer id) {
		
		skillDetailService.deleteSkillDetail(id);
		return "redirect:/"+FORM_EDIT_CAPNHATUTV;

	}
	@RequestMapping(value = "edit_Skill")
	public String edit_Skill(@RequestParam("id") Integer id,Model model) {
		
		model.addAttribute("skill",skillDetailService.getSkillDetailById(id));
		
		return FORM_EDIT_SKILL;
	}
}
