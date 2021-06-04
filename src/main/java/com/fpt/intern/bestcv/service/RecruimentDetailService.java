package com.fpt.intern.bestcv.service;

import org.springframework.data.domain.Page;

import com.fpt.intern.bestcv.entity.RecruimentDetail;

public interface RecruimentDetailService {
	RecruimentDetail getRecruimentDetailById(int id);
	Page<RecruimentDetail> findAllPage(int pageNo, int size);
	//search
	Page<RecruimentDetail> findByKeywords(String keyword,int pageNo,int size);
	Page<RecruimentDetail> findByKeywordsAdvance(String keyword,String industry,String priorityAddress,int pageNo,int size);
	Page<RecruimentDetail> findBySalary(double salary,int flex,int pageNo,int size);
	Page<RecruimentDetail> findBySalaryAdvance(double salary,int flex,String industry,String priorityAddress,int pageNo,int size);

}
