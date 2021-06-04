package com.fpt.intern.bestcv.dto;



import java.util.Set;

import com.fpt.intern.bestcv.entity.AspNetRoles;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;

public class CandidateUserRoleDTO {
	private int id;
	
	
	private AspNetUsers aspNetUsers;
	private Set<AspNetRoles> aspNetRoles;
	private Candidate candidate;
	
	public CandidateUserRoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CandidateUserRoleDTO(int id, AspNetUsers aspNetUsers, AspNetRoles aspNetRoles, Candidate candidate) {
		super();
		this.id = id;
		this.aspNetUsers = aspNetUsers;
		this.aspNetRoles = (Set<AspNetRoles>) aspNetRoles;
		this.candidate = candidate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public String toString() {
		return "CandidateUserRoleDTO [id=" + id + ", aspNetUsers=" + aspNetUsers + ", aspNetRoles=" + aspNetRoles
				+ ", candidate=" + candidate + "]";
	}

	

	
	
	

}
