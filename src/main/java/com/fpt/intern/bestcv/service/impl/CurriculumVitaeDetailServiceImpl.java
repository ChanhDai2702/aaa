package com.fpt.intern.bestcv.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.CurriculumVitaeDetail;
import com.fpt.intern.bestcv.reposistory.CurriculumVitaeDetailReposistory;
import com.fpt.intern.bestcv.service.CurriculumVitaeDetailService;

@Service
public class CurriculumVitaeDetailServiceImpl implements CurriculumVitaeDetailService{
	
	@Autowired
	private CurriculumVitaeDetailReposistory curriculumVitaeDetailReposistory;

	@Override
	public Page<CurriculumVitaeDetail> findByKeyword(String keyword, int pageNo, int size) {
		Pageable pageable=PageRequest.of(pageNo-1, size);
		return curriculumVitaeDetailReposistory.findByKeyword(keyword, pageable);
	}

	@Override
	public Page<CurriculumVitaeDetail> findByKeywordAdvance(String keyword, String industry, String priorityAddress,
			int pageNo, int size) {
		Pageable pageable=PageRequest.of(pageNo-1, size);
		return curriculumVitaeDetailReposistory.findByKeywordAdvance(keyword, industry, priorityAddress, pageable);
	}

	@Override
	public Page<CurriculumVitaeDetail> findAllPage(int pageNo, int size) {
		Pageable pageable=PageRequest.of(pageNo-1, size);
		return curriculumVitaeDetailReposistory.findAll(pageable);
	}
		@Override
	public CurriculumVitaeDetail findBCurriculumVitaeDetailIdCurriculumVitae(int i) {
		Optional<CurriculumVitaeDetail> curriculumVitaeDetail = curriculumVitaeDetailReposistory.findBCurriculumVitaeDetailIdCurriculumVitae(i);
		if(!curriculumVitaeDetail.isPresent())
			return null;
		return curriculumVitaeDetail.get();
	}

	@Override
	public void editCurriculumVitaeDetail(CurriculumVitaeDetail c) {
		// TODO Auto-generated method stub
		curriculumVitaeDetailReposistory.save(c);
	}

	@Override
	public CurriculumVitaeDetail getCurriculumVitaeDetailbyID(int i) {
		Optional<CurriculumVitaeDetail> curriculumVitaeDetail = curriculumVitaeDetailReposistory.findById(i);
		if(!curriculumVitaeDetail.isPresent())
			return null;
		return curriculumVitaeDetail.get();
	}
	
	@Override
	public void deleteByCurriculumVitaeDetailId(int curriculumVitaeDetailId) {
		// TODO Auto-generated method stub
		curriculumVitaeDetailReposistory.deleteById(curriculumVitaeDetailId);
	}

}
