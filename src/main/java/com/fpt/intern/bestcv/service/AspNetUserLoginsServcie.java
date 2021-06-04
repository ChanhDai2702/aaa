package com.fpt.intern.bestcv.service;

import com.fpt.intern.bestcv.entity.AspNetUserLogins;

public interface AspNetUserLoginsServcie {
	AspNetUserLogins getAspNetUserLoginsByKey(String Key);
	void CreateUserLogins(AspNetUserLogins user);
	AspNetUserLogins getAspNetUserLoginsByProviderAndByKey(String login,String Key);
}
