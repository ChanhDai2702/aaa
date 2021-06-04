package com.fpt.intern.bestcv.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.intern.bestcv.entity.Experience;

@Repository
public interface ExperienceReposistory extends CrudRepository<Experience, Integer> {
	@Query(value = "SELECT * FROM experience n WHERE n.CurriculumVitaeDetailId=?", nativeQuery = true)
	List<Experience> findByExceptionIdCurriculumVitaeDetail(int i);
	@Modifying
    @Transactional
	@Query(value ="DELETE FROM Experience WHERE CurriculumVitaeDetailId=?", nativeQuery = true)
	void deleteByCurriculumVitaeDetailId(int curriculumVitaeDetailId);
}
