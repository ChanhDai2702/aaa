package com.fpt.intern.bestcv.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.RecruimentNews;

@Repository
public interface RecruimentNewsReposistory extends JpaRepository<RecruimentNews, Integer> {
	boolean existsByIdAndRecruiterId(int newsId, int recruiterId);

	@Query(value = "select count(*)> ?1 from recruimentnews n WHERE n.RecruiterId=?2 and MONTH(n.modifiedDate)= MONTH(CURRENT_DATE())", nativeQuery = true)
	int overLimitationCreateRecuimentNewsByRecruiterId(int limit, int recruiterId);

	@Query(value = "select count(*) from  recruimentnews  where PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'),DATE_FORMAT(fillingTime, '%Y%m'))=?1", nativeQuery = true)
	double getNewsByMonth(int month) throws NullPointerException;
}
