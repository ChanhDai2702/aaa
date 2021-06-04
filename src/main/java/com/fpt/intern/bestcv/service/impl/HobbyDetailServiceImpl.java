package com.fpt.intern.bestcv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.HobbyDetail;
import com.fpt.intern.bestcv.reposistory.HobbyDetailReposistory;
import com.fpt.intern.bestcv.service.HobbyDetailService;

@Service
public class HobbyDetailServiceImpl implements HobbyDetailService{

	@Autowired
	private HobbyDetailReposistory hobbyDetailReposistory;
	
	@Override
	public void createHobbyDetail(HobbyDetail hobbyDetail) {
		// TODO Auto-generated method stub
		hobbyDetailReposistory.save(hobbyDetail);
	}

	@Override
	public List<HobbyDetail> getLisHobbyDetail(int curriculumVitaeDetailId) {
		// TODO Auto-generated method stub
		return hobbyDetailReposistory.findByCurriculumVitaeDetailId(curriculumVitaeDetailId);
	}

	@Override
	public void deleteListHobbyDetail(List<HobbyDetail> hobbyDetail) {
		// TODO Auto-generated method stub
		hobbyDetailReposistory.deleteAll(hobbyDetail);
	}
	@Override
	public void deleteHobbyDetailByCurriculumVitaeDetailId(int curriculumVitaeDetailId) {
		// TODO Auto-generated method stub
		hobbyDetailReposistory.deleteHobbyDetailByCurriculumVitaeDetailId(curriculumVitaeDetailId);
	}
}
