package com.fpt.intern.bestcv.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.intern.bestcv.entity.JobGoalDetail;

@Repository
public interface JobGoalDetailRepository extends JpaRepository<JobGoalDetail,Integer>{
	List<JobGoalDetail> findByCurriculumVitaeDetailId(int CurriculumVitaeDetailId);
	@Modifying
    @Transactional
	@Query(value ="DELETE FROM JobGoalDetail WHERE CurriculumVitaeDetailId=?", nativeQuery = true)
	void deleteByCurriculumVitaeDetailId(int curriculumVitaeDetailId);
}
