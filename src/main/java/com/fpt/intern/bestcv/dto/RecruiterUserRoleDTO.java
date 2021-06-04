package com.fpt.intern.bestcv.dto;

import java.util.Set;

import com.fpt.intern.bestcv.entity.AspNetRoles;
import com.fpt.intern.bestcv.entity.AspNetUsers;

import com.fpt.intern.bestcv.entity.Recruiter;

public class RecruiterUserRoleDTO {
	private AspNetUsers aspNetUsers;
	private Set<AspNetRoles> aspNetRoles;
	private Recruiter recruiter;
	public AspNetUsers getAspNetUsers() {
		return aspNetUsers;
	}
	public void setAspNetUsers(AspNetUsers aspNetUsers) {
		this.aspNetUsers = aspNetUsers;
	}
	public Set<AspNetRoles> getAspNetRoles() {
		return aspNetRoles;
	}
	public void setAspNetRoles(Set<AspNetRoles> aspNetRoles) {
		this.aspNetRoles = aspNetRoles;
	}
	public Recruiter getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}
	public RecruiterUserRoleDTO() {
		super();
		this.aspNetUsers = aspNetUsers;
		this.aspNetRoles = aspNetRoles;
		this.recruiter = recruiter;
	}
	@Override
	public String toString() {
		return "RecruiterUserRoleDTO [aspNetUsers=" + aspNetUsers + ", aspNetRoles=" + aspNetRoles + ", recruiter="
				+ recruiter + "]";
	}
	
	

}
