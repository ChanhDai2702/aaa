package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.entity.AspNetRoles;

public interface AspNetRolesService{
	AspNetRoles getRoleByName(String Name);
	List<AspNetRoles> getListAspNetRoles();
}
