package com.fpt.intern.bestcv.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fpt.intern.bestcv.model.IndustryCommand;
import com.fpt.intern.bestcv.model.JobCommand;
import com.fpt.intern.bestcv.service.IndustryService;

@Controller
public class IndustryJobController {
	
	@Autowired
	private IndustryService industryService;
	
	// trang quản lý ngành nghề của utd,utv,viewer 
	
	@GetMapping("/industryJobview-viewer")
	public String getIndustryPage() {	
		return "industryJobview-viewer";
	}
	// trang quản lý ngành nghề của admin
	//@PreAuthorize("ROLE_ADMIN")
	@GetMapping("/industryJobview")
	public String getIndustryPage(Model model) {
		model.addAttribute("industry", new IndustryCommand());
		model.addAttribute("job", new JobCommand());
		model.addAttribute("listIndustry" ,  industryService.getListIn());
		return "industryJobview";
	}
	//@PreAuthorize("ROLE_ADMIN")
	@GetMapping("/addIndustryJob")
	public String getAddIndusttrJobView(Model model) {
		model.addAttribute("industry", new IndustryCommand());
		model.addAttribute("job", new JobCommand());
		model.addAttribute("listIndustry", industryService.getListIn());
		return "addIndustryJob";
	}
	
	
}
