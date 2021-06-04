package com.fpt.intern.bestcv.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpt.intern.bestcv.aouth.GoogleUtils;
import com.fpt.intern.bestcv.aouth.RestFB;
import com.fpt.intern.bestcv.entity.AspNetUserLogins;

import com.fpt.intern.bestcv.model.GooglePojo;
import com.fpt.intern.bestcv.service.AspNetUserLoginsServcie;


@Controller
public class LoginController {

	static String FORM_LOGIN = "components/logins/loginPage";
	static String FORM_CAPTCHA = "components/logins/captcha";
	static String HEADER_NTD = "components/fregments/headers/headerDoanhNghiep";
	static String HEADER_UTV = "components/fregments/headers/headerUngTuyenVien";
	static String FORM_EDITUTV_BT3 = "edit_UTVBT3";
	static String FORM_EDITNTD_BT3 = "edit_NTDBT3";
	static String PAGE_401 = "401";
	static String AUTH_UTV = "ROLE_UTV";
	static String AUTH_NTD = "ROLE_NTD";
	
	@Autowired
	private GoogleUtils googleUtils;

	@Autowired
	private AspNetUserLoginsServcie aspNetUserLoginsServcie;

	@Autowired
	private RestFB restFb;
	
	@GetMapping(value = "/login")
    public String showFormLogin(Principal principal,Model model) {
		return principal!=null? "redirect:/" : FORM_LOGIN;
    }
	@GetMapping(value = "/captcha")
	public String showCaptchaPage() {
		return FORM_CAPTCHA;
	}
	@GetMapping(value = "/401")
	public String AccessDeniedPage() {
		return PAGE_401;
	}
	
	@RequestMapping("/login-google")
	public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException{
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			return "redirect:/login?google=error";
		}
		String accessToken = googleUtils.getToken(code);

		GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
		
//		AspNetUserLogins userLogin = aspNetUserLoginsServcie.getAspNetUserLoginsByKey(googlePojo.getId());
		AspNetUserLogins userLogin = aspNetUserLoginsServcie.getAspNetUserLoginsByProviderAndByKey("Google Provider",googlePojo.getId());
	
		UserDetails userDetail = null;
		if(userLogin==null) {
			return "redirect:/login?google=error";

		}
		userDetail = googleUtils.getAspNetUser(userLogin.getUser());
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(authentication.getAuthorities());
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(AUTH_UTV))) {
			return "redirect:/"+FORM_EDITUTV_BT3;
		}
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(AUTH_NTD))) {
			return "redirect:/"+FORM_EDITNTD_BT3;
		}
		return "redirect:/upload_capnhat";
	}

	@RequestMapping("/login-facebook")
	public String loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			return "redirect:/login?facebook=error";
		}
		String accessToken = restFb.getToken(code);
		com.restfb.types.User user = restFb.getUserInfo(accessToken);
//		AspNetUserLogins userLogin = aspNetUserLoginsServcie.getAspNetUserLoginsByKey(user.getId());
		AspNetUserLogins userLogin =  aspNetUserLoginsServcie.getAspNetUserLoginsByProviderAndByKey("FaceBook Provider",user.getId());
		UserDetails userDetail = null;
		if(userLogin==null) {
			return "redirect:/login?facebook=error";
		}
		userDetail = restFb.getUser(userLogin.getUser());
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(AUTH_UTV))) {
			return "redirect:/"+FORM_EDITUTV_BT3;
		}
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(AUTH_NTD))) {
			return "redirect:/"+FORM_EDITNTD_BT3;
		}
		return "redirect:/";
	}

}
