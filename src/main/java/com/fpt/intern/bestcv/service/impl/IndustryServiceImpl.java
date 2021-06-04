package com.fpt.intern.bestcv.service.impl;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.reposistory.IndustryReposistory;
import com.fpt.intern.bestcv.service.IndustryService;


@Service
public class IndustryServiceImpl implements IndustryService {
	@Autowired
	private IndustryReposistory industryReposistory;

	@Override
	public List<Industry> getListAllIndustry() {
		return industryReposistory.findAll();
	}


	@Override
	public boolean deleteIndustry(int id) {
		if (industryReposistory.checkExsitsIndustryinOtherTable(id)!=null) {
			return false;
		}
		if (industryReposistory.deleteIndustry(id)>0) {
			return true;
		}
		return false;
	}
	@Override
	public List<Industry> getListIn() {
	
		return industryReposistory.findAll();
	}
	@Override
	public boolean saveIns(Industry x) {
		if (industryReposistory.findIndustryByName(x.getIndustryName())==null) {
			industryReposistory.save(x);
			return true;
		}	
		return false;
	}
	@Override
	public Industry findIndustryById(int id) {
		return industryReposistory.getOne(id);
	}

	@Override
	public Industry getIndustryById(int id) {
		Optional<Industry> industry = industryReposistory.findById(id);
		if(!industry.isPresent())
			return null;
		return industry.get();
	
	}

	@Override
	public Industry getIndustryByName(String name) {
		Optional<Industry> industry = industryReposistory.findByIndustryName(name);
		if(!industry.isPresent())
			return null;
		return industry.get();
	}
}
