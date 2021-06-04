package com.fpt.intern.bestcv.aouth;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.intern.bestcv.entity.AspNetRoles;
import com.fpt.intern.bestcv.entity.AspNetUserLogins;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.service.AspNetRolesService;
import com.fpt.intern.bestcv.service.AspNetUserLoginsServcie;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

@Component
public class RestFB {
	@Autowired
	private Environment env;

	@Autowired
	private AspNetUserLoginsServcie aspNetUserLoginsServcie;
	
	@Autowired
	private AspNetRolesService aspNetRolesService;
	
	@Autowired
	private AspNetUsersService aspNetUsersService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
  public String getToken(final String code) throws ClientProtocolException, IOException {
    String link = String.format(env.getProperty("facebook.link.get.token"), env.getProperty("facebook.app.id"),
        env.getProperty("facebook.app.secret"), env.getProperty("facebook.redirect.uri"), code);
    String response = Request.Get(link).execute().returnContent().asString();
    
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(response).get("access_token");
    return node.textValue();
  }
  
  public com.restfb.types.User getUserInfo(final String accessToken) {
    FacebookClient facebookClient = new DefaultFacebookClient(accessToken, env.getProperty("facebook.app.secret"),
        Version.LATEST);
    return facebookClient.fetchObject("me", com.restfb.types.User.class);
  }
  
  public UserDetails buildUser(com.restfb.types.User user) {
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;
    
    AspNetUsers aspUser = new AspNetUsers();
	System.out.println(user.toString());
	aspUser.setId(UUID.randomUUID().toString());
	aspUser.setEmail(user.getEmail());
	aspUser.setUserName(user.getName());
	
	aspUser.setPasswordHash(passwordEncoder.encode("123456"));
	HashSet<AspNetRoles> roles = new HashSet<>();
	roles.add(aspNetRolesService.getRoleByName("ROLE_UTV"));
	aspUser.setRoles(roles);
	aspNetUsersService.updateAspNetUsers(aspUser);
	
	AspNetUserLogins userLogin = new AspNetUserLogins("FaceBook Provider",user.getId(),aspUser);
	aspNetUserLoginsServcie.CreateUserLogins(userLogin);
    
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority("ROLE_UTV"));
    UserDetails userDetail = new User(user.getId(),aspUser.getPasswordHash(), enabled, accountNonExpired, credentialsNonExpired,
        accountNonLocked, authorities);
    return userDetail;
  }
  public UserDetails getUser(AspNetUsers user) {
	    boolean enabled = true;
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRoles().stream().findFirst().get().getName().toString()));
		UserDetails userDetail = new User(user.getId(),
				user.getPasswordHash(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	    return userDetail;
	  }
	public String getTokenUTV(final String code) throws ClientProtocolException, IOException {
		String link = String.format(env.getProperty("facebook.link.get.tokenutv"), env.getProperty("facebook.app.idutv"),
				env.getProperty("facebook.app.secretutv"), env.getProperty("facebook.redirect.uriutv"), code);
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}
	public com.restfb.types.User getUserInfoUTV(final String accessToken) {
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, env.getProperty("facebook.app.secretutv"),
				Version.LATEST);
		return facebookClient.fetchObject("me", com.restfb.types.User.class);
	}
	public String getTokenNTD(final String code) throws ClientProtocolException, IOException {
		String link = String.format(env.getProperty("facebook.link.get.tokenntd"), env.getProperty("facebook.app.idntd"),
				env.getProperty("facebook.app.secretntd"), env.getProperty("facebook.redirect.urintd"), code);
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}
	public com.restfb.types.User getUserInfoNTD(final String accessToken) {
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, env.getProperty("facebook.app.secretntd"),
				Version.LATEST);
		return facebookClient.fetchObject("me", com.restfb.types.User.class);
	}
}
