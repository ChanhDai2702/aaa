package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.entity.Education;

public interface EducationService {
	List<Education> findByEducationIdCurriculumVitaeDetail(int e);
	
	void editEducation(Education e);
	
	Education getEducationbyID(int id);
	
	void removeEducation(Education e);
	
	void deleteEducationByCurriculumVitaeDetailId(int curriculumVitaeDetailId);

}
