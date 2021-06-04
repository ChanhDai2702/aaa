package com.fpt.intern.bestcv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.AspNetRoles;
import com.fpt.intern.bestcv.reposistory.AspNetRolesReposistory;
import com.fpt.intern.bestcv.service.AspNetRolesService;

@Service
public class AspNetRolesServiceImp implements AspNetRolesService{

	@Autowired
	private AspNetRolesReposistory aspNetRolesReposistory;
	
	@Override
	public AspNetRoles getRoleByName(String Name) {
		// TODO Auto-generated method stub
		return aspNetRolesReposistory.findByName(Name);
	}

	@Override
	public List<AspNetRoles> getListAspNetRoles() {
		// TODO Auto-generated method stub
		return aspNetRolesReposistory.findAll();
	}

}
