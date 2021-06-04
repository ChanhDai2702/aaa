package com.fpt.intern.bestcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fpt.intern.bestcv.entity.pk.AspNetUserLogins_PK;

@Entity
@Table(name = "AspNetUserLogins")
@IdClass(AspNetUserLogins_PK.class)
public class AspNetUserLogins {
	@Id
	@Column(name = "LoginProvider", length = 128, nullable = false)
	private String loginProvider;
	@Id
	@Column(name = "ProviderKey", length = 128, nullable = false)
	private String providerKey;
	@Id
	@ManyToOne
	@JoinColumn(name = "UserId", nullable = false)
	private AspNetUsers user;
	public AspNetUserLogins(String loginProvider, String providerKey, AspNetUsers user) {
		super();
		this.loginProvider = loginProvider;
		this.providerKey = providerKey;
		this.user = user;
	}
	public AspNetUserLogins() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public AspNetUsers getUser() {
		return user;
	}
	public void setUser(AspNetUsers user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "AspNetUserLogins [loginProvider=" + loginProvider + ", providerKey=" + providerKey + ", user=" + user
				+ "]";
	}
	
}
