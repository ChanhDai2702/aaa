package com.fpt.intern.bestcv.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.dto.CandidateUserRoleDTO;

import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Display;
import com.fpt.intern.bestcv.reposistory.AspNetRolesReposistory;
import com.fpt.intern.bestcv.reposistory.AspNetUsersReposistory;
import com.fpt.intern.bestcv.reposistory.CandidateReposistory;
import com.fpt.intern.bestcv.service.AspNetRolesService;
import com.fpt.intern.bestcv.service.AspNetUserLoginsServcie;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.CandidateUserRoleDTOService;
@Service
public class CandidateUserRoleDTOServiceImpl implements CandidateUserRoleDTOService {
	
	@Autowired 
	private CandidateService candidateService;
	
	@Autowired
	private AspNetRolesService aspNetRolesService;
	
	@Autowired
	private AspNetUserLoginsServcie aspNetUserLoginsServcie;
	
	@Autowired
	private AspNetUsersService aspNetUsersService;
	
	@Autowired
	private AspNetUsersReposistory aspNetUsersReposistory;
	@Autowired
	private CandidateReposistory candidateReposistory;
	@Autowired
	private AspNetRolesReposistory aspNetRolesReposistory;
	@Override
	public List<CandidateUserRoleDTO> getAllAcountCandidatesUser() {
		
		List<Candidate> listcan = candidateReposistory.findAll();
		List<CandidateUserRoleDTO> listnew = new ArrayList<CandidateUserRoleDTO>();
		
		for (int i = 0; i < listcan.size(); i++) {
				String iduser = listcan.get(i).getUsers().getId();
				AspNetUsers user = aspNetUsersReposistory.findById(iduser).get();//tim id aspNetUser tu list Candidate
				CandidateUserRoleDTO candidateUserRoleDTO = new CandidateUserRoleDTO();
				candidateUserRoleDTO.setAspNetUsers(user);//truyen DTO voi AspNetUser
				candidateUserRoleDTO.setCandidate(listcan.get(i));//truyen DTO voi candidate
				candidateUserRoleDTO.setAspNetRoles(user.getRoles());//truyen DTO voi AspNetRoles
				System.out.println("iduser " +iduser);
				System.out.println("user " +user);
				System.out.println("listcan.get(i) " +listcan.get(i));
				listnew.add(candidateUserRoleDTO);
		}
		System.out.println("listnew " +listnew);
		
		return listnew;
	}
	
	//tim kiem thong tin tai khoan by ten va sdt
	@Override
	public List<CandidateUserRoleDTO> searchCandidate(String name) {
			List<AspNetUsers> users = aspNetUsersReposistory.findByUserNameContaining(name);//search tu khoa by ten vs sdt lay tu AspNetUser
			List<CandidateUserRoleDTO> listDTO = new ArrayList<CandidateUserRoleDTO>();//tao list DTO de lay thong tin tu 3 bang 
			for(int i=0;i<users.size();i++) {
				System.out.println("candidates Search " +candidateService.getCandidateByUsersId(users.get(i).getId()));
				CandidateUserRoleDTO candidateUserRoleDTO = new CandidateUserRoleDTO();
				candidateUserRoleDTO.setAspNetUsers(users.get(i));
				Optional<Candidate> can = candidateReposistory.findByUsersId(users.get(i).getId());
				if(!can.isPresent())
					return null;
				candidateUserRoleDTO.setCandidate(can.get());
				candidateUserRoleDTO.setAspNetRoles(users.get(i).getRoles());
				listDTO.add(candidateUserRoleDTO);
			}
			
		return listDTO;
	}
	@Override
	public CandidateUserRoleDTO updateCandidate(CandidateUserRoleDTO candidateUserRoleDTO) {
		// TODO Auto-generated method stub
	
		return null;
	}


	

	
	
	
	
	
	
	
	
}
