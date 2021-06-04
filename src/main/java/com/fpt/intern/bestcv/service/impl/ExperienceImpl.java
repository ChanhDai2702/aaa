package com.fpt.intern.bestcv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.Experience;
import com.fpt.intern.bestcv.reposistory.ExperienceReposistory;
import com.fpt.intern.bestcv.service.ExperienceService;

@Service
public class ExperienceImpl implements ExperienceService {
	@Autowired
	private ExperienceReposistory experienceReposistory;

	@Override
	public List<Experience> findByExperienceIdCurriculumVitaeDetail(int i) {

		return experienceReposistory.findByExceptionIdCurriculumVitaeDetail(i);
	}

	@Override
	public void editExperience(Experience e) {
		// TODO Auto-generated method stub
		experienceReposistory.save(e);
	}

	@Override
	public Experience getExperiencebyid(int id) {
		Optional<Experience> experience = experienceReposistory.findById(id);
		if(!experience.isPresent())
			return null;
		return experience.get();
	}

	@Override
	public void removeExperience(int i) {
		experienceReposistory.deleteById(i);
		
	}
	@Override
	public void deleteExperienceByCurriculumVitaeDetailId(int curriculumVitaeDetailId) {
		// TODO Auto-generated method stub
		experienceReposistory.deleteByCurriculumVitaeDetailId(curriculumVitaeDetailId);
	}
}
