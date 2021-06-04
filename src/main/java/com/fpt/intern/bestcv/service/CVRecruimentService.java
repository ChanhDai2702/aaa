package com.fpt.intern.bestcv.service;

public interface CVRecruimentService {
	int getCountRecruited();
	int getLikeCountByCV(int curriculumVitaeId);
	double getPercentIncrease() throws NullPointerException; 
}
