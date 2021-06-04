package com.fpt.intern.bestcv.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.intern.bestcv.entity.HobbyDetail;

@Repository
public interface HobbyDetailReposistory extends JpaRepository<HobbyDetail,Integer>{
	List<HobbyDetail> findByCurriculumVitaeDetailId(int CurriculumVitaeDetailId);
	@Modifying
    @Transactional
	@Query(value ="DELETE FROM HobbyDetail WHERE CurriculumVitaeDetailId=?", nativeQuery  = true)
	void deleteHobbyDetailByCurriculumVitaeDetailId(int curriculumVitaeDetailId);
}
