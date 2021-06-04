package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.dto.CandidateDTO;
import com.fpt.intern.bestcv.entity.Candidate;


public interface RecruiterDTOservice {
	List<CandidateDTO> getAllAcountCandidatesDTO();
	CandidateDTO findRecruiterById(int id);

}
