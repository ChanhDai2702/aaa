package com.fpt.intern.bestcv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.reposistory.DetailRegisterVipRepository;
import com.fpt.intern.bestcv.service.DetailRegisterVipService;

@Service
public class DetailRegisterVipServiceImp implements DetailRegisterVipService {

	@Autowired
	private DetailRegisterVipRepository detailRegisterVipRepository;

	@Override
	public boolean existsVipPackageNotExpByRecruiterId(int recruiterId) {
		return detailRegisterVipRepository.existsVipPackageNotExpByRecruiterId(recruiterId) == 1 ? true : false;
	}

	@Override
	public double getDoanhThu() {
		try {
			return detailRegisterVipRepository.getDoanhThu();
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public double getDoanhThuIncrease() throws NullPointerException {
		// TODO Auto-generated method stub
		try {
			return detailRegisterVipRepository.getDoanhThuIncrease();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}

	@Override
	public double getDoanhThuByMonth(int month) throws NullPointerException {
		// TODO Auto-generated method stub
		try {
			return detailRegisterVipRepository.getDoanhThuByMonth(month);
		}catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}

}
