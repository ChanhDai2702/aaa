package com.fpt.intern.bestcv.service;

import com.fpt.intern.bestcv.entity.Admin;

public interface AdminService {
	void editAdmin(Admin a);
	
	Admin getAdminbyid(int i);
	
	Admin getUserId(String id);
	
	Admin getAdminByUsersId(String userId);
}
