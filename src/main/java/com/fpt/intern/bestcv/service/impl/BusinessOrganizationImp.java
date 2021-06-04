package com.fpt.intern.bestcv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.BusinessOrganization;
import com.fpt.intern.bestcv.reposistory.BusinessOrganizationReposistory;
import com.fpt.intern.bestcv.service.BusinessOrganzizationService;
@Service
public class BusinessOrganizationImp implements BusinessOrganzizationService {
	@Autowired
	BusinessOrganizationReposistory busi;
	@Override
	public void addBusinessOrganzization(BusinessOrganization BO) {
		
		busi.save(BO);
	}

	@Override
	public void editBusinessOrganization(BusinessOrganization DO) {
		busi.save(DO);
	}

	@Override
	public void removeBusinessOrganization(int id) {
		busi.deleteById(id);
		
	}

	@Override
	public List<BusinessOrganization> getAllBusinessOrganization() {
		
		return (List<BusinessOrganization>) busi.findAll();
	}

	@Override
	public BusinessOrganization getBusinessOrganizationByID(int id) {
		Optional<BusinessOrganization> businessOrganization =  busi.findById(id);
		if(!businessOrganization.isPresent())
			return null;
		return businessOrganization.get();
	}

	@Override
	public BusinessOrganization findByBusinessModelName(String businessModelName) {
		Optional<BusinessOrganization> businessOrganization =  busi.findByBusinessModelName(businessModelName);
		if(!businessOrganization.isPresent())
			return null;
		return businessOrganization.get();
	
	}

}
