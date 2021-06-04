package com.fpt.intern.bestcv.service;

import com.fpt.intern.bestcv.entity.RecruimentNewsCandidates;

public interface RecruimentNewsCandidatesService {
	void saveRecruimentNewsCandidates(RecruimentNewsCandidates recruimentNewsCandidates);
	long countByLike(int newsId);
	RecruimentNewsCandidates getRecruimentNewsCandidatesByRecruimentNewsIdAndCandidatesId(int recruimentNewsId, int candidatesId);
}
