package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.entity.JobGoalDetail;

public interface JobGoalDetailService {
	void createJobDetail(JobGoalDetail goalDetail);
	List<JobGoalDetail> getListGoalDetail(int curriculumVitaeDetailId);
	void deleteJobGoalDetailByCurriculumVitaeDetailId(int curriculumVitaeDetailId);

}
