package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.dto.RecommendDTO;
import com.fpt.intern.bestcv.entity.Recruiter;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RecruiterService {
	Recruiter getRecruiterByUsersId(String userId);

	List<Recruiter> getAllRecuiter();
	
	void addRecruiter(Recruiter re);
	int getCount();
	public void editRecruiter(Recruiter re);
	void removeRecruiterByID(int id);
	List<Recruiter> getAllByRecruiter();
	Recruiter	getRecruiterbyID(int id);
	double getPercentIncrease() throws NullPointerException; 
	
	List<Recruiter> getAllAcountRecruiters();
	Recruiter findCruiterById(int id);
	
	Page<Recruiter> getRecruiterByIndustryIds(int industryId, int pageable, int pageSize);
	
	Page<Recruiter> getAllRecruiters(int pageable, int pageSize);
	
	List<RecommendDTO> getRecommendDTO(int id);
	
	List<RecommendDTO> getRecommendDTO();
}
