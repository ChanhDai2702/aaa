package com.fpt.intern.bestcv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.reposistory.CVRecruimentReposistory;
import com.fpt.intern.bestcv.service.CVRecruimentService;

@Service
public class CVRecruimentServiceImpl implements CVRecruimentService {

	@Autowired
	private CVRecruimentReposistory cvRecruimentReposistory;

	@Override
	public int getLikeCountByCV(int curriculumVitaeId) {
		return cvRecruimentReposistory.getLikeCount(curriculumVitaeId);
	}

	@Override
	public int getCountRecruited() {
		// TODO Auto-generated method stub
		return cvRecruimentReposistory.getCountRecruited();
	}

	@Override
	public double getPercentIncrease() throws NullPointerException {
		// TODO Auto-generated method stub
		try {
			return cvRecruimentReposistory.getPercentIncrease();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

}
