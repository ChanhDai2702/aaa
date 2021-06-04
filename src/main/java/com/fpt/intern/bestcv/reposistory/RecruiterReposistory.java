package com.fpt.intern.bestcv.reposistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.dto.RecommendDTO;
import com.fpt.intern.bestcv.entity.Recruiter;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.dto.RecommendDTO;
import com.fpt.intern.bestcv.entity.Recruiter;

@Repository
public interface RecruiterReposistory extends JpaRepository<Recruiter, Integer> {
	Optional<Recruiter> findByUsersId(String userId);

	@Query(value = "select count(*) from bestcv_db.recruiter re", nativeQuery = true)
	int getCount();

	@Query(value = "select ROUND( (count(*) - (select count(*)  from recruiter re join aspnetusers u on u.Id=re.UserId where PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'),DATE_FORMAT(u.CreateDate, '%Y%m'))=1 ))*100/ (select count(*)  from recruiter re join aspnetusers u on u.Id=re.UserId  where PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'),DATE_FORMAT(u.CreateDate, '%Y%m'))=1 ),2)"
			+ "from recruiter re join aspnetusers u on u.Id=re.UserId where month(u.CreateDate)=month(now()) and YEAR(u.CreateDate)=YEAR(now())", nativeQuery = true)
	double getPercentIncrease() throws NullPointerException;

	
	@Query(value = "SELECT * FROM bestcv_db.recruiter r join bestcv_db.industry i on r.IndustryId = i.IndustryId "
			+ "where r.IndustryId = ?1", nativeQuery = true)
	Page<Recruiter> findRecruiterByIndustryIDs(int industryId, Pageable pageable);
	
	@Query(value = "SELECT * FROM recruiter r join industry i on r.IndustryId = i.IndustryId ", nativeQuery = true)
	Page<Recruiter> findAllRecruiters(Pageable pageable);
	
	@Query(value = "SELECT i.IndustryId,r.RecruiterId ,rd.Salary,j.JobName,r.Image,r.CompanyName,r.Address,rd.Quantity,rd.Position\r\n"
			+ "FROM bestcv_db.recruiter r join bestcv_db.recruimentnews rn on rn.RecruiterId = r.RecruiterId \r\n"
			+ "join bestcv_db.recruimentdetail rd on rn.RecruimentNewsId= rd.RecruimentNewsId\r\n"
			+ "join bestcv_db.job j on j.JobId = rd.JopId\r\n"
			+ "join bestcv_db.industry i on j.IndustryId = i.IndustryId where r.RecruiterId = ?1", nativeQuery = true)
	List<RecommendDTO> getRecommendDTO(int recruiterId);
	
	@Query(value = "SELECT rn.RecruimentNewsId,i.IndustryId,r.RecruiterId ,rd.Salary,j.JobName,r.Image,r.CompanyName,r.Address,rd.Quantity,rd.Position\r\n"
			+ "FROM bestcv_db.recruiter r join bestcv_db.recruimentnews rn on rn.RecruiterId = r.RecruiterId \r\n"
			+ "join bestcv_db.recruimentdetail rd on rn.RecruimentNewsId= rd.RecruimentNewsId\r\n"
			+ "join bestcv_db.job j on j.JobId = rd.JopId\r\n"
			+ "join bestcv_db.industry i on j.IndustryId = i.IndustryId limit 9", nativeQuery = true)
	List<RecommendDTO> getRecommendDTO();
}
