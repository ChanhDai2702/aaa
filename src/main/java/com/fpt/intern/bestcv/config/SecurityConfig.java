package com.fpt.intern.bestcv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fpt.intern.bestcv.controller.CVController;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	static String AUTH_ADMIN = "ROLE_ADMIN";
	static String AUTH_SUB_ADMIN = "ROLE_SUB_ADMIN";
	static String AUTH_UTV = "ROLE_UTV";
	static String AUTH_NTD = "ROLE_NTD";
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).
		passwordEncoder(passwordEncoder());

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/resources/**", "/static/**","/vendor/**","/fonts/**", "/css/**", "/js/**", "/images/**", "/icon/**","/error");
	}


	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(900);
		http
		.authorizeRequests()
		.antMatchers("/**").permitAll()
//		.antMatchers("/","/login","/autologin","/login-google","/statisticalall","/login-facebook","/login-google-utv","/login-facebook-utv","/login-google-ntd","/login-facebook-ntd","/forgot_password","/search/**","/captcha","/registerUTV","/registerNTD","/registerUTVBT3","/reset_password","/registerNTDBT3","/confirm-account").permitAll()
//			.antMatchers("/edit_admin").hasAuthority(AUTH_ADMIN)
//			.antMatchers("/dashboard").hasAuthority(AUTH_ADMIN)
//			.antMatchers("/statisticalaccount").hasAuthority(AUTH_ADMIN)
//			.antMatchers("/statistical").hasAuthority(AUTH_ADMIN)
//			.antMatchers("/CandidateAccount").hasAuthority(AUTH_ADMIN)
//			.antMatchers("/RecruiterAccount").hasAuthority(AUTH_ADMIN)
//			.antMatchers("/edit_NTD","/edit_NTDBT3").hasAnyAuthority(AUTH_NTD)
//			.antMatchers("/edit_UTV","/edit_UTVBT3").hasAnyAuthority(AUTH_UTV)
//			.antMatchers("/upload_capnhat").hasAnyAuthority(AUTH_NTD,AUTH_ADMIN,AUTH_SUB_ADMIN,AUTH_UTV)
//			.antMatchers("/otppage").hasAnyAuthority(AUTH_NTD,AUTH_ADMIN,AUTH_SUB_ADMIN,AUTH_UTV)
//			.antMatchers("/cv/listCV").hasAnyAuthority(AUTH_UTV,AUTH_ADMIN,AUTH_SUB_ADMIN,AUTH_NTD)
//			.antMatchers("/cv/searchCV").hasAnyAuthority(AUTH_UTV,AUTH_ADMIN,AUTH_SUB_ADMIN,AUTH_NTD)
//			.antMatchers("/cv/createCV").hasAnyAuthority(AUTH_UTV)
//			.antMatchers("/cv/checkCV/**").hasAnyAuthority(AUTH_UTV,AUTH_ADMIN,AUTH_SUB_ADMIN,AUTH_NTD)
//			.antMatchers("/cv/saveCV").hasAnyAuthority(AUTH_UTV)
//			.antMatchers("/cv/editCV/**").hasAnyAuthority(AUTH_UTV)
//			.antMatchers("/cv/saveEditCV/**").hasAnyAuthority(AUTH_UTV)
//			.anyRequest().authenticated()
			.and()
		.formLogin()
		.loginProcessingUrl("/j_spring_security_login")
		.loginPage("https://localhost:8080/login")
		.successHandler(authenticationSuccessHandler())
		.failureHandler(authenticationFailureHandler())
		.usernameParameter("email")
		.passwordParameter("passwordHash")
		.permitAll()
		.and()
		.logout()
		.logoutUrl("/j_spring_security_logout")
		.logoutSuccessUrl("/login").invalidateHttpSession(true)
		.deleteCookies("remember-me","JSESSIONID")
		.permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/401")
		;
	}
}
