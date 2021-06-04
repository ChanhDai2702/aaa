package com.fpt.intern.bestcv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.dto.CandidateDTO;
import com.fpt.intern.bestcv.dto.CandidateUserRoleDTO;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.entity.PriorityAddress;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.reposistory.AspNetUsersReposistory;
import com.fpt.intern.bestcv.reposistory.CandidateReposistory;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CandidateDTOservice;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.PriorityAddressService;
import com.fpt.intern.bestcv.service.RecruiterDTOservice;
import com.fpt.intern.bestcv.service.RecruiterService;
@Service
public class RecruiterDTOserviceImpl implements RecruiterDTOservice {
	@Autowired
	private AspNetUsersReposistory aspNetUsersReposistory;
	
	@Autowired
	RecruiterService recruiterService;
	@Autowired
	AspNetUsersService aspnetuserService;
	
	@Autowired 
	IndustryService industryService;
	
	@Autowired 
	PriorityAddressService priorityAddressService;
	@Autowired
	private CandidateReposistory candidateReposistory;
	@Override
	public List<CandidateDTO> getAllAcountCandidatesDTO() {
		// TODO Auto-generated method stub
		return null;
	}
	//tim va lay tat ca thong tin NTD
	@Override
	public CandidateDTO findRecruiterById(int id) {
		// TODO Auto-generated method stub
		Recruiter re = recruiterService.findCruiterById(id);//tim id cua NTD
		PriorityAddress pr=priorityAddressService.getPriorityAddressById(re.getId());//tim id cua dchiutien getId cua NTD
		Industry in=industryService.getIndustryById(re.getId());//tim id cua linhvuc getId cua NTD
		AspNetUsers users = aspnetuserService.findbyId(re.getUsers().getId());//lay thong tin cua aspnetUser by Users từ Recruiter
		CandidateDTO candto = new CandidateDTO();
		candto.setId(re.getId());
		candto.setDiaChi(re.getAddress());
		candto.setHinhAnh(re.getImage());
		candto.setMail(users.getEmail());
		candto.setSdt(users.getPhoneNumber());
		candto.setTenUCV(re.getCompanyName());
		candto.setTenUser(users.getUserName());
		candto.setDiaChiUuTien(pr.getPriorityAddressName());
		candto.setLinhVuc(in.getIndustryName());
		candto.setTrangthai(users.isLockoutEnabled());
	return candto;
		
	}
	
	

}
