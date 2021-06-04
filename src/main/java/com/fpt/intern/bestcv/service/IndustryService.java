package com.fpt.intern.bestcv.service;


import java.util.List;

import com.fpt.intern.bestcv.entity.Industry;


public interface IndustryService {
	
	List<Industry> getListAllIndustry();
	boolean deleteIndustry(int id);
	List<Industry> getListIn();
	boolean saveIns(Industry x);
	Industry findIndustryById(int id);
	Industry getIndustryByName(String name);
	Industry getIndustryById(int id);
}
