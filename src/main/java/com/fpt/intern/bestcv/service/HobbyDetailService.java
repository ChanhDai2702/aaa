package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.entity.HobbyDetail;

public interface HobbyDetailService {
	void createHobbyDetail(HobbyDetail hobbyDetail);
	void deleteListHobbyDetail(List<HobbyDetail> hobbyDetail);
	List<HobbyDetail> getLisHobbyDetail(int curriculumVitaeDetailId);
	void deleteHobbyDetailByCurriculumVitaeDetailId(int curriculumVitaeDetailId);

}
