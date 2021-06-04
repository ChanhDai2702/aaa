package com.fpt.intern.bestcv.reposistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.RecruimentNewsCandidates;
import com.fpt.intern.bestcv.entity.pk.RecruimentNewsCandidates_PK;

@Repository
public interface RecruimentNewsCandidatesReposistory extends JpaRepository<RecruimentNewsCandidates, RecruimentNewsCandidates_PK>{
	@Query(value = "SELECT COUNT(*) FROM recruimentnewscandidates n WHERE n.RecruimentnewsId=?1 and n.Like=1", nativeQuery = true)
	long countLikeByRecruimentNewsId(int newsId);
	@Query(value = "SELECT * FROM recruimentnewscandidates n WHERE n.RecruimentNewsId=?1 and n.CandidateId=?2", nativeQuery = true)
	Optional<RecruimentNewsCandidates> findByRecruimentNewsIdAndCandidateId(int recruimentNewsId, int candidateId);
}
