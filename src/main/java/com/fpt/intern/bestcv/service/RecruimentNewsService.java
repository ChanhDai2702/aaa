package com.fpt.intern.bestcv.service;


import com.fpt.intern.bestcv.entity.RecruimentNews;

public interface RecruimentNewsService {
	RecruimentNews getRecruimentNewsById(int id);
	boolean checkOwnerNews(int newsId, int recruiterId);
	void addOneViewToNews(int newsId);
	void saveRecruimentNews(RecruimentNews news) throws Exception;
	boolean overLimitationCreateRecuimentNewsByRecruiterId(int recruiterId);
	double getNewsByMonth(int month) throws NullPointerException;
}
