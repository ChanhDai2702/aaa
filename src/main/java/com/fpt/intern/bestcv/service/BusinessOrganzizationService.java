package com.fpt.intern.bestcv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.BusinessOrganization;


public interface BusinessOrganzizationService {
	void addBusinessOrganzization (BusinessOrganization BO);
	
	void editBusinessOrganization(BusinessOrganization DO);
	
	void removeBusinessOrganization(int id);
	
	List<BusinessOrganization> getAllBusinessOrganization();
	
	BusinessOrganization getBusinessOrganizationByID(int id);
	
	BusinessOrganization findByBusinessModelName(String businessModelName);
}
