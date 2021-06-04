package com.fpt.intern.bestcv.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.intern.bestcv.entity.RecruimentNewsCandidates;
import com.fpt.intern.bestcv.reposistory.RecruimentNewsCandidatesReposistory;
import com.fpt.intern.bestcv.service.RecruimentNewsCandidatesService;

@Service
@Transactional
public class RecruimentNewsCandidatesServiceImpl implements RecruimentNewsCandidatesService{
	
	@Autowired
	private RecruimentNewsCandidatesReposistory recruimentNewsCandidatesReposistory;

	@Override
	public long countByLike(int newsId) {
		return recruimentNewsCandidatesReposistory.countLikeByRecruimentNewsId(newsId);
	}

	@Override
	public RecruimentNewsCandidates getRecruimentNewsCandidatesByRecruimentNewsIdAndCandidatesId(int recruimentNewsId,
			int candidatesId) {
		Optional<RecruimentNewsCandidates> optional = recruimentNewsCandidatesReposistory.findByRecruimentNewsIdAndCandidateId(recruimentNewsId, candidatesId);
		if(!optional.isPresent())
			return null;
		return optional.get();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveRecruimentNewsCandidates(RecruimentNewsCandidates recruimentNewsCandidates) {
		try {
			recruimentNewsCandidatesReposistory.save(recruimentNewsCandidates);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
