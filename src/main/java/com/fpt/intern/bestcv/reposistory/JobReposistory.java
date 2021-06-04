package com.fpt.intern.bestcv.reposistory;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.Job;
@Transactional
@Repository
public interface JobReposistory extends JpaRepository<Job, Integer>{
	Optional<Job> findByJobName(String jobName);
	
	@Query(value =  "select * from job where JobName = (?1)", nativeQuery = true )
	Job findJobByName(String jobName);
	
	@Modifying
	@Query(value = " delete from job where JobId =:id", nativeQuery = true)
	public int deleteJob(@Param("id")int id);
	
	List<Job> findByIndustryId(int industryId);
	
	@Query(value = "select jobid,jobname,industryid from job where jobid =:id and jobid in ("
			+ "	select jopid from recruimentdetail "
			+ "	) or jobid =:id and jobid in  (	select jobid from curriculumvitae ) ",nativeQuery = true)
	Job checkExistsJobinOtherTable(@Param("id")int id);
	
}