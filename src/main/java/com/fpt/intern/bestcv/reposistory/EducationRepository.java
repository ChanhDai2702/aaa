package com.fpt.intern.bestcv.reposistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.intern.bestcv.entity.CurriculumVitae;
import com.fpt.intern.bestcv.entity.Education;

@Repository
public interface EducationRepository  extends CrudRepository<Education, Integer>{
	@Query(value = "SELECT * FROM education n WHERE n.CurriculumVitaeDetailId=?", nativeQuery = true)
	List<Education> findByEducationIdCurriculumVitaeDetail(int i);
	@Modifying
    @Transactional
	@Query(value ="DELETE FROM Education WHERE CurriculumVitaeDetailId=?", nativeQuery = true)
	void deleteByCurriculumVitaeDetailId(int curriculumVitaeDetailId);
}
