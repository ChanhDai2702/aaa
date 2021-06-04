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
@Table(name = "Admin")
public class Admin {
	@Id
	@Column(name = "AdminId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "AdminName", length = 60, nullable = false)
	private String adminName;
	@Column(name = "Address", length = 160, nullable = false)
	private String address;
	@Column(name = "Image", length = 255)
	private String image;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private AspNetUsers users;
	
	public Admin(String adminName, String address, String image) {
		this.adminName = adminName;
		this.address = address;
		this.image = image;
	}
	public Admin() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
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
	@Override
	public String toString() {
		return "Admin [id=" + id + ", adminName=" + adminName + ", address=" + address + ", image=" + image
				+ ", aspNetUsers=" + users + "]";
	}
}
