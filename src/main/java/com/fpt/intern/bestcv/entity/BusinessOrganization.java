package com.fpt.intern.bestcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BusinessOrganization")
public class BusinessOrganization {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name = "BussninessOrganizationId")
	private int bussninessOrganizationId;
	@Column(name = "BusinessModelName", length = 50, nullable = false)
	private String businessModelName;
	public BusinessOrganization(String businessModelName) {
		this.businessModelName = businessModelName;
	}
	public BusinessOrganization() {
		super();
	}
	public int getBussninessOrganizationId() {
		return bussninessOrganizationId;
	}
	public void setBussninessOrganizationId(int bussninessOrganizationId) {
		this.bussninessOrganizationId = bussninessOrganizationId;
	}
	public String getBusinessModelName() {
		return businessModelName;
	}
	public void setBusinessModelName(String businessModelName) {
		this.businessModelName = businessModelName;
	}
	@Override
	public String toString() {
		return "BusinessOrganization [bussninessOrganizationId=" + bussninessOrganizationId + ", businessModelName="
				+ businessModelName + "]";
	}
	
}
