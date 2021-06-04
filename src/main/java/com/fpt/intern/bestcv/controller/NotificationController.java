package com.fpt.intern.bestcv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.entity.Admin;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Notification;
import com.fpt.intern.bestcv.entity.RecruimentNews;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.model.LoggedUser;
import com.fpt.intern.bestcv.service.FilesStorageService;
import com.fpt.intern.bestcv.service.NotificationService;
import com.fpt.intern.bestcv.utility.PageURLUtility;

@Controller
@RequestMapping("/notify")
public class NotificationController {

	@Autowired
	private NotificationService notifiService;
	@Autowired
	FilesStorageService storageService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin")
	public String notifiADMIN(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1")String strPageNum,RedirectAttributes ra,
			Authentication authentication) {
		try {
			LoggedUser users = (LoggedUser) authentication.getPrincipal();
			Admin asp = (Admin) users.getInfo();
			System.out.println("---------------------------------dasdasds-------------------------------"+users.getInfo());
			int pageNum = Integer.parseInt(strPageNum);
			if (pageNum <= 0)
				pageNum = 1;
			
			
			Page<Notification> page = notifiService.listNotifyByUserID(asp.getAspNetUsers().getId(),pageNum, 10);
			
			Page<Notification> pageNotifi = notifiService.listFiveNotifyByUserID(asp.getAspNetUsers().getId(),pageNum, 5);
			
			if (page.getTotalPages() != 0 && pageNum > page.getTotalPages()) {
				System.out.println("adasdasdsa"+page.getTotalPages());
				
				return PageURLUtility.PAGE_404;
			}
			else
			{
				List<Notification> listNotifis = page.getContent();
				List<Notification> list5notifis= pageNotifi.getContent();
				
				model.addAttribute("currentPage", pageNum);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("totalItems", page.getTotalElements());
				model.addAttribute("notifis", listNotifis);
				
				model.addAttribute("currentPage1", pageNum);
				model.addAttribute("totalPages1", page.getTotalPages());
				model.addAttribute("totalItems1", page.getTotalElements());
				model.addAttribute("notifis1", list5notifis);
				model.addAttribute("counter", new Counter());
				return PageURLUtility.NOTIFICATION_ADMIN;
			}
			
		}
		catch (NumberFormatException e) {
			ra.addFlashAttribute("error", "Please provide a valid page number");
			return "redirect:/notify/admin";
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return PageURLUtility.PAGE_500;
		}	
	}
	
	@RequestMapping(value = "/ntd")
	@PreAuthorize("hasRole('ROLE_NTD')")
	public String notifiNTD(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1")String strPageNum,RedirectAttributes ra,
			Authentication authentication) {
		
		try {
			int pageNum = Integer.parseInt(strPageNum);
			if (pageNum <= 0)
				pageNum = 1;
			LoggedUser users = (LoggedUser) authentication.getPrincipal();
			Recruiter asp = (Recruiter) users.getInfo();
			Page<Notification> page = notifiService.listFiveNotifyByUserID(asp.getAspNetUsers().getId(),pageNum, 5);
			
			if (page.getTotalPages() != 0 && pageNum > page.getTotalPages()) {
				System.out.println("adasdasdsa"+page.getTotalPages());
				return PageURLUtility.PAGE_404;
			}
			else
			{
				List<Notification> listNotifis = page.getContent();
				
				model.addAttribute("currentPage", pageNum);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("totalItems", page.getTotalElements());
				model.addAttribute("notifis", listNotifis);
			
				return "index_NTD";
			}
			
		}
		catch (NumberFormatException e) {
			ra.addFlashAttribute("error", "Please provide a valid page number");
			return "redirect:/notify/ntd";
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return PageURLUtility.PAGE_500;
		}
		
	}
	
	@RequestMapping("/files/{fileName}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable(name = "fileName") String filename) {
		try {
			Resource file = storageService.load(filename);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value = "/candidate")
	public String notifiCandate() {
		return PageURLUtility.NOTIFICATION_CANDIDATE;
	}
	@RequestMapping(value = "/recruiter")
	public String notifiRecruiter() {
		return PageURLUtility.NOTIFICATION_RECRUITER;
	}
}
// hien thi so thong bao
class Counter
{
    private int count;

    public Counter()
    {
        count = 0;
    }

    public Counter(int init)
    {
        count = init;
    }

    public int get()
    {
        return count;
    }

    public void clear()
    {
        count = 0;
    }

    public int incrementAndGet()
    {
        return ++count;
    }

    public String toString()
    {
        return ""+count;
    }
}
