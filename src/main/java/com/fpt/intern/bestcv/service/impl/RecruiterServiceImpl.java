package com.fpt.intern.bestcv.service.impl;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.dto.RecommendDTO;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.reposistory.RecruiterReposistory;
import com.fpt.intern.bestcv.service.RecruiterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Service
public class RecruiterServiceImpl implements RecruiterService{
	
	@Autowired
	private RecruiterReposistory recruiterReposistory;
	
	@Override
	public Recruiter getRecruiterByUsersId(String userId) {
		Optional<Recruiter> optional = recruiterReposistory.findByUsersId(userId);
		if(!optional.isPresent())
			return null;
		return optional.get();
	}
	@Override
	public void addRecruiter(Recruiter re) {
		recruiterReposistory.save(re);
		
	}

	@Override
	public void editRecruiter(Recruiter re) {
		// TODO Auto-generated method stub
		recruiterReposistory.save(re);
	}

	@Override
	public void removeRecruiterByID(int id) {
		// TODO Auto-generated method stub
		recruiterReposistory.deleteById(id);
	}
	@Override
	public Recruiter getRecruiterbyID(int id) {
		Optional<Recruiter> findRecruiter = recruiterReposistory.findById(id);
		if(findRecruiter.isPresent()) {

			return findRecruiter.get();

		}
		return null;
	}
	@Override
	public List<Recruiter> getAllByRecruiter() {
		// TODO Auto-generated method stub
		return (List<Recruiter>) recruiterReposistory.findAll();
	}
	
	@Override
	public List<Recruiter> getAllRecuiter() {
	return recruiterReposistory.findAll();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return recruiterReposistory.getCount();
	}
	@Override
	public double getPercentIncrease() throws NullPointerException {
		// TODO Auto-generated method stub
		try {
			return recruiterReposistory.getPercentIncrease();
		}catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	@Override
	public List<RecommendDTO> getRecommendDTO() {
		return recruiterReposistory.getRecommendDTO();
	}
	@Override
	public List<Recruiter> getAllAcountRecruiters() {
		// TODO Auto-generated method stub
		return (List<Recruiter>)recruiterReposistory.findAll();
	}
	@Override
	public Recruiter findCruiterById(int id) {
		// TODO Auto-generated method stub
		Optional<Recruiter> findDisOptional = recruiterReposistory.findById(id);
		return findDisOptional.isPresent() == true ? findDisOptional.get() : null;
	}
	
	@Override
	public Page<Recruiter> getAllRecruiters(int pageNum, int pageSize){
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return recruiterReposistory.findAllRecruiters(pageable);
	}
	
	@Override
	public Page<Recruiter> getRecruiterByIndustryIds(int industryId, int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return recruiterReposistory.findRecruiterByIndustryIDs(industryId, pageable);
	} 
	
	@Override
	public List<RecommendDTO> getRecommendDTO(int id) {
		return recruiterReposistory.getRecommendDTO(id);
	}
	
}
