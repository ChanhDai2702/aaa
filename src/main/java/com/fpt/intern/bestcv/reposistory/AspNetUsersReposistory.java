package com.fpt.intern.bestcv.reposistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.AspNetUsers;

@Repository
public interface AspNetUsersReposistory extends JpaRepository<AspNetUsers, String> {
	@Query(value = "select * from AspNetUsers where BINARY Email = ?", nativeQuery = true)
	Optional<AspNetUsers> findByEmail(String email);

	@Query(value = "select * from candidate where user_id = ?user_id", nativeQuery = true)
	AspNetUsers findgetCandidateByUsersId(String user_id);

	@Query(value = "SELECT * FROM bestcv_db.aspnetusers au join bestcv_db.aspnetuserroles ar on au.Id = ar.UserId join bestcv_db.aspnetroles ap on ar.RoleId=ap.Id where ap.Name=?1", nativeQuery = true)
	AspNetUsers getAspNetUsersByRole(String role);

	@Query(value = "SELECT count(*) FROM bestcv_db.aspnetusers", nativeQuery = true)
	int getCount();

	@Query(value = "SELECT count(*)  FROM bestcv_db.aspnetusers u WHERE YEAR(u.CreateDate)=YEAR(now()) and MONTH(u.CreateDate)=MONTH(now()) and dayofmonth(u.CreateDate)= dayofmonth(now())", nativeQuery = true)
	int getIncrease();
	
	AspNetUsers findByUserName(String username);

	@Query("SELECT c FROM AspNetUsers c where c.userName like %:userName% or c.phoneNumber like %:userName%")
	List<AspNetUsers> findByUserNameContaining(@Param("userName") String userName);
	
	@Query("SELECT c FROM AspNetUsers c where c.id like %:id% ")
	List<AspNetUsers> findByUserIdContaining(@Param("id") int id);


}


