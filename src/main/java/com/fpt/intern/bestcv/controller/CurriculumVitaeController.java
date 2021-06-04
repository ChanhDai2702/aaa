package com.fpt.intern.bestcv.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.CurriculumVitae;
import com.fpt.intern.bestcv.entity.CurriculumVitaeDetail;
import com.fpt.intern.bestcv.entity.Education;
import com.fpt.intern.bestcv.entity.Experience;
import com.fpt.intern.bestcv.entity.HobbyDetail;
import com.fpt.intern.bestcv.entity.Job;
import com.fpt.intern.bestcv.entity.JobGoalDetail;
import com.fpt.intern.bestcv.entity.SkillDetail;
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
@RequestMapping("/cv")
public class CurriculumVitaeController {

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

	@GetMapping(value = "/createCV")
	public String showCreateNewCVPage(Principal principal,Model model,HttpServletRequest request) {
		model.addAttribute("jobId",(String) model.getAttribute("jobId"));
		Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString()).getId());
		CurriculumVitaeDetail curriculumVitaeDetail = new CurriculumVitaeDetail();
		Education education = new Education();
		Experience experience = new Experience();

		model.addAttribute("candidate",candidate);
		model.addAttribute("curriculumVitaeDetail",curriculumVitaeDetail);
		model.addAttribute("education", education);
		model.addAttribute("experience", experience);

		List<HobbyDetail> hobbyDetailq = new ArrayList<HobbyDetail>();
		hobbyDetailq.add(new HobbyDetail());		
		return "components/cv/createCV";
	}
	
	@PostMapping(value = "/jobCV")
	public String CreateJobForCV(Model model,HttpServletRequest request,RedirectAttributes redirect) {
		redirect.addFlashAttribute("jobId",(String) request.getParameter("job"));
		return "redirect:/cv/createCV";
	}
	
	@GetMapping(value = "/checkCV/{id}")
	public String showCheckCVPage(Principal principal,Model model,HttpServletRequest request,@PathVariable(name = "id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CurriculumVitaeDetail curriculumVitaeDetail = curriculumVitaeDetailService.findBCurriculumVitaeDetailIdCurriculumVitae(id);
		CurriculumVitae curriculumVitae = curriculumVitaeDetail.getCurriculumVitae();
		Education education = educationService.findByEducationIdCurriculumVitaeDetail(curriculumVitaeDetail.getId()).get(0);
		Experience experience = experienceService.findByExperienceIdCurriculumVitaeDetail(curriculumVitaeDetail.getId()).get(0);
		List<SkillDetail> skillDetail = skillDetailService.findByEducationIdCurriculumVitaeDetail(curriculumVitaeDetail.getId());
		List<HobbyDetail> hobbyDetail = hobbyDetailService.getLisHobbyDetail(curriculumVitaeDetail.getId());
		List<JobGoalDetail> jobGoalDetail = jobGoalDetailService.getListGoalDetail(curriculumVitaeDetail.getId());
		model.addAttribute("candidate",curriculumVitae.getCandidate());
		model.addAttribute("curriculumVitaeDetail",curriculumVitaeDetail);
		model.addAttribute("curriculumVitae",curriculumVitae);
		model.addAttribute("education", education);
		model.addAttribute("experience", experience);
		model.addAttribute("skillDetail", skillDetail);
		model.addAttribute("hobbyDetail", hobbyDetail);
		model.addAttribute("jobGoalDetail",jobGoalDetail);
		curriculumVitaeService.addOneViewToNews(id);
		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_UTV"))) {
			if(candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString()).getId())==null && candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString()).getId()).getId()!= curriculumVitae.getCandidate().getId()){
				return "403";
			}
		}
		return "components/cv/CheckCV";
	}
	@RequestMapping(value = "/saveCV")
	public String saveNewCV(@RequestParam("file") MultipartFile file,Principal principal,Model model, Candidate candidate,Education education,CurriculumVitaeDetail curriculumVitaeDetail,
			Experience experience,HttpServletRequest request ) {
		System.out.println(request.getParameter("job"));
		candidate = candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString()).getId());
		CurriculumVitae curriculumVitae = new CurriculumVitae();
		curriculumVitae.setCandidate(candidate);
		curriculumVitae.setStatus("false");
		curriculumVitae.setFileName(storageService.save(file));
		curriculumVitae.setCreatedDate(new Date());
		curriculumVitae.setUpdatedDate(new Date());
		Job job = null;
		if(request.getParameter("job")!=null) {
			job = jobService.getJobbyID(Integer.parseInt(request.getParameter("job")));
		}else {
			job = jobService.getJobbyID(1);
		}

		curriculumVitae.setJob(job);

		curriculumVitaeService.editCurriculumVitae(curriculumVitae);

		curriculumVitaeDetail.setCurriculumVitae(curriculumVitae);
		curriculumVitaeDetail.setImage(file.getName());

		curriculumVitaeDetailService.editCurriculumVitaeDetail(curriculumVitaeDetail);


		education.setCurriculumVitaeDetail(curriculumVitaeDetail);
		experience.setCurriculumVitaeDetail(curriculumVitaeDetail);
		System.out.println(curriculumVitae);
		System.out.println(curriculumVitaeDetail);
		System.out.println(experience);
		System.out.println(education);
		educationService.editEducation(education);
		experienceService.editExperience(experience);
		
		List<SkillDetail> skillDetail = new ArrayList<SkillDetail>();

		if(request.getParameterValues("kynangmem") !=null) {
			for(int i =0;i<request.getParameterValues("kynangmem").length;i++) {
				skillDetail.add(new SkillDetail(request.getParameterValues("kynangmem")[i],null,"kỹ năng mềm"));
				skillDetail.get(skillDetail.size()-1).setCurriculumVitaeDetail(curriculumVitaeDetail);
			}
		}

		if(request.getParameterValues("tinhoc") != null) {
			for(int i =0;i<request.getParameterValues("tinhoc").length;i+=2) {
				skillDetail.add(new SkillDetail(request.getParameterValues("tinhoc")[i],request.getParameterValues("tinhoc")[i+1],"Tin học"));
				skillDetail.get(skillDetail.size()-1).setCurriculumVitaeDetail(curriculumVitaeDetail);
			}
		}
		if(request.getParameterValues("ngoaingu")!=null) {
			for(int i =0;i<request.getParameterValues("ngoaingu").length;i+=2) {
				skillDetail.add(new SkillDetail(request.getParameterValues("ngoaingu")[i],request.getParameterValues("ngoaingu")[i+1],"Ngoại ngữ"));
				skillDetail.get(skillDetail.size()-1).setCurriculumVitaeDetail(curriculumVitaeDetail);
			}
		}


		if(skillDetail.size() > 0) {
			System.out.println("Ky nang");

			for(int i =0;i<skillDetail.size();i++) {
				skillDetailService.saveSkillDetail(skillDetail.get(i));
				System.out.println(skillDetail.get(i));
			}
		}

		List<HobbyDetail> hobbyDetail = new ArrayList<HobbyDetail>();
		if(request.getParameterValues("hobby")!=null) {
			for(int i =0;i<request.getParameterValues("hobby").length;i++) {
				hobbyDetail.add(new HobbyDetail(request.getParameterValues("hobby")[i]));
				hobbyDetail.get(hobbyDetail.size()-1).setCurriculumVitaeDetail(curriculumVitaeDetail);
			}
		}


		if(hobbyDetail.size()>0) {
			System.out.println("So thich");
			for(int i =0;i<hobbyDetail.size();i++) {
				hobbyDetailService.createHobbyDetail(hobbyDetail.get(i));
				System.out.println(hobbyDetail.get(i));
			}
		}

		List<JobGoalDetail> jobGoalDetail = new ArrayList<JobGoalDetail>();
		if(request.getParameterValues("jobGoal")!=null) {
			for(int i =0;i<request.getParameterValues("jobGoal").length;i++) {
				jobGoalDetail.add(new JobGoalDetail(request.getParameterValues("jobGoal")[i]));
				jobGoalDetail.get(jobGoalDetail.size()-1).setCurriculumVitaeDetail(curriculumVitaeDetail);
			}
		}
		if(jobGoalDetail.size()>0) {
			System.out.println("Muc tieu nghe nghiep");
			for(int i =0;i<jobGoalDetail.size();i++) {
				jobGoalDetailService.createJobDetail(jobGoalDetail.get(i));
				System.out.println(jobGoalDetail.get(i));
			}
		}
		return "redirect:/cv/listCV";
	}
	@GetMapping(value = "/editCV/{id}")
	public String showEditCVPage(Principal principal,Model model,HttpServletRequest request,@PathVariable(name = "id") int id) {
		Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsersService.getAspNetUsersByEmail(principal.getName().toString()).getId());
		CurriculumVitaeDetail curriculumVitaeDetail = curriculumVitaeDetailService.findBCurriculumVitaeDetailIdCurriculumVitae(id);
		CurriculumVitae curriculumVitae = curriculumVitaeDetail.getCurriculumVitae();
		List<Education> listEducation = educationService.findByEducationIdCurriculumVitaeDetail(curriculumVitaeDetail.getId());
		Education education = new Education();
		if(listEducation.size()>0) {
			education = listEducation.get(0);
		}
		List<Experience> listExperience = experienceService.findByExperienceIdCurriculumVitaeDetail(curriculumVitaeDetail.getId());
		Experience experience = new Experience();
		if(listExperience.size()>0) {
			experience = listExperience.get(0);
		}
		List<SkillDetail> skillDetail = skillDetailService.findByEducationIdCurriculumVitaeDetail(curriculumVitaeDetail.getId());
		List<HobbyDetail> hobbyDetail = hobbyDetailService.getLisHobbyDetail(curriculumVitaeDetail.getId());
		List<JobGoalDetail> jobGoalDetail = jobGoalDetailService.getListGoalDetail(curriculumVitaeDetail.getId());

		model.addAttribute("candidate",candidate);
		model.addAttribute("curriculumVitaeDetail",curriculumVitaeDetail);
		model.addAttribute("curriculumVitae",curriculumVitae);
		model.addAttribute("education", education);
		model.addAttribute("experience", experience);
		model.addAttribute("skillDetail", skillDetail);
		model.addAttribute("hobbyDetail", hobbyDetail);
		model.addAttribute("jobGoalDetail",jobGoalDetail);
		return "components/cv/EditCV";
	}
	@RequestMapping(value = "/saveEditCV/{id}")
	public String saveEditCV(@RequestParam("file") MultipartFile file,Principal principal,Model model,@PathVariable(name = "id") int id, Education education,
			Experience experience,HttpServletRequest request) {
		educationService.deleteEducationByCurriculumVitaeDetailId(id);
		experienceService.deleteExperienceByCurriculumVitaeDetailId(id);
		hobbyDetailService.deleteHobbyDetailByCurriculumVitaeDetailId(id);
		skillDetailService.deleteSkillDetailByCurriculumVitaeDetailId(id);
		jobGoalDetailService.deleteJobGoalDetailByCurriculumVitaeDetailId(id);
		CurriculumVitaeDetail curriculumVitaeDetail = curriculumVitaeDetailService.getCurriculumVitaeDetailbyID(id);
		CurriculumVitae curriculumVitae = curriculumVitaeDetail.getCurriculumVitae();
		curriculumVitae.setUpdatedDate(new Date());
		if(file.getSize()!=0) {
			curriculumVitae.setFileName(storageService.saveWithCustomName(file,UUID.randomUUID().toString()));
		}

		curriculumVitaeService.editCurriculumVitae(curriculumVitae);

//		curriculumVitaeDetailService.editCurriculumVitaeDetail(curriculumVitaeDetail);


		education.setCurriculumVitaeDetail(curriculumVitaeDetail);
		experience.setCurriculumVitaeDetail(curriculumVitaeDetail);
		System.out.println(curriculumVitae);
		System.out.println(curriculumVitaeDetail);
		System.out.println(experience);
		System.out.println(education);
		educationService.editEducation(education);
		experienceService.editExperience(experience);
		
		List<SkillDetail> skillDetail = new ArrayList<SkillDetail>();

		if(request.getParameterValues("kynangmem") !=null) {
			for(int i =0;i<request.getParameterValues("kynangmem").length;i++) {
				skillDetail.add(new SkillDetail(request.getParameterValues("kynangmem")[i],null,"kỹ năng mềm"));
				skillDetail.get(skillDetail.size()-1).setCurriculumVitaeDetail(curriculumVitaeDetail);
			}
		}

		if(request.getParameterValues("tinhoc") != null) {
			for(int i =0;i<request.getParameterValues("tinhoc").length;i+=2) {
				skillDetail.add(new SkillDetail(request.getParameterValues("tinhoc")[i],request.getParameterValues("tinhoc")[i+1],"Tin học"));
				skillDetail.get(skillDetail.size()-1).setCurriculumVitaeDetail(curriculumVitaeDetail);
			}
		}
		if(request.getParameterValues("ngoaingu")!=null) {
			for(int i =0;i<request.getParameterValues("ngoaingu").length;i+=2) {
				skillDetail.add(new SkillDetail(request.getParameterValues("ngoaingu")[i],request.getParameterValues("ngoaingu")[i+1],"Ngoại ngữ"));
				skillDetail.get(skillDetail.size()-1).setCurriculumVitaeDetail(curriculumVitaeDetail);
			}
		}


		if(skillDetail.size() > 0) {
			System.out.println("Ky nang");

			for(int i =0;i<skillDetail.size();i++) {
				skillDetailService.saveSkillDetail(skillDetail.get(i));
				System.out.println(skillDetail.get(i));
			}
		}

		List<HobbyDetail> hobbyDetail = new ArrayList<HobbyDetail>();
		if(request.getParameterValues("hobby")!=null) {
			for(int i =0;i<request.getParameterValues("hobby").length;i++) {
				hobbyDetail.add(new HobbyDetail(request.getParameterValues("hobby")[i]));
				hobbyDetail.get(hobbyDetail.size()-1).setCurriculumVitaeDetail(curriculumVitaeDetail);
			}
		}


		if(hobbyDetail.size()>0) {
			System.out.println("So thich");
			for(int i =0;i<hobbyDetail.size();i++) {
				hobbyDetailService.createHobbyDetail(hobbyDetail.get(i));
				System.out.println(hobbyDetail.get(i));
			}
		}

		List<JobGoalDetail> jobGoalDetail = new ArrayList<JobGoalDetail>();
		if(request.getParameterValues("jobGoal")!=null) {
			for(int i =0;i<request.getParameterValues("jobGoal").length;i++) {
				jobGoalDetail.add(new JobGoalDetail(request.getParameterValues("jobGoal")[i]));
				jobGoalDetail.get(jobGoalDetail.size()-1).setCurriculumVitaeDetail(curriculumVitaeDetail);
			}
		}
		if(jobGoalDetail.size()>0) {
			System.out.println("Muc tieu nghe nghiep");
			for(int i =0;i<jobGoalDetail.size();i++) {
				jobGoalDetailService.createJobDetail(jobGoalDetail.get(i));
				System.out.println(jobGoalDetail.get(i));
			}
		}

		return "redirect:/cv/editCV/" + curriculumVitae.getId();
	}
}
