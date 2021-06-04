package com.fpt.intern.bestcv.reposistory;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.RecruimentDetail;

@Repository
public interface RecruimentDetailReposistory extends JpaRepository<RecruimentDetail, Integer>{
	
	
	@Query(value = "select * from RecruimentDetail RD join Job J on RD.JopId=J.JobId where CONCAT(RD.Position,J.JobName) like %?1%",nativeQuery = true)
	Page<RecruimentDetail> findByKeyword(String keyword,Pageable pageable);
	@Query(value = "select * from RecruimentDetail RD join Job J on RD.JopId=J.JobId where CONCAT(RD.Position,J.JobName) like %?1% "
			+ "  and RecruimentNewsId in (select RecruimentNewsId from RecruimentNews where RecruiterId in "
			+ "(select RecruiterId from bestcv_db.Recruiter where IndustryId like %?2% and PriorityAddressId like %?3%))",nativeQuery = true)
	Page<RecruimentDetail> findByKeywordAdvance(String keyword, String industryId, String priorityId,Pageable pageable);
	@Query(value = "SELECT * from RecruimentDetail where (?1-?2)<=Salary && (?1+?2)>=Salary",nativeQuery = true)
	Page<RecruimentDetail> findBySalary(double salary, int flex,Pageable pageable);
	@Query(value = "SELECT * from RecruimentDetail where (?1-?2)<=Salary && (?1+?2)>=Salary "
			+ "  and RecruimentNewsId in (select RecruimentNewsId from RecruimentNews where RecruiterId in "
			+ "(select RecruiterId from bestcv_db.Recruiter where IndustryId like %?3% and PriorityAddressId like %?4%))",nativeQuery = true)
	Page<RecruimentDetail> findBySalaryAdvance(double salary, int flex, String industryId, String priorityId,Pageable pageable);
}
