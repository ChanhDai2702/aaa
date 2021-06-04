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
import com.fpt.intern.bestcv.model.IndustryCommand;
import com.fpt.intern.bestcv.service.IndustryService;

@Controller
public class IndustryController {
	@Autowired
	private IndustryService industryService;
	@GetMapping("delete-industry/{id}")
	String deleteIndusTry(@PathVariable("id") int id, RedirectAttributes model ) {
		if (industryService.deleteIndustry(id)==true) {
			model.addFlashAttribute("message", "Xóa ngành thành công");
		}else {
			model.addFlashAttribute("error", "Xóa thất bại, ngành đang có trong dữ liệu khác");
		}	
		return "redirect:/industryJobview";	
	}
	
	@PostMapping("update-industry")
	String updateIndustry(@Validated @ModelAttribute("industry") IndustryCommand industryCommand, BindingResult rs, RedirectAttributes model ) {
		if (rs.hasErrors()) {
			model.addFlashAttribute("error", "Tên ngành chỉ chứa kí tự và số");
			return "redirect:/industryJobview";
		}
		Industry industry = new Industry();
		industry.setId(industryCommand.getId());
		industry.setIndustryName(industryCommand.getIndustryName());;
		if (industryService.saveIns(industry)==false) {
			model.addFlashAttribute("error", "Cập nhập không thành công, tên ngành đã tồn tại");
			
			return "redirect:/industryJobview";
		}else {
			model.addFlashAttribute("message", "Cập nhập thành công");
			return "redirect:/industryJobview";
		}		
	}

	@PostMapping("addIndustry")
	public String addIndustry(@Validated @ModelAttribute("industry") IndustryCommand x,BindingResult rs, RedirectAttributes model) {
		if (rs.hasErrors()) {
			model.addFlashAttribute("error", "Tên ngành chỉ chứa kí tự và số");
			return "redirect:/addIndustryJob";
		}
		Industry industry = new Industry();
		industry.setIndustryName(x.getIndustryName());
		if (industryService.saveIns(industry)) {
			model.addFlashAttribute("message", " Thêm ngành thành công");
			return "redirect:/industryJobview";
		}else {
			model.addFlashAttribute("error", " Thêm thật bại, trùng tên ngành");
			return "redirect:/addIndustryJob";
		}
	}
}
