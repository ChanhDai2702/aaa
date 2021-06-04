package com.fpt.intern.bestcv.controller;

import java.security.Principal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Notification;
import com.fpt.intern.bestcv.entity.RecruimentDetail;
import com.fpt.intern.bestcv.entity.RecruimentNews;
import com.fpt.intern.bestcv.entity.RecruimentNewsCandidates;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.model.LoggedUser;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.DetailRegisterVipService;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.JobService;
import com.fpt.intern.bestcv.service.NotificationService;
import com.fpt.intern.bestcv.service.RecruimentDetailService;
import com.fpt.intern.bestcv.service.RecruimentNewsCandidatesService;
import com.fpt.intern.bestcv.service.RecruimentNewsService;
import com.fpt.intern.bestcv.service.RecruiterService;
import com.fpt.intern.bestcv.service.impl.MailService;
import com.fpt.intern.bestcv.utility.PageURLUtility;

@Controller
@RequestMapping("/news")
public class RecruimentNewsController {
	
	@Autowired
	private AspNetUsersService aspNetUsersService;
	@Autowired
	private DetailRegisterVipService detailRegisterVipService;
	@Autowired
	private RecruimentNewsService recruimentNewsService;
	@Autowired
	private RecruimentDetailService recruimentDetailService;
	@Autowired
	private IndustryService industryService;
	@Autowired
	private JobService jobService;
	@Autowired
	private MailService mailService;
	DecimalFormat df = new DecimalFormat("#,###,##0.00");
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private RecruiterService recruiterService;
	@Autowired
	private RecruimentNewsCandidatesService recruimentNewsCandidatesService;
	
