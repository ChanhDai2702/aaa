package com.fpt.intern.bestcv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.dto.LuotXemNganhDTO;
import com.fpt.intern.bestcv.entity.CurriculumVitae;
import com.fpt.intern.bestcv.reposistory.CurriculumVitaeReposistory;
import com.fpt.intern.bestcv.service.CurriculumVitaeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Service
public class CurriculumVitaeServiceImpl implements CurriculumVitaeService {
	@Autowired
	private CurriculumVitaeReposistory curriculumVitaeReposistory;

	@Override
	public List<CurriculumVitae> getAllCurriculumVitae() {

		return (List<CurriculumVitae>) curriculumVitaeReposistory.findAll();
	}

	@Override
	public List<CurriculumVitae> search(int job_id) {

		return (List<CurriculumVitae>) curriculumVitaeReposistory.searchByJobProduct(job_id);
	}

	@Override
	public int getLikeCount(int curriculumVitaeId) {
		return curriculumVitaeReposistory.getLikeCount(curriculumVitaeId);
	}

	@Override
	public void editCurriculumVitae(CurriculumVitae c) {
		// TODO Auto-generated method stub
		curriculumVitaeReposistory.save(c);
	}

	@Override
	public CurriculumVitae getCurriculumVitaeByID(int id) {
		Optional<CurriculumVitae> curriculumVitae = curriculumVitaeReposistory.findById(id);
		if (!curriculumVitae.isPresent())
			return null;
		return curriculumVitae.get();
	}

	@Override
	public CurriculumVitae findByCurriculumVitaeIdCandidate(int id) {
		Optional<CurriculumVitae> curriculumVitae = curriculumVitaeReposistory.findByCurriculumVitaeIdCandidate(id);
		if (!curriculumVitae.isPresent())
			return null;
		return curriculumVitae.get();
	}
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		curriculumVitaeReposistory.deleteById(id);
	}
	@Override
	public CurriculumVitae findByIdAndUpdateApprove(int id) {
		CurriculumVitae curriculumVitae = curriculumVitaeReposistory.findById(id).get();
		System.out.println(curriculumVitae);
		curriculumVitae.setStatus("true");
		curriculumVitaeReposistory.save(curriculumVitae);
		return curriculumVitae;
	}
	@Override
	public List<CurriculumVitae> getAllCurriculumVitaeApprove() {
		return curriculumVitaeReposistory.listAllCurriculumvitaeApprove();
	}
	@Override
	public List<CurriculumVitae> getAllCurriculumVitaeDontApprove() {
		return curriculumVitaeReposistory.listAllCurriculumvitaeDontApprove();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return curriculumVitaeReposistory.getCount();
	}

	@Override
	public double getPercentIncrease() throws NullPointerException {
		// TODO Auto-generated method stub
		try {
			return curriculumVitaeReposistory.getPercentIncrease();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public List<LuotXemNganhDTO> getTopCVByIndustry() {
		// TODO Auto-generated method stub
		return curriculumVitaeReposistory.getTopCVByIndustry();
	}

	@Override
	public Page<CurriculumVitae> getCVByIndustryID(int industryID, int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return curriculumVitaeReposistory.findCVByIndustryID(industryID, pageable);
	}

	@Override
	public Page<CurriculumVitae> getAllCVPages(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return curriculumVitaeReposistory.findAllCVPage(pageable);
	}

	@Override
	public List<CurriculumVitae> getCVByIndustryID(int industryID) {
		return curriculumVitaeReposistory.findCVByIndustryID(industryID);
	}


	@Override
public List<CurriculumVitae> getAllCurriculumVitaeByKeyWordForUTV(String keyword,int candidateId) {
	// TODO Auto-generated method stub
	return curriculumVitaeReposistory.searchCurriculumVitaeForUTV(keyword,candidateId);
	}
	@Override
	public List<CurriculumVitae> getAllCurriculumVitaeByKeyWordForAdmin(String keyword) {
		// TODO Auto-generated method stub
		return curriculumVitaeReposistory.searchCurriculumVitaeForAdmin(keyword);
	}

	@Override
	public List<CurriculumVitae> getAllCurriculumVitaeByStatus(String status) {
		// TODO Auto-generated method stub
		return curriculumVitaeReposistory.findByStatus(status);
	}

	@Override
	public List<CurriculumVitae> getCurriculumVitaeByCandidateId(int id) {
		// TODO Auto-generated method stub
		return curriculumVitaeReposistory.findCurriculumVitaeByCandidateId(id);
	}

	@Override
	public List<CurriculumVitae> getAllCurriculumVitaeByKeyWordForNTD(String keyword) {
		// TODO Auto-generated method stub
		return curriculumVitaeReposistory.searchCurriculumVitaeForNTD(keyword);
	}
	
	@Override
	public void addOneViewToNews(int id) {
		Optional<CurriculumVitae> optional = curriculumVitaeReposistory.findById(id);
		if(!optional.isPresent())
			return;
		CurriculumVitae news= optional.get();
		news.setViews(news.getViews()+1);
		curriculumVitaeReposistory.save(news);	
	}
	
	@Override
	public List<CurriculumVitae> getAllCvApproved() {
		return curriculumVitaeReposistory.findAllCvApproved();
	}
}
