package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.dto.CandidateDTO;



public interface CandidateDTOservice {
	List<CandidateDTO> getAllAcountCandidatesDTO();
	CandidateDTO findCandidateById(int id);
	CandidateDTO updateCandidateDTO(CandidateDTO candidateDTO);
	void deleteCan(int id);

}
