package com.fpt.intern.bestcv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.AspNetUserLogins;
import com.fpt.intern.bestcv.reposistory.AspNetUserLoginsReposistory;
import com.fpt.intern.bestcv.service.AspNetUserLoginsServcie;

@Service
public class AspNetUserLoginsServiceImpl implements AspNetUserLoginsServcie{

	@Autowired
	private AspNetUserLoginsReposistory aspNetUserLoginsReposistory;
	@Override
	public AspNetUserLogins getAspNetUserLoginsByKey(String key) {
		return aspNetUserLoginsReposistory.findByProviderKey(key);
	}
	@Override
	public void CreateUserLogins(AspNetUserLogins user) {
		// TODO Auto-generated method stub
		aspNetUserLoginsReposistory.save(user);
	}

	@Override
	public AspNetUserLogins getAspNetUserLoginsByProviderAndByKey(String login, String Key) {
		// TODO Auto-generated method stub
		return aspNetUserLoginsReposistory.findByLoginProviderAndProviderKey(login, Key);
	}

}
