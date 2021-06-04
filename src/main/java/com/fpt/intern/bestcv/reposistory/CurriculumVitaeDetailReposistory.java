package com.fpt.intern.bestcv.reposistory;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpt.intern.bestcv.entity.CurriculumVitaeDetail;

public interface CurriculumVitaeDetailReposistory extends JpaRepository<CurriculumVitaeDetail, Integer>{
	
	@Query(value = "select * from CurriculumVitaeDetail cd join CurriculumVitae c on cd.CurriculumVitaeId = c.CurriculumVitaeId join Job j on c.JobId= j.JobId where concat(cd.JobPosition,j.JobName) like %?1%",nativeQuery = true)
	Page<CurriculumVitaeDetail> findByKeyword(String keyword,Pageable pageable);
	@Query(value = "select * from CurriculumVitaeDetail cd join CurriculumVitae c on cd.CurriculumVitaeId = c.CurriculumVitaeId join Job j on c.JobId= j.JobId where concat(cd.JobPosition,j.JobName) like %?1% and "
			+ "c.CandidateId in (select CandidateId from Candidate where IndustryId like %?2% and PriorityAddressId like %?3%)",nativeQuery = true)
	Page<CurriculumVitaeDetail> findByKeywordAdvance(String keyword,String industry,String priority,Pageable pageable);

	
	@Query(value = "SELECT * FROM curriculumvitaedetail n WHERE n.curriculumvitaeid=?", nativeQuery = true)
	Optional<CurriculumVitaeDetail> findBCurriculumVitaeDetailIdCurriculumVitae(int i);
}
