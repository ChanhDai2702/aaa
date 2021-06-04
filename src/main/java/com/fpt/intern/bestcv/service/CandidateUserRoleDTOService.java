package com.fpt.intern.bestcv.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fpt.intern.bestcv.dto.CandidateUserRoleDTO;
import com.fpt.intern.bestcv.entity.Display;


public interface CandidateUserRoleDTOService {
	 List<CandidateUserRoleDTO> getAllAcountCandidatesUser();
	 public List<CandidateUserRoleDTO> searchCandidate(String name);
	 CandidateUserRoleDTO updateCandidate(CandidateUserRoleDTO candidateUserRoleDTO);
	
	
}
