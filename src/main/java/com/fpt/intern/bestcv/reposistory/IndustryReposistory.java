package com.fpt.intern.bestcv.reposistory;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.Industry;
@Transactional
@Repository
public interface IndustryReposistory extends JpaRepository<Industry, Integer>{
	Optional<Industry> findByIndustryName(String industryName);
	
	@Query(value =  "select * from industry where IndustryName = (?1)", nativeQuery = true )
	Industry findIndustryByName(String industryName);
	@Transactional
	@Modifying
	@Query(value =  "delete from industry where IndustryId= (?1)", nativeQuery = true )
	int deleteIndustry(int id);
	
	
	@Query(value = " select industryid, industryname from industry where industryid=:id and industryid in ("
			+ " select industryid from job"
			+ "	) or industryid=:id and   industryid  in  (select industryid from recruiter "
			+ "	) or industryid=:id and industryid  in (select industryid from Candidate ) ", nativeQuery = true)
	Industry checkExsitsIndustryinOtherTable(@Param("id") int id);
	
}
