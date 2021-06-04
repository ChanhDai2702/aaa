package com.fpt.intern.bestcv.controller;

import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.fpt.intern.bestcv.reposistory.AspNetRolesReposistory;
import com.fpt.intern.bestcv.reposistory.AspNetUsersReposistory;
import com.fpt.intern.bestcv.reposistory.CandidateReposistory;
import com.fpt.intern.bestcv.reposistory.VipPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.fpt.intern.bestcv.entity.Admin;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.entity.PriorityAddress;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.service.AdminService;
import com.fpt.intern.bestcv.service.AspNetRolesService;
import com.fpt.intern.bestcv.service.AspNetUserLoginsServcie;
import com.fpt.intern.bestcv.service.AspNetUsersService;

import com.fpt.intern.bestcv.service.CandidateService;

import com.fpt.intern.bestcv.service.FilesStorageService;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.PriorityAddressService;

import com.fpt.intern.bestcv.service.RecruiterService;




@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	static String FILE_DISPLAY_PATTERN =  "([^\\s]+(\\.(?i)(PNG|js|css|png|jpg))$)";
	static String FORM_EDIT_ADMIN = "components/editInformation/edit_admin";
	static String FORM_GENERATEOTP = "generateOtp";
	@Autowired
	AspNetUsersService aspnet;

	@Autowired
	AspNetUsersReposistory aspNetUsersReposistory;
	
	@Autowired
	FilesStorageService storageService;
	
	@Autowired
	AspNetRolesReposistory aspNetRolesReposistory;
	

	@RequestMapping(value = "edit_admin")
	public String EditAdmin(Model model,Principal principal) {
		if(principal!=null) {
			AspNetUsers aspNetUsers = aspnet.getAspNetUsersByEmail(principal.getName().toString());
			Admin a = adminService.getUserId(aspNetUsers.getId());
			if(a.getImage()==null) {
				a.setImage("emoji.png");
			}
			model.addAttribute("ADMIN", a);
			model.addAttribute("success");
			return FORM_EDIT_ADMIN;
		}
		return "redirect:/";
	}

	@PostMapping(value = "save_admin")
	public String editadmin(Admin admin,@RequestParam("files") MultipartFile fileImages,RedirectAttributes redirAttrs) {
		AspNetUsers aspNetUsers = aspnet.getAspNetUsersByID(admin.getAspNetUsers().getId());
		aspNetUsers.setPhoneNumber(admin.getAspNetUsers().getPhoneNumber());
		if(fileImages.getSize()!=0) {
			admin.setImage(storageService.saveWithCustomName(fileImages,UUID.randomUUID().toString()));
		}
		admin.setAspNetUsers(aspNetUsers);
		redirAttrs.addFlashAttribute("admin_data", admin);
		return "redirect:/"+FORM_GENERATEOTP;
	}


	@Autowired
	VipPackageRepository vipPakageRepository;


	@GetMapping(value="/statistical")
	public String statistical(Model model, @Param("daystart") String daystart, @Param("dayend") String dayend) {

		if(daystart==null && dayend==null){

			Map<String, Integer> ds = new LinkedHashMap<>();
			model.addAttribute("listchart", ds);

			return "/statistical";
		}else if (daystart.length() == 10 && dayend.length() == 10) {

			LocalDate start = LocalDate.parse(daystart);
			LocalDate end = LocalDate.parse(dayend);
			List<LocalDate> totalDates = new ArrayList<>();
			while (!start.isAfter(end)) {
				totalDates.add(start);
				start = start.plusDays(1);
			}

			Map<String, Double> ds = new LinkedHashMap<>();
			SimpleDateFormat sf= new SimpleDateFormat("dd/MM/yyyy");

			for (int i = 0; i < totalDates.size(); i++) {
				LocalDate date=totalDates.get(i);

				try{
					ds.put(String.valueOf(date.getDayOfWeek()+","+sf.format(Date.valueOf(totalDates.get(i)))), vipPakageRepository.statisticalByDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear()));
				}catch (Exception e){
					ds.put(String.valueOf(date.getDayOfWeek()+","+sf.format(Date.valueOf(totalDates.get(i)))), 0.0);
				};
			}

			model.addAttribute("daystart",daystart);
			model.addAttribute("dayend",dayend);

			model.addAttribute("listchart", ds);
			return "/statistical";

		} else if (daystart.length() == 7 && dayend.length() == 7) {
			String[] partstart = daystart.split("-");
			String monthstart = partstart[1]; // 004
			String yearstart = partstart[0];
			LocalDate start = LocalDate.of(Integer.valueOf(yearstart),Integer.valueOf(monthstart),1);

			String[] partsend = dayend.split("-");
			String monthend = partsend[1]; // 004
			String yearsend = partsend[0];
			LocalDate end = LocalDate.of(Integer.valueOf(yearsend),Integer.valueOf(monthend),1);
			List<LocalDate> totalsMonth = new ArrayList<>();

			while (!start.isAfter(end)) {
				totalsMonth.add(start);
				start = start.plusMonths(1);
			}
			Map<String, Double> ds = new LinkedHashMap<>();
			SimpleDateFormat sf= new SimpleDateFormat("MM/yyyy");

			for (int i = 0; i < totalsMonth.size(); i++) {
				LocalDate date=totalsMonth.get(i);
				try{
					double x = vipPakageRepository.statisticalByMonth(date.getMonthValue(),date.getYear());
					ds.put(sf.format(Date.valueOf(totalsMonth.get(i))), vipPakageRepository.statisticalByMonth(date.getMonthValue(), date.getYear()));

				}catch (Exception e){
					ds.put(sf.format(Date.valueOf(totalsMonth.get(i))), 0.0);
				};
			}

			model.addAttribute("daystart",daystart);
			model.addAttribute("dayend",dayend);
			model.addAttribute("listchart", ds);
			return "/statistical";
		} else{

			int yearstart=Integer.valueOf(daystart);
			int yearend=Integer.valueOf(dayend);
			Map<String, Double> ds = new LinkedHashMap<>();
			for (int i = yearstart; i <=yearend ; i++) {
				try{
					double x = vipPakageRepository.statisticalByYear(i);
					ds.put(String.valueOf(i), vipPakageRepository.statisticalByYear(i));
				}catch (Exception e){
					ds.put(String.valueOf(i), 0.0);
				};
			}
			model.addAttribute("daystart",daystart);
			model.addAttribute("dayend",dayend);
			model.addAttribute("listchart", ds);
			return "/statistical";
		}
	}

	@GetMapping(value="/statisticalaccount")
	public String statisticalaccount(Model model) {
		LocalDate now=LocalDate.now();
		Map<String, Integer> ds = new LinkedHashMap<>();
		Map<String, Integer> ds1 = new LinkedHashMap<>();
	
		//truyen du lieu query thang/nam voi role cua UTV
		ds.put(String.valueOf(now.minusMonths(2).getMonthValue()+"-"+now.minusMonths(2).getYear()),aspNetRolesReposistory.countaccountrole("ROLE_UTV",now.minusMonths(2).getMonthValue(), now.minusMonths(2).getYear()));
		ds.put(String.valueOf(now.minusMonths(1).getMonthValue()+"-"+now.minusMonths(1).getYear()),aspNetRolesReposistory.countaccountrole("ROLE_UTV",now.minusMonths(1).getMonthValue(), now.minusMonths(1).getYear()));
		ds.put(String.valueOf(now.getMonthValue()+"-"+now.getYear()),aspNetRolesReposistory.countaccountrole("ROLE_UTV",now.getMonthValue(), now.getYear()));
		
		//truyen du lieu query thang/nam voi role cua NTD
		ds1.put(String.valueOf(now.minusMonths(2).getMonthValue()+"-"+now.minusMonths(2).getYear()),aspNetRolesReposistory.countaccountrole("ROLE_NTD",now.minusMonths(2).getMonthValue(), now.minusMonths(2).getYear()));
		ds1.put(String.valueOf(now.minusMonths(1).getMonthValue()+"-"+now.minusMonths(1).getYear()),aspNetRolesReposistory.countaccountrole("ROLE_NTD",now.minusMonths(1).getMonthValue(), now.minusMonths(1).getYear()));
		ds1.put(String.valueOf(now.getMonthValue()+"-"+now.getYear()),aspNetRolesReposistory.countaccountrole("ROLE_NTD",now.getMonthValue(), now.getYear()));


		model.addAttribute("listchart", ds);
		model.addAttribute("listchart1", ds1);
		return "statisticalaccount";
	}
	@GetMapping(value="/statisticalall")
	public String statisticalall(Model model, @Param("daystart") String daystart, @Param("dayend") String dayend) {
		if(daystart==null && dayend==null){
			LocalDate ngaybatdau=LocalDate.of(2021,3,30);
			LocalDate ngayketthuc=LocalDate.now();


			String[] partstart = String.valueOf(ngaybatdau).split("-");
			String monthstart = partstart[1]; // 004
			String yearstart = partstart[0];
			LocalDate start = LocalDate.of(Integer.valueOf(yearstart),Integer.valueOf(monthstart),1);

			String[] partsend = String.valueOf(ngayketthuc).split("-");
			String monthend = partsend[1]; // 004
			String yearsend = partsend[0];
			LocalDate end = LocalDate.of(Integer.valueOf(yearsend),Integer.valueOf(monthend),1);
			List<LocalDate> totalsMonth = new ArrayList<>();

			while (!start.isAfter(end)) {
				totalsMonth.add(start);
				start = start.plusMonths(1);
			}
			Map<String, Double> ds = new LinkedHashMap<>();
			SimpleDateFormat sf= new SimpleDateFormat("MM/yyyy");



			for (int i = 0; i < totalsMonth.size(); i++) {
				LocalDate date=totalsMonth.get(i);
				try{
					double x = vipPakageRepository.statisticalByMonth(date.getMonthValue(),date.getYear());
					ds.put(sf.format(Date.valueOf(totalsMonth.get(i))), vipPakageRepository.statisticalByMonth(date.getMonthValue(), date.getYear()));

				}catch (Exception e){
					ds.put(sf.format(Date.valueOf(totalsMonth.get(i))), 0.0);
				};
			}

			model.addAttribute("daystart",ngaybatdau);
			model.addAttribute("dayend",ngayketthuc);
			model.addAttribute("listchart", ds);



			return "/statisticalall";
		}else if (daystart.length() == 10 && dayend.length() == 10) {

			LocalDate start = LocalDate.parse(daystart);
			LocalDate end = LocalDate.parse(dayend);
			List<LocalDate> totalDates = new ArrayList<>();
			while (!start.isAfter(end)) {
				totalDates.add(start);
				start = start.plusDays(1);
			}

			Map<String, Double> ds = new LinkedHashMap<>();
			SimpleDateFormat sf= new SimpleDateFormat("dd/MM/yyyy");

			for (int i = 0; i < totalDates.size(); i++) {
				LocalDate date=totalDates.get(i);

				try{

					ds.put(String.valueOf(date.getDayOfWeek()+","+sf.format(Date.valueOf(totalDates.get(i)))), vipPakageRepository.statisticalByDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear()));

				}catch (Exception e){
					ds.put(String.valueOf(date.getDayOfWeek()+","+sf.format(Date.valueOf(totalDates.get(i)))), 0.0);

				};

			}

			model.addAttribute("daystart",daystart);
			model.addAttribute("dayend",dayend);

			model.addAttribute("listchart", ds);
			return "/statistical";

		} else if (daystart.length() == 7 && dayend.length() == 7) {
			String[] partstart = daystart.split("-");
			String monthstart = partstart[1]; // 004
			String yearstart = partstart[0];
			LocalDate start = LocalDate.of(Integer.valueOf(yearstart),Integer.valueOf(monthstart),1);

			String[] partsend = dayend.split("-");
			String monthend = partsend[1]; // 004
			String yearsend = partsend[0];
			LocalDate end = LocalDate.of(Integer.valueOf(yearsend),Integer.valueOf(monthend),1);
			List<LocalDate> totalsMonth = new ArrayList<>();

			while (!start.isAfter(end)) {
				totalsMonth.add(start);
				start = start.plusMonths(1);
			}
			Map<String, Double> ds = new LinkedHashMap<>();
			SimpleDateFormat sf= new SimpleDateFormat("MM/yyyy");



			for (int i = 0; i < totalsMonth.size(); i++) {
				LocalDate date=totalsMonth.get(i);
				try{
					double x = vipPakageRepository.statisticalByMonth(date.getMonthValue(),date.getYear());
					ds.put(sf.format(Date.valueOf(totalsMonth.get(i))), vipPakageRepository.statisticalByMonth(date.getMonthValue(), date.getYear()));

				}catch (Exception e){
					ds.put(sf.format(Date.valueOf(totalsMonth.get(i))), 0.0);
				};
			}

			model.addAttribute("daystart",daystart);
			model.addAttribute("dayend",dayend);
			model.addAttribute("listchart", ds);
			return "/statistical";
		} else{

			int yearstart=Integer.valueOf(daystart);
			int yearend=Integer.valueOf(dayend);
			Map<String, Double> ds = new LinkedHashMap<>();
			for (int i = yearstart; i <=yearend ; i++) {
				try{
					double x = vipPakageRepository.statisticalByYear(i);
					ds.put(String.valueOf(i), vipPakageRepository.statisticalByYear(i));
				}catch (Exception e){
					ds.put(String.valueOf(i), 0.0);
				};

			}
			model.addAttribute("daystart",daystart);
			model.addAttribute("dayend",dayend);
			model.addAttribute("listchart", ds);
			return "/statistical";
		}

	}

}