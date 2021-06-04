package com.fpt.intern.bestcv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.Education;
import com.fpt.intern.bestcv.reposistory.EducationRepository;
import com.fpt.intern.bestcv.service.EducationService;
@Service
public class EducationImp implements EducationService {
	@Autowired
	private EducationRepository educationRepository;
	

	@Override
	public void editEducation(Education e) {
		// TODO Auto-generated method stub
		educationRepository.save(e);
	}


	@Override
	public List<Education> findByEducationIdCurriculumVitaeDetail(int e) {
		// TODO Auto-generated method stub
		return educationRepository.findByEducationIdCurriculumVitaeDetail(e);
	}


	@Override
	public Education getEducationbyID(int id) {
		// TODO Auto-generated method stub
		Optional<Education> education =  educationRepository.findById(id);
		if(!education.isPresent())
			return null;
		return education.get();
	}


	@Override
	public void removeEducation(Education e) {
		educationRepository.delete(e);
		
	}
	
	@Override
	public void deleteEducationByCurriculumVitaeDetailId(int curriculumVitaeDetailId) {
		// TODO Auto-generated method stub
		educationRepository.deleteByCurriculumVitaeDetailId(curriculumVitaeDetailId);
	}

}
