package com.fpt.intern.bestcv.seed;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fpt.intern.bestcv.entity.Admin;
import com.fpt.intern.bestcv.entity.AspNetRoles;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.BusinessOrganization;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.CurriculumVitae;
import com.fpt.intern.bestcv.entity.CurriculumVitaeDetail;
import com.fpt.intern.bestcv.entity.Education;
import com.fpt.intern.bestcv.entity.Experience;
import com.fpt.intern.bestcv.entity.HobbyDetail;
import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.entity.Job;
import com.fpt.intern.bestcv.entity.JobGoalDetail;
import com.fpt.intern.bestcv.entity.PriorityAddress;
import com.fpt.intern.bestcv.entity.RecruimentDetail;
import com.fpt.intern.bestcv.entity.RecruimentNews;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.entity.SkillDetail;
import com.fpt.intern.bestcv.reposistory.AdminReposistory;
import com.fpt.intern.bestcv.reposistory.AspNetRolesReposistory;
import com.fpt.intern.bestcv.reposistory.AspNetUsersReposistory;
import com.fpt.intern.bestcv.reposistory.BusinessOrganizationReposistory;
import com.fpt.intern.bestcv.reposistory.CandidateReposistory;
import com.fpt.intern.bestcv.reposistory.CurriculumVitaeDetailReposistory;
import com.fpt.intern.bestcv.reposistory.CurriculumVitaeReposistory;
import com.fpt.intern.bestcv.reposistory.EducationRepository;
import com.fpt.intern.bestcv.reposistory.ExperienceReposistory;
import com.fpt.intern.bestcv.reposistory.HobbyDetailReposistory;
import com.fpt.intern.bestcv.reposistory.IndustryReposistory;
import com.fpt.intern.bestcv.reposistory.JobGoalDetailRepository;
import com.fpt.intern.bestcv.reposistory.JobReposistory;
import com.fpt.intern.bestcv.reposistory.PriorityAddressReposistory;
import com.fpt.intern.bestcv.reposistory.RecruimentDetailReposistory;
import com.fpt.intern.bestcv.reposistory.RecruimentNewsReposistory;
import com.fpt.intern.bestcv.reposistory.RecruiterReposistory;
import com.fpt.intern.bestcv.reposistory.SkillDetailReposistory;
import com.fpt.intern.bestcv.service.CurriculumVitaeDetailService;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private AdminReposistory adminRepository;

	@Autowired
	private CandidateReposistory candidateReposistory;
	
	@Autowired
	private IndustryReposistory industryReposistory;
	
	@Autowired
	private JobReposistory jobReposistory;
	
	@Autowired
	private BusinessOrganizationReposistory businessOrganizationReposistory;
	
	@Autowired
	private PriorityAddressReposistory priorityAddressReposistory;
	
	@Autowired
	private RecruiterReposistory recruiterReposistory;
	
	@Autowired
	private AspNetUsersReposistory userRepository;

	@Autowired
	private AspNetRolesReposistory roleRepository;
	
	@Autowired
	private RecruimentNewsReposistory recruimentNewsReposistory;
	
	@Autowired
	private RecruimentDetailReposistory recruimentDetailReposistory;
	
	@Autowired
	private CurriculumVitaeDetailReposistory curriculumVitaeDetailReposistory;
	
	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private ExperienceReposistory experienceReposistory;
	
	@Autowired
	private HobbyDetailReposistory hobbyDetailReposistory;
	
	@Autowired
	private SkillDetailReposistory skillDetailReposistory;
	
	@Autowired
	private JobGoalDetailRepository jobGoalDetailRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CurriculumVitaeReposistory curriculumVitaeReposistory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		//industry
		if(!industryReposistory.findByIndustryName("C??ng ngh??? th??ng tin").isPresent()) {
			industryReposistory.save(new Industry("C??ng ngh??? th??ng tin"));
		}
		if(!industryReposistory.findByIndustryName("Kinh t???").isPresent()) {
			industryReposistory.save(new Industry("Kinh t???"));
		}
		if(!industryReposistory.findByIndustryName("Ng??n ng???").isPresent()) {
			industryReposistory.save(new Industry("Ng??n ng???"));
		}
		if(!industryReposistory.findByIndustryName("Nh?? h??ng - kh??ch s???n").isPresent()) {
			industryReposistory.save(new Industry("Nh?? h??ng - kh??ch s???n"));
		}
		//Job
		if(!jobReposistory.findByJobName("Coder").isPresent()) {
			jobReposistory.save(new Job("Coder", industryReposistory.findByIndustryName("C??ng ngh??? th??ng tin").get()));
		}
		if(!jobReposistory.findByJobName("Designer").isPresent()) {
			jobReposistory.save(new Job("Designer", industryReposistory.findByIndustryName("C??ng ngh??? th??ng tin").get()));
		}
		if(!jobReposistory.findByJobName("Tester").isPresent()) {
			jobReposistory.save(new Job("Tester", industryReposistory.findByIndustryName("C??ng ngh??? th??ng tin").get()));
		}
		if(!jobReposistory.findByJobName("K??? to??n").isPresent()) {
			jobReposistory.save(new Job("K??? to??n", industryReposistory.findByIndustryName("Kinh t???").get()));
		}
		if(!jobReposistory.findByJobName("L??? t??n").isPresent()) {
			jobReposistory.save(new Job("L??? t??n", industryReposistory.findByIndustryName("Nh?? h??ng - kh??ch s???n").get()));
		}
		if(!jobReposistory.findByJobName("Ph???c v???").isPresent()) {
			jobReposistory.save(new Job("Ph???c v???", industryReposistory.findByIndustryName("Nh?? h??ng - kh??ch s???n").get()));
		}
		if(!jobReposistory.findByJobName("Ng??n ng??? Anh").isPresent()) {
			jobReposistory.save(new Job("Ng??n ng??? Anh", industryReposistory.findByIndustryName("Ng??n ng???").get()));
		}
		if(!jobReposistory.findByJobName("Ng??n ng??? H??n").isPresent()) {
			jobReposistory.save(new Job("Ng??n ng??? H??n", industryReposistory.findByIndustryName("Ng??n ng???").get()));
		}
		if(!jobReposistory.findByJobName("Ng??n ng??? Nh???t").isPresent()) {
			jobReposistory.save(new Job("Ng??n ng??? Nh???t", industryReposistory.findByIndustryName("Ng??n ng???").get()));
		}
		
		//BusinessOrganization
		if(!businessOrganizationReposistory.findByBusinessModelName("Doanh nghi???p t?? nh??n").isPresent()) {
			businessOrganizationReposistory.save(new BusinessOrganization("Doanh nghi???p t?? nh??n"));
		}
		if(!businessOrganizationReposistory.findByBusinessModelName("C??ng ty tr??ch nhi???m h???u h???n").isPresent()) {
			businessOrganizationReposistory.save(new BusinessOrganization("C??ng ty tr??ch nhi???m h???u h???n"));
		}
		if(!businessOrganizationReposistory.findByBusinessModelName("C??ng ty c??? ph???n").isPresent()) {
			businessOrganizationReposistory.save(new BusinessOrganization("C??ng ty c??? ph???n"));
		}
		if(!businessOrganizationReposistory.findByBusinessModelName("C??ng ty h???p danh").isPresent()) {
			businessOrganizationReposistory.save(new BusinessOrganization("C??ng ty h???p danh"));
		}
		//PriorityAddress
		if(!priorityAddressReposistory.findByPriorityAddressName("C???n Th??").isPresent()) {
			List<String> addresses= Arrays.asList("C???n Th??",
					"???? N???ng",
					"H???i Ph??ng",
					"H?? N???i",
					"TP HCM");
			for (String string : addresses) {
				priorityAddressReposistory.save(new PriorityAddress(string));
			}
		}
		// Roles
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new AspNetRoles(UUID.randomUUID().toString(), "ROLE_ADMIN"));
		}
		if (roleRepository.findByName("ROLE_SUBADMIN") == null) {
			roleRepository.save(new AspNetRoles(UUID.randomUUID().toString(), "ROLE_SUBADMIN"));
		}

		if (roleRepository.findByName("ROLE_NTD") == null) {
			roleRepository.save(new AspNetRoles(UUID.randomUUID().toString(), "ROLE_NTD"));
		}

		if (roleRepository.findByName("ROLE_UTV") == null) {
			roleRepository.save(new AspNetRoles(UUID.randomUUID().toString(), "ROLE_UTV"));
		}

		// Admin account
		if (!userRepository.findByEmail("admin@bestcv.com").isPresent()) {
			AspNetUsers admin = new AspNetUsers();
			admin.setId(UUID.randomUUID().toString());
			admin.setEmail("admin@bestcv.com");
			admin.setEmailComfirmed(true);
			admin.setUserName("admin@bestcv.com");
			admin.setPasswordHash(passwordEncoder.encode("123456"));
			HashSet<AspNetRoles> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			admin.setRoles(roles);
			userRepository.save(admin);
			Admin adminUser = new Admin("Nguy???n Ng???c H??", "656/80 Quang Trung, p11, qu???n G?? V???p, tp. H??? Ch?? Minh", "emoji.png");
			adminUser.setAspNetUsers(admin);
			adminRepository.save(adminUser);
		}

