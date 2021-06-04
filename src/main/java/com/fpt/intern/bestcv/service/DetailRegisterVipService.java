package com.fpt.intern.bestcv.service;

public interface DetailRegisterVipService {
	boolean existsVipPackageNotExpByRecruiterId(int recruiterId);
	double getDoanhThu();
	double getDoanhThuIncrease() throws NullPointerException;
	double getDoanhThuByMonth(int month) throws NullPointerException;
}
