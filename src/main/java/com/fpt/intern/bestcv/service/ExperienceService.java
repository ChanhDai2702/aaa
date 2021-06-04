package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.entity.Experience;



public interface ExperienceService {
	List<Experience> findByExperienceIdCurriculumVitaeDetail(int i);
	
	Experience getExperiencebyid(int id);
		
	void editExperience(Experience e);
	
	void removeExperience(int i);
	void deleteExperienceByCurriculumVitaeDetailId(int curriculumVitaeDetailId);

}
