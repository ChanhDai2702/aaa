package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.entity.SkillDetail;

public interface SkillDetailService {
	void saveSkillDetail(SkillDetail s);
	
	void deleteSkillDetail(int i);
	
	List<SkillDetail> findByEducationIdCurriculumVitaeDetail(int i);
	
	SkillDetail getSkillDetailById(int i);
	
	List<SkillDetail> getSkillDetailByTypeofSkill(String typeofSkill);
	void deleteSkillDetailByCurriculumVitaeDetailId(int curriculumVitaeDetailId);


}
