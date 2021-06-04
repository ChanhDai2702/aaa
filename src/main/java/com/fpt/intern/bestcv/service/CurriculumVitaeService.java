package com.fpt.intern.bestcv.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fpt.intern.bestcv.dto.LuotXemNganhDTO;
import com.fpt.intern.bestcv.entity.CurriculumVitae;

public interface CurriculumVitaeService {
	
	int getLikeCount(int CurriculumVitaeId);

	List<CurriculumVitae> search(int job_id);

	List<CurriculumVitae> getAllCurriculumVitae();
	List<CurriculumVitae> getAllCurriculumVitaeByKeyWordForAdmin(String keyword);
	List<CurriculumVitae> getAllCurriculumVitaeByKeyWordForNTD(String keyword);
	List<CurriculumVitae> getAllCurriculumVitaeByKeyWordForUTV(String keyword,int candidateId);
	List<CurriculumVitae> getAllCurriculumVitaeByStatus(String status);
	List<CurriculumVitae> getAllCurriculumVitaeApprove();
	List<CurriculumVitae> getAllCurriculumVitaeDontApprove();
	List<CurriculumVitae> getCurriculumVitaeByCandidateId(int id);

	void editCurriculumVitae(CurriculumVitae c);
	
	CurriculumVitae getCurriculumVitaeByID(int id);
	
	CurriculumVitae findByCurriculumVitaeIdCandidate(int id);
	
	void deleteById(int id);
	
	CurriculumVitae findByIdAndUpdateApprove(int id);
	double getPercentIncrease() throws NullPointerException;
	int getCount();
	List<LuotXemNganhDTO> getTopCVByIndustry();
	Page<CurriculumVitae> getCVByIndustryID(int industryID, int pageable, int pageSize);
	Page<CurriculumVitae> getAllCVPages(int pageNum, int pageSize);
	List<CurriculumVitae> getCVByIndustryID(int industryID);
	
	void addOneViewToNews(int id);
	
	List<CurriculumVitae> getAllCvApproved();
	

	
}
