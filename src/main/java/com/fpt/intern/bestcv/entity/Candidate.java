package com.fpt.intern.bestcv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Candidate")
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CandidateId")
	private int id;
	@Column(name = "FullName", length = 80, nullable = false)
	private String fullName;
	@Column(name = "Address", length = 160, nullable = false)
	private String address;
	@Column(name = "Image", columnDefinition = "TEXT")
	private String image;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateOfBirth", columnDefinition = "DATETIME NOT NULL default '1900-01-01T00:00:00.000'")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	@Column(name = "Gender", length = 10, nullable = false)
	private String gender;
	//
	@ManyToOne
	@JoinColumn(name = "PriorityAddressId", nullable = false)
	private PriorityAddress priorityAddress;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private AspNetUsers users;
	@ManyToOne
	@JoinColumn(name = "IndustryId", nullable = false)
	private Industry industry;
	
	public Candidate(String fullName, String address, String image, Date dateOfBirth, String gender) {
		this.fullName = fullName;
		this.address = address;
		this.image = image;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}
	public Candidate() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public PriorityAddress getPriorityAddress() {
		return priorityAddress;
	}
	public void setPriorityAddress(PriorityAddress priorityAddress) {
		this.priorityAddress = priorityAddress;
	}
	
	public AspNetUsers getUsers() {
		return users;
	}
	public AspNetUsers getAspNetUsers() {
		return users;
	}
	public void setAspNetUsers(AspNetUsers users) {
		this.users = users;
	}
	public void setUsers(AspNetUsers users) {
		this.users = users;
	}
	public Industry getIndustry() {
		return industry;
	}
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", fullName=" + fullName + ", address=" + address + ", image=" + image
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + "]";
	}
	
	
}
