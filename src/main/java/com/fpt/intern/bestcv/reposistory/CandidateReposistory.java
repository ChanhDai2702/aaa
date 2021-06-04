package com.fpt.intern.bestcv.reposistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.dto.LuotXemDTO;
import com.fpt.intern.bestcv.entity.Candidate;

@Repository
public interface CandidateReposistory extends JpaRepository<Candidate, Integer>{
	Optional<Candidate> findByUsersId(String userId);
	
	@Query(value = "SELECT ca.fullname,  Views  FROM candidate ca"
			+ " JOIN curriculumvitae c ON c.CandidateId = ca.CandidateId  where month(CreatedDate)=month(now()) and YEAR(CreatedDate)=YEAR(now())"
			+ "	ORDER BY Views DESC, ca.CandidateId DESC LIMIT 3", nativeQuery = true)
	List<LuotXemDTO> getTopThreeView();
	
	@Query(value ="Select candidate_id,full_name,email,phone_number,address ,name from asp_net_users inner join candidate on asp_net_users.id=candidate.user_id",nativeQuery = true)
	Optional<Candidate> findCandidateIdByAspNetUser(int candidateId, int aspNetUserId);
	
	@Query(value="Select candidate_id,full_name,email,phone_number,address from asp_net_users A inner join candidate A on A.asp_net_users.id=C.candidate.user_id where C.fullName like %:fullName%" +"OR C.phoneNumber like %:fullName%",nativeQuery=true)
	List<Candidate> findByNameorsdtCandidate(@Param("fullName") String fullName);
	

}