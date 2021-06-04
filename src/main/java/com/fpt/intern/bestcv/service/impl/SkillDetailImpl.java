package com.fpt.intern.bestcv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.SkillDetail;
import com.fpt.intern.bestcv.reposistory.SkillDetailReposistory;
import com.fpt.intern.bestcv.service.SkillDetailService;

@Service
public class SkillDetailImpl implements SkillDetailService {
	@Autowired
	private  SkillDetailReposistory skillDetailReposistory;

	@Override
	public void saveSkillDetail(SkillDetail s) {
		// TODO Auto-generated method stub
		skillDetailReposistory.save(s);
	}

	@Override
	public void deleteSkillDetail(int i) {
		// TODO Auto-generated method stub
		skillDetailReposistory.deleteById(i);;
	}

	@Override
	public List<SkillDetail> findByEducationIdCurriculumVitaeDetail(int i) {
		// TODO Auto-generated method stub
		return skillDetailReposistory.findByEducationIdCurriculumVitaeDetail(i);
	}

	@Override
	public SkillDetail getSkillDetailById(int i) {
		Optional<SkillDetail>  skillDetail = skillDetailReposistory.findById(i);
		if(!skillDetail.isPresent())
			return null;
		return skillDetail.get();
	}
	@Override
	public List<SkillDetail> getSkillDetailByTypeofSkill(String typeofSkill) {
		// TODO Auto-generated method stub
		return skillDetailReposistory.findByTypeofSkill(typeofSkill);
	}

	@Override
	public void deleteSkillDetailByCurriculumVitaeDetailId(int curriculumVitaeDetailId) {
		// TODO Auto-generated method stub
		skillDetailReposistory.deleteByCurriculumVitaeDetailId(curriculumVitaeDetailId);
	}
}
