package com.fpt.intern.bestcv.dto;

import java.util.Date;

public class CandidateDTO {
	private int id;
	private String tenUCV;
	private String mail;
	private String sdt;
	private Date ngaySinh;
	private String gioitinh;
	private String diaChi;
	private String hinhAnh;
	private String tenUser;
	private boolean trangthai;
	private String diaChiUuTien;
	private String linhVuc;
	public String getDiaChiUuTien() {
		return diaChiUuTien;
	}
	public void setDiaChiUuTien(String diaChiUuTien) {
		this.diaChiUuTien = diaChiUuTien;
	}
	public String getLinhVuc() {
		return linhVuc;
	}
	public void setLinhVuc(String linhVuc) {
		this.linhVuc = linhVuc;
	}
	public boolean isTrangthai() {
		return trangthai;
	}
	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenUCV() {
		return tenUCV;
	}
	public void setTenUCV(String tenUCV) {
		this.tenUCV = tenUCV;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public String getTenUser() {
		return tenUser;
	}
	public void setTenUser(String tenUser) {
		this.tenUser = tenUser;
	}
	
	public CandidateDTO(int id, String tenUCV, String mail, String sdt, Date ngaySinh, String gioitinh, String diaChi,
			String hinhAnh, String tenUser, boolean trangthai, String diaChiUuTien, String linhVuc) {
		super();
		this.id = id;
		this.tenUCV = tenUCV;
		this.mail = mail;
		this.sdt = sdt;
		this.ngaySinh = ngaySinh;
		this.gioitinh = gioitinh;
		this.diaChi = diaChi;
		this.hinhAnh = hinhAnh;
		this.tenUser = tenUser;
		this.trangthai = trangthai;
		this.diaChiUuTien = diaChiUuTien;
		this.linhVuc = linhVuc;
	}
	public CandidateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
