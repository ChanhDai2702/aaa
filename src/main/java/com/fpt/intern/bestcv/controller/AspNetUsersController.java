package com.fpt.intern.bestcv.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.service.AspNetUsersService;


@Controller
public class AspNetUsersController {
	@Autowired
	AspNetUsersService aspnet;
	
	
	
	
//	@PostMapping(value = "save")
//	public String saveProduct(AspNetUsers user) {
//		Date d = new Date(2021, 03, 27);
//		//AspNetUsers asp = new AspNetUsers("1", "daiviet1023@gmail.com", false, "123", "AAA", "0123456789", false, false, d, false, 0, "abc", d);
//		
//		//System.out.println(user);	
//		//aspnet.addAspNetUsers(asp);
//			return "redirect:/home";
//		}
	
}
