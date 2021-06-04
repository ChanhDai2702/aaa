package com.fpt.intern.bestcv.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.AspNetUserLogins;
import com.fpt.intern.bestcv.entity.pk.AspNetUserLogins_PK;

@Repository
public interface AspNetUserLoginsReposistory extends JpaRepository<AspNetUserLogins,AspNetUserLogins_PK>{
	AspNetUserLogins findByProviderKey(String providerKey);

	@Query("SELECT u FROM AspNetUserLogins u WHERE u.loginProvider = ?1 and u.providerKey = ?2")
	AspNetUserLogins findByLoginProviderAndProviderKey(String LoginProvider,String providerKey);
}
