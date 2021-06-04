package com.fpt.intern.bestcv.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.intern.bestcv.entity.AspNetRoles;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.service.AspNetUsersService;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{
	static String NOT_FIND_USER = "không tìm thấy user";
	static String LOCKED_ACCOUNT = "Tài khoản đã bị khóa!";
	@Autowired
	private AspNetUsersService aspNetUsersService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException,ExceptionInInitializerError {
		AspNetUsers user = aspNetUsersService.getAspNetUsersByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException(NOT_FIND_USER);
		}
//		System.out.println(new BCryptPasswordEncoder().matches(param,user.getPasswordHash()));
		if(user.isLockoutEnabled() ) {
			throw new LockedException(LOCKED_ACCOUNT);
		}
		return new User(user.getEmail(), user.getPasswordHash(),getAuthorities(user));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(AspNetUsers user) {
//		String[] userRoles = {user.getRoles().stream().findFirst().get().getName().toString()} ;
//		
//		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	     
		for (AspNetRoles role : user.getRoles()) {
		    authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}
}
