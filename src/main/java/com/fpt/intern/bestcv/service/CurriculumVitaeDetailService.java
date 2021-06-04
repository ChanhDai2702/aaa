package com.fpt.intern.bestcv.service;

import org.springframework.data.domain.Page;

import com.fpt.intern.bestcv.entity.CurriculumVitaeDetail;

public interface CurriculumVitaeDetailService {
	
	Page<CurriculumVitaeDetail> findByKeyword(String keyword,int pageNo,int size);
	Page<CurriculumVitaeDetail> findByKeywordAdvance(String keyword,String industry,String priorityAddress,int pageNo,int size);
	Page<CurriculumVitaeDetail> findAllPage(int pageNo, int i);

	CurriculumVitaeDetail findBCurriculumVitaeDetailIdCurriculumVitae(int i);
	void	editCurriculumVitaeDetail(CurriculumVitaeDetail c);
	CurriculumVitaeDetail getCurriculumVitaeDetailbyID(int i);
	
	void deleteByCurriculumVitaeDetailId(int curriculumVitaeDetailId);
}
