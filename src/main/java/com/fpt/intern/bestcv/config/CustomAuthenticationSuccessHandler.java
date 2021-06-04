package com.fpt.intern.bestcv.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.service.AspNetUsersService;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler 
										implements AuthenticationSuccessHandler{
	static String RETURN_HOME = "/";
	static String RETURN_DASHBOARD="/dashboard";
	static String AUTH_ADMIN = "ROLE_ADMIN";
	@Autowired
	private AspNetUsersService aspNetUsersService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		AspNetUsers user = aspNetUsersService.getAspNetUsersByEmail(request.getParameter("email"));
        String redirectURL = RETURN_HOME;
        if(user != null) {
        	user.setAccessFailedCount(0);
    		aspNetUsersService.updateAspNetUsers(user);
        }
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(AUTH_ADMIN))) {
        	redirectURL = RETURN_DASHBOARD;
        }
		super.setDefaultTargetUrl(redirectURL);
        super.onAuthenticationSuccess(request, response, authentication);
	}

}
