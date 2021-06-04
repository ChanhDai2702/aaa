package com.fpt.intern.bestcv.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.intern.bestcv.entity.RecruimentDetail;
import com.fpt.intern.bestcv.entity.RecruimentNews;
import com.fpt.intern.bestcv.reposistory.RecruimentDetailReposistory;
import com.fpt.intern.bestcv.reposistory.RecruimentNewsReposistory;
import com.fpt.intern.bestcv.service.RecruimentNewsService;

@Service
@Transactional
public class RecruimentNewsServiceImpl implements RecruimentNewsService{

	@Autowired
	private RecruimentNewsReposistory recruimentNewsReposistory;
	@Autowired
	private RecruimentDetailReposistory recruimentDetailReposistory;
	
	@Override
	public RecruimentNews getRecruimentNewsById(int id) {
		Optional<RecruimentNews> optional= recruimentNewsReposistory.findById(id);
		if(!optional.isPresent())
			return null;
		return optional.get();
	}

	@Override
	public boolean checkOwnerNews(int newsId, int recruiterId) {
		return recruimentNewsReposistory.existsByIdAndRecruiterId(newsId, recruiterId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addOneViewToNews(int newsId) {
		Optional<RecruimentNews> optional = recruimentNewsReposistory.findById(newsId);
		if(!optional.isPresent())
			return;
		RecruimentNews news= optional.get();
		news.setViews(news.getViews()+1);
		recruimentNewsReposistory.save(news);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveRecruimentNews(RecruimentNews news) throws Exception {
		RecruimentDetail detail = news.getRecruimentDetail();
		news.setRecruimentDetail(null);
		RecruimentNews result=recruimentNewsReposistory.save(news);
		detail.setId(result.getId());
		detail.setRecruimentNews(result);
		recruimentDetailReposistory.save(detail);
	}

	@Override
	public boolean overLimitationCreateRecuimentNewsByRecruiterId(int recruiterId) {
		return recruimentNewsReposistory.overLimitationCreateRecuimentNewsByRecruiterId(30, recruiterId) == 1?true:false;
	}

	@Override
	public double getNewsByMonth(int month) throws NullPointerException {
		// TODO Auto-generated method stub
		try {
			return recruimentNewsReposistory.getNewsByMonth(month);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

}
