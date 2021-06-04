package com.fpt.intern.bestcv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.dto.LuotXemDTO;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.reposistory.CandidateReposistory;
import com.fpt.intern.bestcv.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService{
	
	@Autowired
	private CandidateReposistory candidateReposistory;
	
	@Override
	public Candidate getCandidateByUsersId(String userId) {
		Optional<Candidate> optional = candidateReposistory.findByUsersId(userId);
		if(!optional.isPresent())
			return null;
		return optional.get();
	}
	@Override
	public Candidate getCandidateById(int id) {
		Optional<Candidate> candidate = candidateReposistory.findById(id);
		if(!candidate.isPresent()) {
			return null;
		}
		return candidate.get();
	}

	@Override
	public void saveCandidate(Candidate c) {
		candidateReposistory.save(c);
		
	}

	@Override
	public List<LuotXemDTO> getTopThreeView() {
		// TODO Auto-generated method stub
		return candidateReposistory.getTopThreeView();
	}
	@Override
	public List<Candidate> getAllAcountCandidates() {
		// TODO Auto-generated method stub
		return (List<Candidate>) candidateReposistory.findAll();
	}
	@Override
	public Candidate findCandidateById(int id) {
		// TODO Auto-generated method stub
	//	Optional<Candidate> findDisOptional = ;
		return candidateReposistory.findById(id).get();
	}
	@Override
	public AspNetUsers searchCandidate(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Candidate getCandidateByAspCandidateId(int candidateId, int aspNetUserId) {
		// TODO Auto-generated method stub
		Optional<Candidate> optional = candidateReposistory.findCandidateIdByAspNetUser(candidateId, aspNetUserId);
		if(!optional.isPresent())
			return null;
		return optional.get();
	}
	
}
