package com.fpt.intern.bestcv.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.RecruimentDetail;
import com.fpt.intern.bestcv.reposistory.RecruimentDetailReposistory;
import com.fpt.intern.bestcv.service.RecruimentDetailService;

@Service
public class RecruimentDetailServiceImpl implements RecruimentDetailService
{
	@Autowired
	private RecruimentDetailReposistory recruimentDetailReposistory;
	@Override
	public Page<RecruimentDetail> findByKeywords(String keyword,int pageNo,int size) {
		Pageable pageable =  PageRequest.of(pageNo - 1, size);
		return  recruimentDetailReposistory.findByKeyword(keyword,pageable);
	}

	@Override
	public Page<RecruimentDetail> findByKeywordsAdvance(String keyword, String industry, String priorityAddress, int pageNo,
			int size) {
		Pageable pageable =  PageRequest.of(pageNo - 1, size);
		return recruimentDetailReposistory.findByKeywordAdvance(keyword, industry, priorityAddress,pageable);
	}

	@Override
	public Page<RecruimentDetail> findBySalary(double salary, int flex, int pageNo, int size) {
		Pageable pageable =  PageRequest.of(pageNo - 1, size);
		return recruimentDetailReposistory.findBySalary(salary, flex, pageable);
	}

	@Override
	public Page<RecruimentDetail> findBySalaryAdvance(double salary, int flex, String industry, String priorityAddress,
			int pageNo, int size) {
		Pageable pageable =  PageRequest.of(pageNo - 1, size);
		return recruimentDetailReposistory.findBySalaryAdvance(salary, flex, industry, priorityAddress, pageable);
	}

	@Override
	public RecruimentDetail getRecruimentDetailById(int id) {
		Optional<RecruimentDetail> optional = recruimentDetailReposistory.findById(id);
		if(!optional.isPresent())
			return null;
		return optional.get();
	}
	
	@Override
	public Page<RecruimentDetail> findAllPage(int pageNo, int size) {
		Pageable pageable=PageRequest.of(pageNo-1, size);
		return recruimentDetailReposistory.findAll(pageable);
	}
	
}
