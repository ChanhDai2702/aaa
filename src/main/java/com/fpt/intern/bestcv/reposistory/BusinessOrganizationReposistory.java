package com.fpt.intern.bestcv.reposistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.BusinessOrganization;

@Repository
public interface BusinessOrganizationReposistory extends JpaRepository<BusinessOrganization, Integer>{
	Optional<BusinessOrganization> findByBusinessModelName(String businessModelName);
}
