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
@Table(name = "Recruiter")
public class Recruiter {
	@Id
	@Column(name = "RecruiterId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "CompanyName", length = 60, nullable = false)
	private String companyName;
	@Column(name = "Address", length = 160, nullable = false)
	private String address;
	@Column(name = "Image", length = 255)
	private String image;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private AspNetUsers users;
	@ManyToOne
	@JoinColumn(name = "BusinessOrganizationId", nullable = false)
	private BusinessOrganization businessOrganization;
	@ManyToOne
	@JoinColumn(name = "PriorityAddressId", nullable = false)
	private PriorityAddress priorityAddress;
	@ManyToOne
	@JoinColumn(name = "IndustryId", nullable = false)
	private Industry industry;

	//
	public Recruiter(String companyName, String address, String image) {
		super();
		this.companyName = companyName;
		this.address = address;
		this.image = image;
	}
	
	public Recruiter() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AspNetUsers getUsers() {
		return users;
	}

	public void setUsers(AspNetUsers users) {
		this.users = users;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public AspNetUsers getAspNetUsers() {
		return users;
	}
	public void setAspNetUsers(AspNetUsers users) {
		this.users = users;
	}
	public BusinessOrganization getBusinessOrganization() {
		return businessOrganization;
	}
	public void setBusinessOrganization(BusinessOrganization businessOrganization) {
		this.businessOrganization = businessOrganization;
	}
	public PriorityAddress getPriorityAddress() {
		return priorityAddress;
	}
	public void setPriorityAddress(PriorityAddress priorityAddress) {
		this.priorityAddress = priorityAddress;
	}
	public Industry getIndustry() {
		return industry;
	}
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	@Override
	public String toString() {
		return "Recruiter [recruiterId=" + id + ", companyName=" + companyName + ", address=" + address
				+ ", image=" + image + "]";
	}
	
}
