package com.fpt.intern.bestcv.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpt.intern.bestcv.entity.CurriculumVitaeDetail;
import com.fpt.intern.bestcv.entity.RecruimentDetail;
import com.fpt.intern.bestcv.service.CVRecruimentService;
import com.fpt.intern.bestcv.service.CurriculumVitaeDetailService;

import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.PriorityAddressService;
import com.fpt.intern.bestcv.service.RecruimentDetailService;
import com.fpt.intern.bestcv.service.RecruimentNewsCandidatesService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private PriorityAddressService priorityAddressService;

	@Autowired
	private IndustryService industryService;
	
	@Autowired
	private CVRecruimentService cvRecruimentService;
	
	@Autowired
	private CurriculumVitaeDetailService curriculumVitaeDetailService;
	
	@Autowired
	private RecruimentDetailService recruimentDetailService;

	@Autowired
	private RecruimentNewsCandidatesService recruimentNewsCandidatesService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') "
			+ " || hasRole('ROLE_NTD')")
	@GetMapping("/cv")
	public String searchCV(@RequestParam(value = "keyword",required = false) String keyword,
			@RequestParam(value = "page", required = false) String pageNo,
			@RequestParam(value = "industry", required = false) String industry,
			@RequestParam(value = "priorityAddress", required = false) String priorityAddress, Model model) {
		int pageNo_ = 1;
		final int pageSize = 10;
		if(pageNo!=null) {
			try {
				pageNo_=Integer.parseInt(pageNo);
			} catch (Exception e2) {
			}
		}
		Page<CurriculumVitaeDetail> page=null;
		if(industry == null && priorityAddress == null) {
			page=curriculumVitaeDetailService.findByKeyword(keyword, pageNo_, pageSize);
		}else {
			if (industry == null) {
				industry = "";
			} else {
				model.addAttribute("industryId", Integer.parseInt(industry));
			}
			if (priorityAddress == null) {
				priorityAddress = "";
			} else {
				model.addAttribute("priorityAddressId", Integer.parseInt(priorityAddress));
			}
				page = curriculumVitaeDetailService.findByKeywordAdvance(keyword, industry, priorityAddress, pageNo_, pageSize);
		}
		Map<CurriculumVitaeDetail, Integer> mapCurriculumVitaeDetail=new HashMap<CurriculumVitaeDetail, Integer>();
		page.getContent().forEach(e->{
			int count =cvRecruimentService.getLikeCountByCV(e.getCurriculumVitae().getId());
			mapCurriculumVitaeDetail.put(e, count);
		});
		model.addAttribute("list_cv", mapCurriculumVitaeDetail); 
		model.addAttribute("industry", industryService.getListAllIndustry());
		model.addAttribute("priorityAddress", priorityAddressService.findAll());
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("pageNo", pageNo_);
		model.addAttribute("keyword", keyword);
		return "components/search/search-cv";
	}
	
	@GetMapping("/news")
	public String seacrhReceruimentNews(@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) String pageNo,
			@RequestParam(value = "industry", required = false) String industry,
			@RequestParam(value = "priorityAddress", required = false) String priorityAddress, Model model) {
		int pageNo_ = 1;
		final int pageSize = 10;
		double salary = -1;
		try {
			if (pageNo != null) {
				pageNo_ = Integer.parseInt(pageNo);
			}
			salary = Double.parseDouble(keyword);
		} catch (Exception e2) {
		}
		//
		model.addAttribute("industry", industryService.getListAllIndustry());
		model.addAttribute("priorityAddress", priorityAddressService.findAll());
		//
		Page<RecruimentDetail> page = null;
		if (industry == null && priorityAddress == null) {
			if (salary == -1) {
				page = recruimentDetailService.findByKeywords(keyword, pageNo_, pageSize);
			} else {
				page = recruimentDetailService.findBySalary(salary, 5000, pageNo_, pageSize);
			}
		} else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
					|| auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_UTV"))) {
				if (industry == null) {
					industry = "";
				} else {
					model.addAttribute("industryId", Integer.parseInt(industry));
				}
				if (priorityAddress == null) {
					priorityAddress = "";
				} else {
					model.addAttribute("priorityAddressId", Integer.parseInt(priorityAddress));
				}
				if (salary == -1) {
					page = recruimentDetailService.findByKeywordsAdvance(keyword, industry, priorityAddress, pageNo_,
							pageSize);
				} else {
					page = recruimentDetailService.findBySalaryAdvance(salary, 5000, industry, priorityAddress, pageNo_,
							pageSize);
				}
			} else {
				model.addAttribute("checkRole", "Vui lòng đăng nhập để thực hiện chức năng này");
				return "components/search/search-recruiment";
			}
		}
		Map<RecruimentDetail, Long> recruimentDetails = new HashMap<RecruimentDetail, Long>();
		page.getContent().forEach(e -> {
			long count = recruimentNewsCandidatesService.countByLike(e.getRecruimentNews().getId());
			recruimentDetails.put(e, count);
		});

		model.addAttribute("recruimentDetail", recruimentDetails);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("pageNo", pageNo_);
		model.addAttribute("keyword", keyword);
		return "components/search/search-recruiment";
	}
	
}
