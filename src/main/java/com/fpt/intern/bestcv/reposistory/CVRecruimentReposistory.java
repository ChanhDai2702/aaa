package com.fpt.intern.bestcv.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.CVRecruiment;
import com.fpt.intern.bestcv.entity.pk.CVRecruiment_PK;

@Repository
public interface CVRecruimentReposistory extends JpaRepository<CVRecruiment, CVRecruiment_PK> {

	@Query(value = "SELECT count(*) FROM cvrecruiment where `like`=1 and CurriculumVitae=?1", nativeQuery = true)
	int getLikeCount(int curriculumVitaeId);

	@Query(value = "select count(*) from bestcv_db.curriculumvitae c join bestcv_db.cvrecruiment cr on c.CurriculumVitaeId = cr.CurriculumVitae where `approved`=1", nativeQuery = true)
	int getCountRecruited();

	@Query(value = "select ROUND( (count(*) - (select count(*) from cvrecruiment where PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'),DATE_FORMAT(ApprovedDate, '%Y%m'))=1))*100/ (select count(*) from cvrecruiment where PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'),DATE_FORMAT(ApprovedDate, '%Y%m'))=1),2)"
			+ "from cvrecruiment where month(ApprovedDate)=month(now()) and YEAR(ApprovedDate)=YEAR(now()) ", nativeQuery = true)
	double getPercentIncrease() throws NullPointerException;
}
