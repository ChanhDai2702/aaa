package com.fpt.intern.bestcv.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.intern.bestcv.entity.SkillDetail;

@Repository
public interface SkillDetailReposistory extends CrudRepository<SkillDetail, Integer> {
	@Query(value = "SELECT * FROM skilldetail n WHERE n.CurriculumVitaeDetailId=?", nativeQuery = true)
	List<SkillDetail> findByEducationIdCurriculumVitaeDetail(int i);
	
	List<SkillDetail> findByTypeofSkill(String typeofSkill);
	
	@Modifying
    @Transactional
	@Query(value ="DELETE FROM SkillDetail WHERE CurriculumVitaeDetailId=?", nativeQuery = true)
	void deleteByCurriculumVitaeDetailId(int curriculumVitaeDetailId);
}