//		// Sub-Admin account
//		if (!userRepository.findByEmail("sub-admin@bestcv.com").isPresent()) {
//			AspNetUsers subAdmin = new AspNetUsers();
//			subAdmin.setId(UUID.randomUUID().toString());
//			subAdmin.setEmail("sub-admin@bestcv.com");
//			subAdmin.setEmailComfirmed(true);
//			subAdmin.setUserName("sub-admin@bestcv.com");
//			subAdmin.setPasswordHash(passwordEncoder.encode("123456"));
//			HashSet<AspNetRoles> roles = new HashSet<>();
//			roles.add(roleRepository.findByName("ROLE_SUBADMIN"));
//			subAdmin.setRoles(roles);
//			userRepository.save(subAdmin);
//		}
//
		// UTV account
		if (!userRepository.findByEmail("utv@bestcv.com").isPresent()) {
			AspNetUsers utv = new AspNetUsers();
			utv.setId(UUID.randomUUID().toString());
			utv.setEmail("utv@bestcv.com");
			utv.setEmailComfirmed(true);
			utv.setUserName("utv@bestcv.com");
			utv.setPasswordHash(passwordEncoder.encode("123456"));
			HashSet<AspNetRoles> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_UTV"));
			utv.setRoles(roles);
			userRepository.save(utv);
			Candidate candidate = new Candidate("Nguy???n Ng???c H??", "656/80 Quang Trung, p11, qu???n G?? V???p, tp. H??? Ch?? Minh", "emoji.png", new Date(), "Nam");
			candidate.setIndustry(industryReposistory.findByIndustryName("C??ng ngh??? th??ng tin").get());
			candidate.setPriorityAddress(priorityAddressReposistory.findByPriorityAddressName("TP HCM").get());
			candidate.setUsers(utv);
			candidateReposistory.save(candidate);
		}
		if (!userRepository.findByEmail("nguyenvanvy1405@gmail.com").isPresent()) {
			AspNetUsers utv = new AspNetUsers();
			utv.setId(UUID.randomUUID().toString());
			utv.setEmail("nguyenvanvy1405@gmail.com");
			utv.setEmailComfirmed(true);
			utv.setUserName("nguyenvanvy1405@gmail.com");
			utv.setPasswordHash(passwordEncoder.encode("123456"));
			HashSet<AspNetRoles> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_UTV"));
			utv.setRoles(roles);
			userRepository.save(utv);
			Candidate candidate = new Candidate("Nguy???n V??n V???", "656/80 Quang Trung, p11, qu???n G?? V???p, tp. H??? Ch?? Minh", "emoji.png", new Date(), "Nam");
			candidate.setIndustry(industryReposistory.findByIndustryName("C??ng ngh??? th??ng tin").get());
			candidate.setPriorityAddress(priorityAddressReposistory.findByPriorityAddressName("TP HCM").get());
			candidate.setUsers(utv);
			Candidate candidateTwo = new Candidate("mu???i mu???i", "33/56", "emoji.png", new Date(), "n???");
			candidateTwo.setIndustry(industryReposistory.findByIndustryName("C??ng ngh??? th??ng tin").get());
			candidateTwo.setPriorityAddress(priorityAddressReposistory.findByPriorityAddressName("TP HCM").get());
			candidateReposistory.save(candidate);
			CurriculumVitae curriculumVitae = new CurriculumVitae(new Date(), new Date(), 10, "false", 1, "muoi_muoi.png");
			CurriculumVitae curriculumVitae2 = new CurriculumVitae(new Date(), new Date(), 10, "false", 1, "nguyenChanhDai.png");
			CurriculumVitae curriculumVitae3 = new CurriculumVitae(new Date(), new Date(), 10, "false", 1, "NguyenVanVy.png");
			CurriculumVitae curriculumVitae4 = new CurriculumVitae(new Date(), new Date(), 10, "false", 1, "LeMinhHau.png");
			CurriculumVitae curriculumVitae5 = new CurriculumVitae(new Date(), new Date(), 10, "false", 1, "NguyenChiTin.png");
			CurriculumVitae curriculumVitae6 = new CurriculumVitae(new Date(), new Date(), 10, "false", 1, "NguyenThiMinh.png");
			
			
			curriculumVitae.setCandidate(candidate);
			curriculumVitae.setJob(jobReposistory.findByJobName("Coder").get());
			curriculumVitae2.setCandidate(candidate);
			curriculumVitae2.setJob(jobReposistory.findByJobName("Coder").get());
			curriculumVitae3.setCandidate(candidate);
			curriculumVitae3.setJob(jobReposistory.findByJobName("Coder").get());
			curriculumVitae4.setCandidate(candidate);
			curriculumVitae4.setJob(jobReposistory.findByJobName("Coder").get());
			curriculumVitae5.setCandidate(candidate);
			curriculumVitae5.setJob(jobReposistory.findByJobName("Coder").get());
			curriculumVitae6.setCandidate(candidate);
			curriculumVitae6.setJob(jobReposistory.findByJobName("Coder").get());
			curriculumVitaeReposistory.save(curriculumVitae);
			curriculumVitaeReposistory.save(curriculumVitae2);
			curriculumVitaeReposistory.save(curriculumVitae3);
			curriculumVitaeReposistory.save(curriculumVitae4);
			curriculumVitaeReposistory.save(curriculumVitae5);
			curriculumVitaeReposistory.save(curriculumVitae6);
			
			CurriculumVitaeDetail curriculumVitaeDetail1 = new CurriculumVitaeDetail("image/jpeg","FA-BackEnd");
			CurriculumVitaeDetail curriculumVitaeDetail2 = new CurriculumVitaeDetail("image/jpeg","FA-FontEnd");
			CurriculumVitaeDetail curriculumVitaeDetail3 = new CurriculumVitaeDetail("image/jpeg","FA-FontEnd");
			CurriculumVitaeDetail curriculumVitaeDetail4 = new CurriculumVitaeDetail("image/jpeg","FA-BackEnd");
			CurriculumVitaeDetail curriculumVitaeDetail5 = new CurriculumVitaeDetail("image/jpeg","FA-FontEnd");
			CurriculumVitaeDetail curriculumVitaeDetail6 = new CurriculumVitaeDetail("image/jpeg","FA-BackEnd");

			
			curriculumVitaeDetail1.setCurriculumVitae(curriculumVitae);
			curriculumVitaeDetail2.setCurriculumVitae(curriculumVitae2);
			curriculumVitaeDetail3.setCurriculumVitae(curriculumVitae3);
			curriculumVitaeDetail4.setCurriculumVitae(curriculumVitae4);
			curriculumVitaeDetail5.setCurriculumVitae(curriculumVitae5);
			curriculumVitaeDetail6.setCurriculumVitae(curriculumVitae6);
			
			curriculumVitaeDetailReposistory.save(curriculumVitaeDetail1);
			curriculumVitaeDetailReposistory.save(curriculumVitaeDetail2);
			curriculumVitaeDetailReposistory.save(curriculumVitaeDetail3);
			curriculumVitaeDetailReposistory.save(curriculumVitaeDetail4);
			curriculumVitaeDetailReposistory.save(curriculumVitaeDetail5);
			curriculumVitaeDetailReposistory.save(curriculumVitaeDetail6);
			
			
			Education education1 = new Education("K??? thu???t ph???n m???m",new Date(), new Date(),"C??ng ngh??? th??ng tin","?????i h???c c??ng nghi???p",5);
			Education education2 = new Education("Khoa h???c m??y t??nh",new Date(), new Date(),"C??ng ngh??? th??ng tin","?????i h???c c??ng nghi???p",8);
			Education education3 = new Education("K??? thu???t ph???n m???m",new Date(), new Date(),"C??ng ngh??? th??ng tin","?????i h???c c??ng nghi???p",7);
			Education education4 = new Education("Khoa h???c m??y t??nh",new Date(), new Date(),"C??ng ngh??? th??ng tin","?????i h???c c??ng nghi???p",9);
			Education education5 = new Education("K??? thu???t ph???n m???m",new Date(), new Date(),"C??ng ngh??? th??ng tin","?????i h???c c??ng nghi???p",5);
			Education education6 = new Education("K??? thu???t ph???n m???m",new Date(), new Date(),"C??ng ngh??? th??ng tin","?????i h???c c??ng nghi???p",4);
			
			education1.setCurriculumVitaeDetail(curriculumVitaeDetail1);
			education2.setCurriculumVitaeDetail(curriculumVitaeDetail2);
			education3.setCurriculumVitaeDetail(curriculumVitaeDetail3);
			education4.setCurriculumVitaeDetail(curriculumVitaeDetail4);
			education5.setCurriculumVitaeDetail(curriculumVitaeDetail5);
			education6.setCurriculumVitaeDetail(curriculumVitaeDetail6);
			
			educationRepository.save(education1);
			educationRepository.save(education2);
			educationRepository.save(education3);
			educationRepository.save(education4);
			educationRepository.save(education5);
			educationRepository.save(education6);

			Experience  experience1 = new Experience("Th???c t???p sinh c???a FA", new Date(), new Date(),"FPT Software","Trainee");
			Experience  experience2 = new Experience("Th???c t???p sinh c???a FA", new Date(), new Date(),"FPT Software","Trainee");
			Experience  experience3 = new Experience("Th???c t???p sinh c???a FA", new Date(), new Date(),"FPT Software","Trainee");
			Experience  experience4 = new Experience("Th???c t???p sinh c???a FA", new Date(), new Date(),"FPT Software","Trainee");
			Experience  experience5 = new Experience("Th???c t???p sinh c???a FA", new Date(), new Date(),"FPT Software","Trainee");
			Experience  experience6 = new Experience("Th???c t???p sinh c???a FA", new Date(), new Date(),"FPT Software","Trainee");

			experience1.setCurriculumVitaeDetail(curriculumVitaeDetail1);
			experience2.setCurriculumVitaeDetail(curriculumVitaeDetail2);
			experience3.setCurriculumVitaeDetail(curriculumVitaeDetail3);
			experience4.setCurriculumVitaeDetail(curriculumVitaeDetail4);
			experience5.setCurriculumVitaeDetail(curriculumVitaeDetail5);
			experience6.setCurriculumVitaeDetail(curriculumVitaeDetail6);
			
			experienceReposistory.save(experience1);
			experienceReposistory.save(experience2);
			experienceReposistory.save(experience3);
			experienceReposistory.save(experience4);
			experienceReposistory.save(experience5);
			experienceReposistory.save(experience6);
			
			
			HobbyDetail hobbyDetail1 = new HobbyDetail("Xem phim");
			HobbyDetail hobbyDetail2 = new HobbyDetail("Xem phim");
			HobbyDetail hobbyDetail3 = new HobbyDetail("Xem phim");
			HobbyDetail hobbyDetail4 = new HobbyDetail("Xem phim");			
			HobbyDetail hobbyDetail5 = new HobbyDetail("Xem phim");
			HobbyDetail hobbyDetail6 = new HobbyDetail("Xem phim");
			HobbyDetail hobbyDetail7 = new HobbyDetail("???? b??ng");
			HobbyDetail hobbyDetail8 = new HobbyDetail("Ch??i game");
			HobbyDetail hobbyDetail9 = new HobbyDetail("Leo n??i");
			
			hobbyDetail1.setCurriculumVitaeDetail(curriculumVitaeDetail1);
			hobbyDetail2.setCurriculumVitaeDetail(curriculumVitaeDetail2);
			hobbyDetail3.setCurriculumVitaeDetail(curriculumVitaeDetail3);
			hobbyDetail4.setCurriculumVitaeDetail(curriculumVitaeDetail4);
			hobbyDetail5.setCurriculumVitaeDetail(curriculumVitaeDetail5);
			hobbyDetail6.setCurriculumVitaeDetail(curriculumVitaeDetail6);
			hobbyDetail7.setCurriculumVitaeDetail(curriculumVitaeDetail2);
			hobbyDetail8.setCurriculumVitaeDetail(curriculumVitaeDetail3);
			hobbyDetail9.setCurriculumVitaeDetail(curriculumVitaeDetail2);

			hobbyDetailReposistory.save(hobbyDetail1);
			hobbyDetailReposistory.save(hobbyDetail2);
			hobbyDetailReposistory.save(hobbyDetail3);
			hobbyDetailReposistory.save(hobbyDetail4);
			hobbyDetailReposistory.save(hobbyDetail5);
			hobbyDetailReposistory.save(hobbyDetail6);
			hobbyDetailReposistory.save(hobbyDetail7);
			hobbyDetailReposistory.save(hobbyDetail8);
			hobbyDetailReposistory.save(hobbyDetail9);
			
			SkillDetail skillDetail1 = new SkillDetail("Ch??m ch???",null,"k??? n??ng m???m");
			SkillDetail skillDetail2 = new SkillDetail("Ch??m ch???",null,"k??? n??ng m???m");
			SkillDetail skillDetail3 = new SkillDetail("MS Word","Xu???t s???c","Tin h???c");
			SkillDetail skillDetail4 = new SkillDetail("MS Word","Trung b??nh","Tin h???c");
			SkillDetail skillDetail5 = new SkillDetail("MS Word","Gi???i","Tin h???c");
			SkillDetail skillDetail6 = new SkillDetail("Si??ng n??ng",null,"k??? n??ng m???m");
			SkillDetail skillDetail7 = new SkillDetail("MS Word","Gi???i","Tin h???c");
			SkillDetail skillDetail8 = new SkillDetail("Ti???ng Anh","Gi???i","Ngo???i ng???");
			SkillDetail skillDetail9 = new SkillDetail("Ti???ng Ph??p","Trung b??nh","Ngo???i ng???");
			SkillDetail skillDetail10 = new SkillDetail("Si??ng n??ng",null,"k??? n??ng m???m");
			SkillDetail skillDetail11 = new SkillDetail("Ti???ng Anh","Gi???i","Ngo???i ng???");
			SkillDetail skillDetail12 = new SkillDetail("Ti???ng Anh","Trung b??nh","Ngo???i ng???");
			SkillDetail skillDetail13 = new SkillDetail("MS Excel","Kh??","Tin h???c");
			SkillDetail skillDetail14 = new SkillDetail("Si??ng n??ng",null,"k??? n??ng m???m");
			SkillDetail skillDetail15 = new SkillDetail("Si??ng n??ng",null,"k??? n??ng m???m");
			SkillDetail skillDetail16 = new SkillDetail("Ti???ng Nh???t","Trung b??nh","Ngo???i ng???");
			SkillDetail skillDetail17 = new SkillDetail("Ti???ng Nh???t","Kh??","Ngo???i ng???");
			SkillDetail skillDetail18 = new SkillDetail("Ti???ng Anh","Y???u","Ngo???i ng???");
			SkillDetail skillDetail19 = new SkillDetail("MS Word","Kh??","Tin h???c");
			SkillDetail skillDetail20 = new SkillDetail("MS Excel","Gi???i","Tin h???c");

			skillDetail1.setCurriculumVitaeDetail(curriculumVitaeDetail1);
			skillDetail2.setCurriculumVitaeDetail(curriculumVitaeDetail2);
			skillDetail3.setCurriculumVitaeDetail(curriculumVitaeDetail3);
			skillDetail4.setCurriculumVitaeDetail(curriculumVitaeDetail4);
			skillDetail5.setCurriculumVitaeDetail(curriculumVitaeDetail5);
			skillDetail6.setCurriculumVitaeDetail(curriculumVitaeDetail6);
			skillDetail7.setCurriculumVitaeDetail(curriculumVitaeDetail1);
			skillDetail8.setCurriculumVitaeDetail(curriculumVitaeDetail2);
			skillDetail9.setCurriculumVitaeDetail(curriculumVitaeDetail3);
			skillDetail10.setCurriculumVitaeDetail(curriculumVitaeDetail4);
			skillDetail11.setCurriculumVitaeDetail(curriculumVitaeDetail5);
			skillDetail12.setCurriculumVitaeDetail(curriculumVitaeDetail6);
			skillDetail13.setCurriculumVitaeDetail(curriculumVitaeDetail1);
			skillDetail14.setCurriculumVitaeDetail(curriculumVitaeDetail2);
			skillDetail15.setCurriculumVitaeDetail(curriculumVitaeDetail3);
			skillDetail16.setCurriculumVitaeDetail(curriculumVitaeDetail4);
			skillDetail17.setCurriculumVitaeDetail(curriculumVitaeDetail6);
			skillDetail18.setCurriculumVitaeDetail(curriculumVitaeDetail1);
			skillDetail19.setCurriculumVitaeDetail(curriculumVitaeDetail6);
			skillDetail20.setCurriculumVitaeDetail(curriculumVitaeDetail5);

			skillDetailReposistory.save(skillDetail1);
			skillDetailReposistory.save(skillDetail2);
			skillDetailReposistory.save(skillDetail3);
			skillDetailReposistory.save(skillDetail4);
			skillDetailReposistory.save(skillDetail5);
			skillDetailReposistory.save(skillDetail6);
			skillDetailReposistory.save(skillDetail7);
			skillDetailReposistory.save(skillDetail8);
			skillDetailReposistory.save(skillDetail9);
			skillDetailReposistory.save(skillDetail10);
			skillDetailReposistory.save(skillDetail11);
			skillDetailReposistory.save(skillDetail12);
			skillDetailReposistory.save(skillDetail13);
			skillDetailReposistory.save(skillDetail14);
			skillDetailReposistory.save(skillDetail15);
			skillDetailReposistory.save(skillDetail16);
			skillDetailReposistory.save(skillDetail17);
			skillDetailReposistory.save(skillDetail18);
			skillDetailReposistory.save(skillDetail19);
			skillDetailReposistory.save(skillDetail20);
			
			
			JobGoalDetail jobGoalDetail1 = new JobGoalDetail("L????ng cao");
			JobGoalDetail jobGoalDetail2 = new JobGoalDetail("L????ng cao");
			JobGoalDetail jobGoalDetail3 = new JobGoalDetail("L????ng cao");
			JobGoalDetail jobGoalDetail4 = new JobGoalDetail("L????ng cao");
			JobGoalDetail jobGoalDetail5 = new JobGoalDetail("L????ng cao");
			JobGoalDetail jobGoalDetail6 = new JobGoalDetail("L????ng cao");
			
			jobGoalDetail1.setCurriculumVitaeDetail(curriculumVitaeDetail1);
			jobGoalDetail2.setCurriculumVitaeDetail(curriculumVitaeDetail2);
			jobGoalDetail3.setCurriculumVitaeDetail(curriculumVitaeDetail3);
			jobGoalDetail4.setCurriculumVitaeDetail(curriculumVitaeDetail4);
			jobGoalDetail5.setCurriculumVitaeDetail(curriculumVitaeDetail5);
			jobGoalDetail6.setCurriculumVitaeDetail(curriculumVitaeDetail6);

			jobGoalDetailRepository.save(jobGoalDetail1);
			jobGoalDetailRepository.save(jobGoalDetail2);
			jobGoalDetailRepository.save(jobGoalDetail3);
			jobGoalDetailRepository.save(jobGoalDetail4);
			jobGoalDetailRepository.save(jobGoalDetail5);
			jobGoalDetailRepository.save(jobGoalDetail6);

		}
		// NTD account
		if (!userRepository.findByEmail("ntd@bestcv.com").isPresent()) {
			AspNetUsers ntd = new AspNetUsers();
			ntd.setId(UUID.randomUUID().toString());
			ntd.setEmail("ntd@bestcv.com");
			ntd.setEmailComfirmed(true);
			ntd.setUserName("ntd@bestcv.com");
			ntd.setPasswordHash(passwordEncoder.encode("123456"));
			HashSet<AspNetRoles> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_NTD"));
			ntd.setRoles(roles);
			userRepository.save(ntd);
			
			Recruiter recruiter = new Recruiter("Nguy???n Ng???c H??", "656/80 Quang Trung, p11, qu???n G?? V???p, tp. H??? Ch?? Minh", "emoji.png");
			recruiter.setAspNetUsers(ntd);
			recruiter.setBusinessOrganization(businessOrganizationReposistory.findByBusinessModelName("C??ng ty c??? ph???n").get());
			recruiter.setPriorityAddress(priorityAddressReposistory.findByPriorityAddressName("TP HCM").get());
			recruiter.setIndustry(industryReposistory.findByIndustryName("C??ng ngh??? th??ng tin").get());
			recruiterReposistory.save(recruiter);
		}
		if (!userRepository.findByEmail("vanvyvvk98@gmail.com").isPresent()) {
			AspNetUsers ntd = new AspNetUsers();
			ntd.setId(UUID.randomUUID().toString());
			ntd.setEmail("vanvyvvk98@gmail.com");
			ntd.setEmailComfirmed(true);
			ntd.setUserName("vanvyvvk98@gmail.com");
			ntd.setPasswordHash(passwordEncoder.encode("123456"));
			HashSet<AspNetRoles> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_NTD"));
			ntd.setRoles(roles);
			userRepository.save(ntd);
			
			Recruiter recruiter = new Recruiter("Nguy???n V??n V???", "656/80 Quang Trung, p11, qu???n G?? V???p, tp. H??? Ch?? Minh", "emoji.png");
			recruiter.setAspNetUsers(ntd);
			recruiter.setBusinessOrganization(businessOrganizationReposistory.findByBusinessModelName("C??ng ty c??? ph???n").get());
			recruiter.setPriorityAddress(priorityAddressReposistory.findByPriorityAddressName("TP HCM").get());
			recruiter.setIndustry(industryReposistory.findByIndustryName("C??ng ngh??? th??ng tin").get());
			recruiterReposistory.save(recruiter);
		}
		//RecruimentNews
		if(!recruimentNewsReposistory.findById(1).isPresent())
		{
			RecruimentNews news = new RecruimentNews(0, new Date(), "<ul><li>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Aut commodi vero nihil non, voluptas incidunt pariatur illo cupiditate eligendi ex! Asperiores perferendis officiis possimus est cupiditate suscipit officia porro modi?</li></ul>", "<ul><li>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Aut commodi vero nihil non, voluptas incidunt pariatur illo cupiditate eligendi ex! Asperiores perferendis officiis possimus est cupiditate suscipit officia porro modi?</li></ul>", "???? duy???t", new Date());
			news.setRecruiter(recruiterReposistory.findById(1).get());
			RecruimentNews result = recruimentNewsReposistory.save(news);
			recruimentDetailReposistory.save(new RecruimentDetail(result.getId(), 5, "Nh??n vi??n", "Kh??ng", 10000000f, "To??n th???i gian", "5 n??m", result, jobReposistory.findByJobName("Coder").get()));
		}
		
		
		
		
	}

}