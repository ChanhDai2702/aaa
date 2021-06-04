package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.dto.UserDTO;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Recruiter;

public interface AspNetUsersService {
	AspNetUsers getAspNetUsersByEmail(String email);
	void updateAspNetUsers(AspNetUsers user);
	public void updatePassword(AspNetUsers user, String newPassword);
	int getCount();
	void addAspNetUsers(AspNetUsers user);
	List<AspNetUsers> getAllAspNetUsers();
	AspNetUsers getAspNetUserByRole_Admin(String role_admin);
	AspNetUsers getAspNetUsersByID(String id);
	int getIncrease();
	Candidate registerUCV(UserDTO userDTO,String provider);
	Recruiter registerNTD(UserDTO userDTO,String provider);
	List<AspNetUsers> getAllAcountAspNetUsers();
	AspNetUsers findbyId(String id);
}
