package com.fpt.intern.bestcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AspNetUserClaims")
public class AspNetUserClaims {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn
	private AspNetUsers user;
	@Column(columnDefinition = "TEXT")
	private String claimType;
	@Column(columnDefinition = "TEXT")
	private String claimValue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AspNetUsers getUser() {
		return user;
	}
	public void setUser(AspNetUsers user) {
		this.user = user;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public String getClaimValue() {
		return claimValue;
	}
	public void setClaimValue(String claimValue) {
		this.claimValue = claimValue;
	}
	public AspNetUserClaims(AspNetUsers user, String claimType, String claimValue) {
		super();
		this.user = user;
		this.claimType = claimType;
		this.claimValue = claimValue;
	}
	@Override
	public String toString() {
		return "AspNetUserClaims [id=" + id + ", user=" + user + ", claimType=" + claimType + ", claimValue="
				+ claimValue + "]";
	}
	
}
