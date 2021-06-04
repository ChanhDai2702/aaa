package com.fpt.intern.bestcv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.dto.CandidateUserRoleDTO;
import com.fpt.intern.bestcv.dto.RecruiterUserRoleDTO;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.reposistory.AspNetRolesReposistory;
import com.fpt.intern.bestcv.reposistory.AspNetUsersReposistory;
import com.fpt.intern.bestcv.reposistory.CandidateReposistory;
import com.fpt.intern.bestcv.reposistory.RecruiterReposistory;
import com.fpt.intern.bestcv.service.AspNetRolesService;
import com.fpt.intern.bestcv.service.AspNetUserLoginsServcie;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.RecruiterService;
import com.fpt.intern.bestcv.service.RecruiterUserRoleDTOService;
@Service
public class RecruiterUserRoleDTOServiceImpl implements RecruiterUserRoleDTOService{

	@Autowired
	private RecruiterService recruiterService;
	
	
	@Autowired
	private AspNetRolesService aspNetRolesService;
	
	@Autowired
	private AspNetUserLoginsServcie aspNetUserLoginsServcie;
	
	@Autowired
	private AspNetUsersService aspNetUsersService;
	
	

	@Autowired
	private AspNetUsersReposistory aspNetUsersReposistory;
	@Autowired
	private RecruiterReposistory recruiterReposistory;
	@Autowired
	private AspNetRolesReposistory aspNetRolesReposistory;
	
	//lay tat ca thong tin cua NTD
	@Override
	public List<RecruiterUserRoleDTO> getAllAcountRecruiterUser() {
		List<Recruiter> listrecrui = recruiterReposistory.findAll();
		List<RecruiterUserRoleDTO> listnew = new ArrayList<RecruiterUserRoleDTO>();
		
		for (int i = 0; i < listrecrui.size(); i++) {
				String iduser = listrecrui.get(i).getUsers().getId();//
				AspNetUsers user = aspNetUsersReposistory.findById(iduser).get();//tim id aspNetUser tu list Recruiter
				RecruiterUserRoleDTO recruiterUserRoleDTO = new RecruiterUserRoleDTO();
				recruiterUserRoleDTO.setAspNetUsers(user);//truyen DTO voi AspNetUser
				recruiterUserRoleDTO.setRecruiter(listrecrui.get(i));//truyen DTO voi recruiter
				recruiterUserRoleDTO.setAspNetRoles(user.getRoles());//truyen DTO voi AspNetRoles
				System.out.println("iduser " +iduser);
				System.out.println("user " +user);
				System.out.println("listrecrui.get(i) " +listrecrui.get(i));
				listnew.add(recruiterUserRoleDTO);
		}
		System.out.println("listnew " +listnew);
		return listnew;
	}
	//tim kiem tai khoan theo ten va sdt
	@Override
	public List<RecruiterUserRoleDTO> searchRecruiter(String name) {
		// TODO Auto-generated method stub
		
		List<AspNetUsers> users = aspNetUsersReposistory.findByUserNameContaining(name);//search by user với sdt của user
		System.out.println("aspNetUsersReposistory.findByUserName(name) "+ aspNetUsersReposistory.findByUserNameContaining(name));
		List<RecruiterUserRoleDTO> listDTO = new ArrayList<RecruiterUserRoleDTO>();
		for(int i=0;i<users.size();i++) {
			System.out.println("Recruiter search" +recruiterService.getRecruiterByUsersId(users.get(i).getId()));
			RecruiterUserRoleDTO recruiterUserRoleDTO = new RecruiterUserRoleDTO();
			recruiterUserRoleDTO.setAspNetUsers(users.get(i));//set AspNetUser voi doi tuong lop DTO
			Optional<Recruiter> re=recruiterReposistory.findByUsersId(users.get(i).getId());//tim id UCV voi user tu asspNetUSer
			if(!re.isPresent())
				return null;
			recruiterUserRoleDTO.setRecruiter(re.get());//get tim NTd voi Recruiter
			recruiterUserRoleDTO.setAspNetRoles(users.get(i).getRoles());//
			listDTO.add(recruiterUserRoleDTO);
		}
	return listDTO;
	}

}
