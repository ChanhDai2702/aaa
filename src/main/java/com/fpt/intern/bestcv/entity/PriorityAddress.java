package com.fpt.intern.bestcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PriorityAddress")
public class PriorityAddress {
	@Id
	@Column(name = "PriorityAddressId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int priorityAddressId;
	@Column(name = "PriorityAddressName", length = 160, nullable = false)
	private String priorityAddressName;
	public PriorityAddress(String priorityAddressName) {
		this.priorityAddressName = priorityAddressName;
	}
	public PriorityAddress() {
	}
	public int getPriorityAddressId() {
		return priorityAddressId;
	}
	public void setPriorityAddressId(int priorityAddressId) {
		this.priorityAddressId = priorityAddressId;
	}
	public String getPriorityAddressName() {
		return priorityAddressName;
	}
	public void setPriorityAddressName(String priorityAddressName) {
		this.priorityAddressName = priorityAddressName;
	}
	@Override
	public String toString() {
		return "PriorityAddress [priorityAddressId=" + priorityAddressId + ", priorityAddressName="
				+ priorityAddressName + "]";
	}
	
}
