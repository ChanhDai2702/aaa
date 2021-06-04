package com.fpt.intern.bestcv.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.dto.CandidateDTO;
import com.fpt.intern.bestcv.dto.CandidateUserRoleDTO;
import com.fpt.intern.bestcv.entity.AspNetRoles;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.entity.PriorityAddress;
import com.fpt.intern.bestcv.reposistory.AspNetUsersReposistory;
import com.fpt.intern.bestcv.reposistory.CandidateReposistory;
import com.fpt.intern.bestcv.service.AspNetRolesService;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateDTOservice;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.PriorityAddressService;
@Service
public class CandidateDTOserviceImpl implements CandidateDTOservice {
	@Autowired
	private AspNetUsersReposistory aspNetUsersReposistory;
	
	@Autowired
	CandidateService candidateService;
	@Autowired
	AspNetUsersService aspnetuserService;
	
	@Autowired
	AspNetRolesService aspNetRolesService;
	 
	 @Autowired
	PriorityAddressService priorityAddressService;
	 
	 @Autowired
	 CandidateReposistory candidaterepository;
	 
	@Autowired
	private CandidateReposistory candidateReposistory;
	
	@Autowired 
	private IndustryService industryService;
	@Override
	public List<CandidateDTO> getAllAcountCandidatesDTO() {
		// TODO Auto-generated method stub
		return null;
	}
	//tim id de lay tat cac thong tin cua UCV
	@Override
	public CandidateDTO findCandidateById(int id) {
		// TODO Auto-generated method stub
		Candidate can = candidaterepository.findById(id).get();//tim id cua UCV
		PriorityAddress pr=priorityAddressService.getPriorityAddressById(can.getId());//tim id cua dchiutien getId cua UCV
		Industry in=industryService.getIndustryById(can.getId());//tim id cua linhvuc getId cua NTD
		AspNetUsers users = aspnetuserService.findbyId(can.getUsers().getId());//lay thong tin cua aspnetUser by Users từ Candidate
		
		CandidateDTO candto = new CandidateDTO();
		candto.setId(can.getId());
		candto.setDiaChi(can.getAddress());
		candto.setGioitinh(can.getGender());
		candto.setHinhAnh(can.getImage());
		candto.setMail(users.getEmail());
	
		
		candto.setNgaySinh(can.getDateOfBirth());
		candto.setSdt(users.getPhoneNumber());
		candto.setTenUCV(can.getFullName());
		candto.setDiaChiUuTien(pr.getPriorityAddressName());
		candto.setTrangthai(users.isLockoutEnabled());
		candto.setLinhVuc(in.getIndustryName());
		candto.setTenUser(users.getUserName());
		
		
	return candto;
		
	}

	@Override
	public CandidateDTO updateCandidateDTO(CandidateDTO candidateDTO) {
		// TODO Auto-generated method stub
			
		return null;
	}

	@Override
	public void deleteCan(int id) {
//		candidateReposistory.fin
//		candidateReposistory.deleteById(id);
		
		
		
		
	}
	
	

}
