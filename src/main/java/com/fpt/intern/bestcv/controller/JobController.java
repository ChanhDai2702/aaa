package com.fpt.intern.bestcv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.entity.Job;
import com.fpt.intern.bestcv.model.JobCommand;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.JobService;

@Controller
public class JobController {
	@Autowired
	private JobService jobService;
	@Autowired
	private IndustryService industryService;
	
	@GetMapping("/delete-job/{id}")
	public String deleteJob(@PathVariable("id") int jobId, RedirectAttributes model) {
		//System.err.println(jobId);
		 if (jobService.deleteJob(jobId)==true) {
			 model.addFlashAttribute("message", " Đã xóa công việc ");
		}else {
			model.addFlashAttribute("error", " Xóa thất bại,công việc đang có trong dữ liệu khác");
		}		
		return "redirect:/industryJobview";
	}
    
	@PostMapping("update-job")
	public String updateIndustry(@Validated @ModelAttribute("job") JobCommand jobCommand, BindingResult rs, RedirectAttributes model ) {
		if (rs.hasErrors()) {
			model.addFlashAttribute("erros", "Tên nghề phải chỉ chứa kí tự và số");
			return "redirect:/industryJobview";
		}
		Job job = new Job();
		Industry x =  new Industry();
		x = industryService.findIndustryById(jobCommand.getIndustry().getId());
		job.setId(jobCommand.getId());
		job.setJobName(jobCommand.getJobName());
		job.setIndustry(x);
		if (jobService.saveJob(job)) {
			model.addFlashAttribute("message", "Cập nhập thành công");
			return "redirect:/industryJobview";
		}else {
			model.addFlashAttribute("error", "Cập nhập không  thành công, tên nghề đã tồn tại");
			return "redirect:/industryJobview";
		}	
		
	}
	@PostMapping("addJob")
	public String addJob(@Validated @ModelAttribute("job") JobCommand x , BindingResult rs, RedirectAttributes model) {
		if (rs.hasErrors()) {
			model.addFlashAttribute("error", "Tên nghề phải chỉ chứa kí tự và số");
			return "redirect:/addIndustryJob";
		}		
		Job job = new Job();
		job.setJobName(x.getJobName());
		job.setIndustry(industryService.findIndustryById(x.getIndustry().getId()));
		if (jobService.saveJob(job)) {
			model.addFlashAttribute("message", "Thêm nghề  thành công");
			return "redirect:/industryJobview";
		}
		else {
			model.addFlashAttribute("error", "Thêm nghề thất bại, trùng tên nghề");
			return "redirect:/addIndustryJob";
		}
	}
}
