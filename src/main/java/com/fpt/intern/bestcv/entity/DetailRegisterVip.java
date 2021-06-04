package com.fpt.intern.bestcv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fpt.intern.bestcv.entity.pk.DetailRegisterVip_PK;


@Entity
@Table(name = "DetailRegisterVip")
@IdClass(DetailRegisterVip_PK.class)
public class DetailRegisterVip {
	@Id
	@ManyToOne
	@JoinColumn(name = "RecruiterId", nullable = false)
	private Recruiter recruiter;
	@Id
	@ManyToOne
	@JoinColumn(name = "VipPackageId", nullable = false)
	private VipPackage  vipPackage;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RegisterDate", updatable = false, nullable = false)
	@Id
	private Date registerDate;
	
	@Column(name = "ExpirationDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationDate;
	
	public DetailRegisterVip(Recruiter recruiter, VipPackage vipPackage, Date registerDate, Date expirationDate) {
		super();
		this.recruiter = recruiter;
		this.vipPackage = vipPackage;
		this.registerDate = registerDate;
		this.expirationDate = expirationDate;
	}
	public Recruiter getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}
	public VipPackage getVipPackage() {
		return vipPackage;
	}
	public void setVipPackage(VipPackage vipPackage) {
		this.vipPackage = vipPackage;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	@Override
	public String toString() {
		return "DetailRegisterVip [recruiter=" + recruiter + ", vipPackage=" + vipPackage + ", registerDate="
				+ registerDate + ", expirationDate=" + expirationDate + "]";
	}
	
}
