package com.fpt.intern.bestcv.reposistory;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fpt.intern.bestcv.dto.LuotXemNganhDTO;
import com.fpt.intern.bestcv.entity.CurriculumVitae;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.fpt.intern.bestcv.entity.CurriculumVitae;
public interface CurriculumVitaeReposistory extends JpaRepository<CurriculumVitae, Integer> {

	
	
	
	@Query(value = "select distinct cv.* from job j  join curriculumvitae cv on cv.JobId=j.JobId join curriculumvitaedetail cvd"
			+ " on cv.CurriculumVitaeId=cvd.CurriculumVitaeId join education ed on cvd.CurriculumVitaeDetailId=ed.CurriculumVitaeDetailId join experience ex"
			+ " on ex.CurriculumVitaeDetailId=cvd.CurriculumVitaeDetailId join skilldetail sd on cvd.CurriculumVitaeDetailId=sd.CurriculumVitaeDetailId"
			+ " where (SkillName like %?1% or CompanyName like %?1% or SchoolName like %?1% or JobPosition like %?1% or JobName like %?1%) and Status = 'true'", nativeQuery = true)
	List<CurriculumVitae> searchCurriculumVitaeForNTD(String keyword);
	
	@Query(value = "SELECT * FROM Curriculumvitae p WHERE p.job_id = :job_id", nativeQuery = true)
	List<CurriculumVitae> searchByJobProduct(int job_id);

	@Query(value = "select count(*) from bestcv_db.Curriculumvitae c join bestcv_db.cvrecruiment cr on c.CurriculumVitaeId = cr.CurriculumVitae where c.CurriculumVitaeId=?1 and `like`=1", nativeQuery = true)
	int getLikeCount(int curriculumVitaeId);

	@Query(value = "select count(*) from bestcv_db.Curriculumvitae c", nativeQuery = true)
	int getCount();

	@Query(value = "select ROUND( (count(*) - (select count(*) from Curriculumvitae where PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'),DATE_FORMAT(CreatedDate, '%Y%m'))=1))*100/ (select count(*) from Curriculumvitae where PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'),DATE_FORMAT(CreatedDate, '%Y%m'))=1),2)"
			+ "from Curriculumvitae where month(CreatedDate)=month(now()) and YEAR(CreatedDate)=YEAR(now()) ", nativeQuery = true)
	double getPercentIncrease() throws NullPointerException;

	@Query(value = "SELECT * FROM Curriculumvitae p WHERE p.candidateid = :id", nativeQuery = true)
	Optional<CurriculumVitae> findByCurriculumVitaeIdCandidate(int id);

	@Query(value = "SELECT  ca.fullname,views , i.IndustryName FROM candidate ca "
			+ "		  JOIN Curriculumvitae c ON c.CandidateId = ca.CandidateId  join job j on j.JobId=c.JobId join industry i on i.IndustryId=j.IndustryId   group by i.IndustryId"
			+ "		ORDER BY views DESC ", nativeQuery = true)
	List<LuotXemNganhDTO> getTopCVByIndustry();

	@Query(value = "SELECT * FROM Curriculumvitae p WHERE p.Status = 'true'", nativeQuery = true)
	List<CurriculumVitae> listAllCurriculumvitaeApprove();

	@Query(value = "SELECT * FROM Curriculumvitae p WHERE p.Status = 'false'", nativeQuery = true)
	List<CurriculumVitae> listAllCurriculumvitaeDontApprove();
	
	@Query(value = "SELECT * FROM curriculumvitae cv join candidate c on cv.CandidateId = c.CandidateId join industry i on i.IndustryId = c.IndustryId where c.IndustryID = ?1 and cv.Status like 'true' ORDER BY cv.Views DESC", nativeQuery = true)
	Page<CurriculumVitae> findCVByIndustryID(int industryID, Pageable pageable);
	
	@Query(value = "SELECT * FROM curriculumvitae cv where cv.Status = 'true' order by  cv.Views desc", nativeQuery = true)
	Page<CurriculumVitae> findAllCVPage(Pageable pageable);
	
	@Query(value = "SELECT * FROM curriculumvitae cv join candidate c on cv.CandidateId = c.CandidateId join industry i on i.IndustryId = c.IndustryId where c.IndustryID = ?1 and cv.Status like 'true' ORDER BY cv.Views DESC limit 9", nativeQuery = true)
	List<CurriculumVitae> findCVByIndustryID(int industryID);
	
	List<CurriculumVitae> findByStatus(String status);
	
	@Query(value = "select distinct cv.* from job j  join curriculumvitae cv on cv.JobId=j.JobId join curriculumvitaedetail cvd"
			+ " on cv.CurriculumVitaeId=cvd.CurriculumVitaeId join education ed on cvd.CurriculumVitaeDetailId=ed.CurriculumVitaeDetailId join experience ex"
			+ " on ex.CurriculumVitaeDetailId=cvd.CurriculumVitaeDetailId join skilldetail sd on cvd.CurriculumVitaeDetailId=sd.CurriculumVitaeDetailId"
			+ " where SkillName like %?1% or CompanyName like %?1% or SchoolName like %?1% or JobPosition like %?1% or JobName like '%?1%' or CandidateId = '?2'", nativeQuery = true)
	List<CurriculumVitae> searchCurriculumVitaeForUTV(String keyword,int candidateID);
	
	@Query(value = "select distinct cv.* from job j  join curriculumvitae cv on cv.JobId=j.JobId join curriculumvitaedetail cvd"
			+ " on cv.CurriculumVitaeId=cvd.CurriculumVitaeId join education ed on cvd.CurriculumVitaeDetailId=ed.CurriculumVitaeDetailId join experience ex"
			+ " on ex.CurriculumVitaeDetailId=cvd.CurriculumVitaeDetailId join skilldetail sd on cvd.CurriculumVitaeDetailId=sd.CurriculumVitaeDetailId"
			+ " where SkillName like %?1% or CompanyName like %?1% or SchoolName like %?1% or JobPosition like %?1% or JobName like %?1% ", nativeQuery = true)
	List<CurriculumVitae> searchCurriculumVitaeForAdmin(String keyword);

	@Query(value = "SELECT * FROM Curriculumvitae p WHERE p.candidateid = :id", nativeQuery = true)
	List<CurriculumVitae> findCurriculumVitaeByCandidateId(int id);
	
	@Query(value = "SELECT * FROM curriculumvitae cv where cv.Status ='true' limit 9", nativeQuery = true)
	List<CurriculumVitae> findAllCvApproved();
	
}
