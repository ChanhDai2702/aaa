package com.fpt.intern.bestcv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.JobGoalDetail;
import com.fpt.intern.bestcv.reposistory.JobGoalDetailRepository;
import com.fpt.intern.bestcv.service.JobGoalDetailService;

@Service
public class JobGoalDetailServiceImpl implements JobGoalDetailService{
	@Autowired
	private JobGoalDetailRepository jobGoalDetailRepository;
	@Override
	public void createJobDetail(JobGoalDetail goalDetail) {
		// TODO Auto-generated method stub
		jobGoalDetailRepository.save(goalDetail);
	}

	@Override
	public List<JobGoalDetail> getListGoalDetail(int curriculumVitaeDetailId) {
		// TODO Auto-generated method stub
		return jobGoalDetailRepository.findByCurriculumVitaeDetailId(curriculumVitaeDetailId);
	}
	@Override
	public void deleteJobGoalDetailByCurriculumVitaeDetailId(int curriculumVitaeDetailId) {
		// TODO Auto-generated method stub
		jobGoalDetailRepository.deleteByCurriculumVitaeDetailId(curriculumVitaeDetailId);
	}
}
