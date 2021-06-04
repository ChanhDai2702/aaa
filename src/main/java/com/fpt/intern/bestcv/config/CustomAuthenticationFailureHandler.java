package com.fpt.intern.bestcv.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.service.AspNetUsersService;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler{
	static String LOGIN_ERROR = "Đăng nhập thất bại vui lòng thử lại!";
	static String LOCKED_ACCOUNT = "Tài khoản đã bị khóa!";
	static String FORM_CAPTCHA = "/captcha";
	
	@Autowired
	private AspNetUsersService aspNetUsersService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		AspNetUsers user = aspNetUsersService.getAspNetUsersByEmail(request.getParameter("email"));
		String redirectURL = "/login?error";
		exception = new SessionAuthenticationException(LOGIN_ERROR);
		if(user != null) {
			System.out.println(new BCryptPasswordEncoder().matches(request.getParameter("passwordHash"),user.getPasswordHash()));
			if(user.isLockoutEnabled() && new BCryptPasswordEncoder().matches(request.getParameter("passwordHash"),user.getPasswordHash())) {
				exception = new LockedException(LOCKED_ACCOUNT);
			}else {
				user.setAccessFailedCount(user.getAccessFailedCount()+1);
				aspNetUsersService.updateAspNetUsers(user);
				if(user.getAccessFailedCount() >= 3) {
					redirectURL = FORM_CAPTCHA;
				}
			}
		}
		super.setDefaultFailureUrl(redirectURL);
		super.onAuthenticationFailure(request, response, exception);
	}

}
