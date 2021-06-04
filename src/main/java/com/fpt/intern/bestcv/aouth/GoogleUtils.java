package com.fpt.intern.bestcv.aouth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.model.GooglePojo;


@Component
public class GoogleUtils {
	@Autowired
	private Environment env;
	
	public String getToken(final String code) throws ClientProtocolException, IOException {
		String link = env.getProperty("google.link.get.token");
		String response = Request.Post(link)
				.bodyForm(Form.form().add("client_id", env.getProperty("google.app.id"))
						.add("client_secret", env.getProperty("google.app.secret"))
						.add("redirect_uri", env.getProperty("google.redirect.uri")).add("code", code)
						.add("grant_type", "authorization_code").build())
				.execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}
	public GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = env.getProperty("google.link.get.user_info") + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		System.out.println(response);
		ObjectMapper mapper = new ObjectMapper();
		GooglePojo googlePojo = mapper.readValue(response, GooglePojo.class);
		return googlePojo;
	}
	public UserDetails getAspNetUser(AspNetUsers user) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRoles().stream().findFirst().get().getName().toString()));
		UserDetails userDetail = new User(user.getEmail(),
				user.getPasswordHash(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return userDetail;
	}
	public String getTokenUTV(final String code) throws ClientProtocolException, IOException {
		String link = env.getProperty("google.link.get.tokenutv");
		String response = Request.Post(link)
				.bodyForm(Form.form().add("client_id", env.getProperty("google.app.idutv"))
						.add("client_secret", env.getProperty("google.app.secretutv"))
						.add("redirect_uri", env.getProperty("google.redirect.uriutv")).add("code", code)
						.add("grant_type", "authorization_code").build())
				.execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}
	public String getTokenNTD(final String code) throws ClientProtocolException, IOException {
		String link = env.getProperty("google.link.get.tokenntd");
		String response = Request.Post(link)
				.bodyForm(Form.form().add("client_id", env.getProperty("google.app.idntd"))
						.add("client_secret", env.getProperty("google.app.secretntd"))
						.add("redirect_uri", env.getProperty("google.redirect.urintd")).add("code", code)
						.add("grant_type", "authorization_code").build())
				.execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}
	public GooglePojo getUserInfoUCV(final String accessToken) throws ClientProtocolException, IOException {
		String link = env.getProperty("google.link.get.user_infoutv") + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		GooglePojo googlePojo = mapper.readValue(response, GooglePojo.class);
		return googlePojo;
	}
	public GooglePojo getUserInfoNTD(final String accessToken) throws ClientProtocolException, IOException {
		String link = env.getProperty("google.link.get.user_infontd") + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		GooglePojo googlePojo = mapper.readValue(response, GooglePojo.class);
		return googlePojo;
	}
}