	@Autowired
	private AspNetUsersService aspNetUserService;
	@Autowired
	private CandidateService candidateService;
	@GetMapping
	public String redriectToHomePage() {
		return "redirect:/";
	}
	@RequestMapping(value = "/{id}")
	public synchronized String showDetailRecruimentNewsPage(@PathVariable(name = "id") String strId, Model model,
			Authentication authentication, Principal principal) {
		try {
			
			int newsId = Integer.parseInt(strId);
			RecruimentNews news = recruimentNewsService.getRecruimentNewsById(newsId);
			if (news == null)
				return PageURLUtility.PAGE_404;
			else if (authentication == null) {
				if(news.getStatus().equalsIgnoreCase("Chờ duyệt"))
					return PageURLUtility.PAGE_404;
				else
					recruimentNewsService.addOneViewToNews(news.getId());
			}else {
				AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName());
//				LoggedUser users = (LoggedUser) authentication.getPrincipal();
				if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_NTD"))) {
					if(( recruiterService.getRecruiterByUsersId(aspNetUsers.getId()).getId() == news.getRecruiter().getId()))
						model.addAttribute("isOwner", true);
					else
						return PageURLUtility.PAGE_401; //Chỉ NTD tạo bài đăng ms có quyền xem và sửa
				} else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_UTV"))) {
					RecruimentNewsCandidates recruimentNewsCandidates = recruimentNewsCandidatesService
							.getRecruimentNewsCandidatesByRecruimentNewsIdAndCandidatesId(newsId,
									( candidateService.getCandidateByUsersId(aspNetUsers.getId())).getId());
					model.addAttribute("isLike",
							recruimentNewsCandidates == null ? false : recruimentNewsCandidates.isLike());
					recruimentNewsService.addOneViewToNews(news.getId());
				}else
					return PageURLUtility.PAGE_401; //ADMIN và sub-admin có function khác xử lý việc xem tin tuyển dụng
			}
			RecruimentDetail detail = recruimentDetailService.getRecruimentDetailById(news.getId());
			model.addAttribute("news", news);
			model.addAttribute("detail" ,detail);
			long countLike = recruimentNewsCandidatesService.countByLike(newsId);
			model.addAttribute("countLike", countLike);
			return PageURLUtility.RECRUIMENTNEWS_DETAIL;
		} catch (NumberFormatException e) {
			return PageURLUtility.PAGE_404;
		} catch (Exception e) {
			e.printStackTrace();
			return PageURLUtility.PAGE_500;
		}
	}

	@PreAuthorize("hasRole('ROLE_UTV')")
	@RequestMapping(value = "/like/{id}")
	public String likeAndUnlikeRecruimentNews(@PathVariable(name = "id") String strId, Authentication authentication) {
		try {
			int newsId = Integer.parseInt(strId);
			RecruimentNews news = recruimentNewsService.getRecruimentNewsById(newsId);
			if (news == null)
				return PageURLUtility.PAGE_404;
			// Get infomation user login
			LoggedUser users = (LoggedUser) authentication.getPrincipal();
			Candidate candidate = (Candidate) users.getInfo();
			RecruimentNewsCandidates recruimentNewsCandidates = recruimentNewsCandidatesService
					.getRecruimentNewsCandidatesByRecruimentNewsIdAndCandidatesId(newsId, candidate.getId());
			if (recruimentNewsCandidates == null) {
				recruimentNewsCandidates = new RecruimentNewsCandidates(news, candidate, true);
			} else {
				recruimentNewsCandidates.setLike(!recruimentNewsCandidates.isLike());
			}
			recruimentNewsCandidatesService.saveRecruimentNewsCandidates(recruimentNewsCandidates);
			return PageURLUtility.REDIRECT_RECRUIMENTNEWS_PAGE + newsId;
		} catch (NumberFormatException e) {
			return PageURLUtility.PAGE_404;
		} catch (Exception e) {
			e.printStackTrace();
			return PageURLUtility.PAGE_500;
		}
	}
	
	@PreAuthorize("hasRole('ROLE_UTV')")
	@RequestMapping(value = "/recruitment/{id}")
	public String Recruitment(@PathVariable(name = "id") String strId, Authentication authentication, Principal principal ) {
		try {
			int newsId = Integer.parseInt(strId);
			RecruimentNews news = recruimentNewsService.getRecruimentNewsById(newsId);
			RecruimentDetail detail = recruimentDetailService.getRecruimentDetailById(news.getId());
			
			if (news == null)
				return PageURLUtility.PAGE_404;
			
			AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(principal.getName());
			Candidate candidate = candidateService.getCandidateByUsersId(aspNetUsers.getId());
			
			mailService.sendEmailNotificationCadidate("nguyenvanvy1405@gmail.com", candidate, news, detail);
			AspNetUsers asp =  aspNetUserService.getAspNetUsersByID(news.getRecruiter().getUsers().getId());
			String content = "Ứng tuyển viên "+candidate.getFullName().toUpperCase()+" đã ứng tuyển vào vị trí "+news.getRecruimentDetail().getPosition().toString();
			Notification notifi = new Notification(content,Date.valueOf(LocalDate.now()),news.getStatus().toString(), news,null,asp ,"UTV ứng tuyển");
			notificationService.saveNotifications(notifi);
			
			
			return PageURLUtility.REDIRECT_RECRUIMENTNEWS_PAGE + newsId;
		} catch (NumberFormatException e) {
			return PageURLUtility.PAGE_404;
		} catch (Exception e) {
			e.printStackTrace();
			return PageURLUtility.PAGE_500;
		}
	}

	@PreAuthorize("hasRole('ROLE_NTD')")
	@RequestMapping(value = "/create")
	public String showCreateNewsPage(Model model, Authentication authentication) {
		try {
			LoggedUser users = (LoggedUser) authentication.getPrincipal();
			Recruiter recruiter = (Recruiter) users.getInfo();
			
			if(recruimentNewsService.overLimitationCreateRecuimentNewsByRecruiterId(recruiter.getId()))
				if(!detailRegisterVipService.existsVipPackageNotExpByRecruiterId(recruiter.getId())) {
					model.addAttribute("vipAlert", true);
				}
			RecruimentNews news = new RecruimentNews();
			model.addAttribute("news", news);
			model.addAttribute("industrys", industryService.getListAllIndustry());
			model.addAttribute("jobs", jobService.getListAllJob());
			return PageURLUtility.RECRUIMENTNEWS_CREATE;
		} catch (Exception e) {
			e.printStackTrace();
			return PageURLUtility.PAGE_500;
		}
	}

	@PreAuthorize("hasRole('ROLE_NTD')")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createRecruimentNews(Model model, @ModelAttribute("news") RecruimentNews news, RedirectAttributes ra,
			Authentication authentication) {
		try {
			LoggedUser users = (LoggedUser) authentication.getPrincipal();
			Recruiter recruiter = (Recruiter) users.getInfo();
			if(recruimentNewsService.overLimitationCreateRecuimentNewsByRecruiterId(recruiter.getId()))
				if(!detailRegisterVipService.existsVipPackageNotExpByRecruiterId(recruiter.getId())) {
					ra.addFlashAttribute("vipAlert", true);
					return PageURLUtility.RECRUIMENTNEWS_REDIRECT_CREATE;
				}
			news.setRecruiter(recruiter);
			news.setStatus("Chờ Duyệt");
			recruimentNewsService.saveRecruimentNews(news);
			ra.addFlashAttribute("success", "Tạo tin tuyển dụng thành công! Vui lòng chờ quản trị viên duyệt bài.");
			RecruimentNews result = recruimentNewsService.getRecruimentNewsById(news.getId());
			
			
			System.out.println("-----------------------------------------------------"+result.toString());
			if (result != null) {
				ra.addFlashAttribute("success", "Tạo tin tuyển dụng thành công! Vui lòng chờ quản trị viên duyệt bài.");
				RecruimentDetail result1 = recruimentDetailService.getRecruimentDetailById(news.getId());	
				
				mailService.sendEmailNotificationRecruiter("nguyenvanvy1405@gmail.com", recruiter, result, result1);
				AspNetUsers aspUser = aspNetUserService.getAspNetUserByRole_Admin("ROLE_ADMIN");
				String content = "Nhà tuyển dụng "+recruiter.getCompanyName().toString().toUpperCase()+" có một tin tuyển dụng đang chờ duyệt";
				Notification notifi = new Notification(content,Date.valueOf(LocalDate.now()),news.getStatus().toString(), news,null, aspUser,"Tin chờ duyệt");
				
				notificationService.saveNotifications(notifi);
				
			} else {
				ra.addAttribute("news", news);
				ra.addFlashAttribute("error", "Có lỗi khi tạo tin tuyển dụng! Vui lòng thử lại.");
			}
			
			return PageURLUtility.RECRUIMENTNEWS_REDIRECT_CREATE;
		} catch (Exception e) {
			e.printStackTrace();
			return PageURLUtility.PAGE_500;
		}
	}
	
	@PreAuthorize("hasRole('ROLE_NTD')")
	@RequestMapping(value = "/edit/{id}")
	public String showEditNewsPage(@PathVariable(name = "id") String strId, Model model, Authentication authentication) {
		try {
			int newsId = Integer.parseInt(strId);
			RecruimentNews news = recruimentNewsService.getRecruimentNewsById(newsId);
			if (news == null)
				return PageURLUtility.PAGE_404;
			LoggedUser users = (LoggedUser) authentication.getPrincipal();
			if(news.getRecruiter().getId() != ((Recruiter)users.getInfo()).getId())
				return PageURLUtility.PAGE_401;
			model.addAttribute("news", news);
			model.addAttribute("industrys", industryService.getListAllIndustry());
			model.addAttribute("jobs", jobService.getListAllJob());
			return PageURLUtility.RECRUIMENTNEWS_EDIT;
		} catch (NumberFormatException e) {
			return PageURLUtility.PAGE_404;
		} catch (Exception e) {
			e.printStackTrace();
			return PageURLUtility.PAGE_500;
		}
	}
	@PreAuthorize("hasRole('ROLE_NTD')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String saveEditedRecruimentNews(@PathVariable(name = "id") String strId, @ModelAttribute("news") RecruimentNews news, RedirectAttributes ra,
			Authentication authentication) {
		try {
			int id = Integer.parseInt(strId);
			RecruimentNews newsFind= recruimentNewsService.getRecruimentNewsById(id);
			if (newsFind==null)
				return PageURLUtility.PAGE_404;
			LoggedUser users = (LoggedUser) authentication.getPrincipal();
			Recruiter recruiter = (Recruiter) users.getInfo();
			if(newsFind.getRecruiter().getId() != recruiter.getId())
				return PageURLUtility.PAGE_401;
			
			newsFind.setFillingTime(news.getFillingTime());
			newsFind.setRequirements(news.getRequirements());
			newsFind.setStatus("Chưa duyệt");
			newsFind.setWorkDescription(news.getWorkDescription());
			news.getRecruimentDetail().setId(newsFind.getId());
			news.getRecruimentDetail().setRecruimentNews(newsFind);
			newsFind.setRecruimentDetail(news.getRecruimentDetail());
			recruimentNewsService.saveRecruimentNews(newsFind);
			ra.addFlashAttribute("success", "Sửa tin tuyển dụng thành công! Vui lòng chờ quản trị viên duyệt bài.");
			return PageURLUtility.RECRUIMENTNEWS_REDIRECT_EDIT+id;
		} catch (Exception e) {
			e.printStackTrace();
			return PageURLUtility.PAGE_500;
			
			
		}
	}
}
