package com.fpt.intern.bestcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VipPackage")
public class VipPackage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VipPackageId")
	private int vipPackageId;
	@Column(name = "PackName", length = 40, nullable = false)
	private String packName;
	@Column(name = "Price", columnDefinition = "DECIMAL(18,2) not null")
	private double price;
	@Column(name = "Duration", nullable = false)
	private int duration;
	
	//
	public VipPackage( String packName, double price, int duration) {
		super();
		this.packName = packName;
		this.price = price;
		this.duration = duration;
	}
	public VipPackage() {
		super();
	}
	public int getVipPackageId() {
		return vipPackageId;
	}
	public void setVipPackageId(int vipPackageId) {
		this.vipPackageId = vipPackageId;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "VipPackage [vipPackageId=" + vipPackageId + ", packName=" + packName + ", price=" + price
				+ ", duration=" + duration + "]";
	}
	
}
