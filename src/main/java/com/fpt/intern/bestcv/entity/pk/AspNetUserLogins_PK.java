package com.fpt.intern.bestcv.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AspNetUserLogins_PK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String loginProvider;
	private String providerKey;
	private String user;

	public String getLoginProvider() {
		return loginProvider;
	}

	public void setLoginProvider(String loginProvider) {
		this.loginProvider = loginProvider;
	}

	public String getProviderKey() {
		return providerKey;
	}

	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public AspNetUserLogins_PK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AspNetUserLogins_PK(String loginProvider, String providerKey, String user) {
		super();
		this.loginProvider = loginProvider;
		this.providerKey = providerKey;
		this.user = user;
	}

	@Override
	public String toString() {
		return "AspNetUserLogins_PK [loginProvider=" + loginProvider + ", providerKey=" + providerKey + ", user=" + user
				+ "]";
	}
	
}