package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.dto.LuotXemDTO;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;

public interface CandidateService {
	Candidate getCandidateByUsersId(String userId);

	Candidate getCandidateById(int id);
	
	void saveCandidate(Candidate c);
	
	List<LuotXemDTO> getTopThreeView();
	
	
	List<Candidate> getAllAcountCandidates();
	Candidate findCandidateById(int id);
	AspNetUsers searchCandidate(int id);
	Candidate getCandidateByAspCandidateId(int candidateId,int aspNetUserId);
}
