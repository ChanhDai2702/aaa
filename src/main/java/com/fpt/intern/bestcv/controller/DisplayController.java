package com.fpt.intern.bestcv.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpt.intern.bestcv.entity.Display;
import com.fpt.intern.bestcv.service.DisplayService;
import com.fpt.intern.bestcv.service.FilesStorageService;

import javassist.NotFoundException;


@Controller
@RequestMapping(value = "/display")
public class DisplayController implements Filter {
	static String FORM_DISPLAY_LIST = "components/displays/listDisplay";
	static String FORM_DISPLAY_ADD = "components/displays/addDisplay";
	static String FORM_DISPLAY_EDIT = "components/displays/editDisplay";
	static String FORM_DISPLAY_CHOOSE = "components/displays/listDisplayChoose";

	static String FILE_DISPLAY_PATTERN =  "([^\\s]+(\\.(?i)(PNG|js|css|png|jpg))$)";

	private static int idDisplay = -1;

	private static Display defaultDisplay = new 
			Display("default-image.png",
					15,
					"Times New Roman", 
					"Default",
					"default-image.png", 
					"black", 
					"black",
					"black",
					true);

	static List<String> listFontSize = Arrays.asList(
			"Times New Roman",
			"Arial",
			"Fantasy",
			"cursive");

	@Autowired
	private DisplayService displayService;

	@Autowired
	FilesStorageService storageService;

	@GetMapping
	public String listDisplayChoose(Model model,
			HttpServletRequest request,
			RedirectAttributes redirect) {
		model.addAttribute("listDisplay", displayService.listDisplay());
		return FORM_DISPLAY_CHOOSE;
	}

	@GetMapping(value = "list")
	public String listDislay(Model model,
			HttpServletRequest request,
			RedirectAttributes redirect) {
		model.addAttribute("listDisplay", displayService.listDisplay());
		return FORM_DISPLAY_LIST;
	}

	@RequestMapping(value = "add")  
	public String addDisplay(Model model) {  
		model.addAttribute("display", new Display());  
		model.addAttribute("fontTypes", listFontSize);
		return FORM_DISPLAY_ADD;  
	}

	@PostMapping(value = "save")
	public String saveDisplay(Display display,
			@RequestParam("fileBanner") MultipartFile fileBanner,
			RedirectAttributes redirAttrs,
			@RequestParam("fileReview") MultipartFile fileReview) {
		display.setImageBanner(storageService.saveWithCustomName(fileBanner, UUID.randomUUID().toString()));
		display.setImageReview(storageService.saveWithCustomName(fileReview, UUID.randomUUID().toString()));
		displayService.addDisplay(display);
		return "redirect:/display/list";
	}

	@PostMapping(value = "edit")
	public String processEditDisplay(Display display,
			@RequestParam("fileBanner") MultipartFile fileBanner,
			RedirectAttributes redirAttrs,
			@RequestParam("fileReview") MultipartFile fileReview) {
		if(fileBanner.getSize()!=0) {
			display.setImageBanner(storageService.saveWithCustomName(fileBanner, UUID.randomUUID().toString()));
		}
		if(fileReview.getSize()!=0) {
			display.setImageReview(storageService.saveWithCustomName(fileReview, UUID.randomUUID().toString()));
		}
		displayService.updateDisplay(display);
		return "redirect:/display/list";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteDisplayById(@PathVariable(name = "id") String id) {
		try {
			Display displayFind = displayService.findDisplayById(Integer.parseInt(id)); 
			if(displayFind == null) {
				return HandleErrorController.PAGE_404;
			}
			if(displayFind != null) {
				if(displayFind.getIsActive() == true) {
					defaultDisplay.setIsActive(true);
				}
				displayService.deleteDisplay(Integer.parseInt(id));
			}
			return "redirect:/display/list";
		} catch (NumberFormatException e) {
			return HandleErrorController.PAGE_404;
		} catch (Exception e) {
			e.printStackTrace();
			return HandleErrorController.PAGE_500;
		}
	}

	@GetMapping(value = "edit/{id}")
	public String updateDisplay(@PathVariable(name = "id") String id,
			Model model) throws NotFoundException {
		try {
			Display display = displayService.findDisplayById(Integer.parseInt(id));
			if(display == null) {
				return HandleErrorController.PAGE_404;
			}
			model.addAttribute("display", display);
			model.addAttribute("fontTypes", listFontSize);
			return FORM_DISPLAY_EDIT;
		}catch (NumberFormatException e) {
			return HandleErrorController.PAGE_404;
		} catch (Exception e) {
			e.printStackTrace();
			return HandleErrorController.PAGE_500;
		}
	}

	@GetMapping(value = "choose/{id}")
	public String chooseDisplay(@PathVariable(name = "id") String id,
			Model model,
			HttpServletRequest request) {
		try {
			Integer getId = Integer.parseInt(id);
			displayService.changeFalseActiveDisplay();
			if(getId != idDisplay) {
				displayService.ChangeDisplay(Integer.parseInt(id));
				request.setAttribute("newDisplayActive","1");
			}
			return "redirect:/display";
		} catch (NumberFormatException e) {
			return HandleErrorController.PAGE_404;
		} catch (Exception e) {
			e.printStackTrace();
			return HandleErrorController.PAGE_500;
		}
	}

	@RequestMapping("/files/{fileName}")
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

	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response,
			FilterChain chain)
					throws IOException, ServletException {
		HttpServletRequest requestX = (HttpServletRequest) request;
		String getRequestURi = requestX.getRequestURL().toString();
		defaultDisplay.setId(idDisplay);
		System.out.println("request from filter display:"+getRequestURi);
		if(Pattern.matches(FILE_DISPLAY_PATTERN, getRequestURi)) {
			request.setAttribute("styleCommon", defaultDisplay);
		} else {
			Display displayFind = displayService.findDisplayWasChoose();
			request.setAttribute("styleCommon", displayFind != null ? displayFind : defaultDisplay);
		}
		chain.doFilter(request, response);
	}
}
