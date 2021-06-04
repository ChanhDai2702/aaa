package com.fpt.intern.bestcv.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.Admin;
import com.fpt.intern.bestcv.reposistory.AdminReposistory;
import com.fpt.intern.bestcv.service.AdminService;
@Service
public class AdminImp implements AdminService {
	@Autowired
	AdminReposistory admin;
	@Autowired
	private AdminReposistory adminReposistory;
	@Override
	public void editAdmin(Admin a) {
		// TODO Auto-generated method stub
		admin.save(a);
	}

	@Override
	public Admin getAdminbyid(int i) {
		Optional<Admin> a = admin.findById(i);
		if(!a.isPresent())
			return null;
		return a.get();
	}

	@Override
	public Admin getUserId(String id) {
		Optional<Admin> admin1 = admin.findByUserId(id);
		if(!admin1.isPresent()) {
			return null;
		}
		return admin1.get();
	}


	@Override
	public Admin getAdminByUsersId(String userId) {
		Optional<Admin> optional = adminReposistory.findByUsersId(userId);
		if(!optional.isPresent())
			return null;
		return optional.get();
	}

}
