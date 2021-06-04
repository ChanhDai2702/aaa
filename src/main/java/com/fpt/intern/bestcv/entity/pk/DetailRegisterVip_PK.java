package com.fpt.intern.bestcv.entity.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class DetailRegisterVip_PK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int recruiter;
	private int vipPackage;
	private Date registerDate;
	public DetailRegisterVip_PK(int recruiter, int vipPackage, Date registerDate) {
		super();
		this.recruiter = recruiter;
		this.vipPackage = vipPackage;
		this.registerDate = registerDate;
	}
	public int getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(int recruiter) {
		this.recruiter = recruiter;
	}
	public int getVipPackage() {
		return vipPackage;
	}
	public void setVipPackage(int vipPackage) {
		this.vipPackage = vipPackage;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recruiter;
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + vipPackage;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailRegisterVip_PK other = (DetailRegisterVip_PK) obj;
		if (recruiter != other.recruiter)
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		if (vipPackage != other.vipPackage)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DetailRegisterVip_PK [recruiter=" + recruiter + ", vipPackage=" + vipPackage + ", registerDate="
				+ registerDate + "]";
	}
	
}
