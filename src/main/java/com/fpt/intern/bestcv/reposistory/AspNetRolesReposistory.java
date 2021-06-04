package com.fpt.intern.bestcv.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.AspNetRoles;
import com.fpt.intern.bestcv.entity.AspNetUsers;

@Repository
public interface AspNetRolesReposistory extends JpaRepository<AspNetRoles, String>{
	AspNetRoles findByName(String name);
	AspNetRoles findByUsers(AspNetUsers user);
	
	@Query(value = "select count(*) from bestcv_db.aspnetroles a join bestcv_db.aspnetuserroles asp on a.Id=asp.RoleId join bestcv_db.aspnetusers  u\n" +
			"on asp.UserId=u.Id where a.Name=:role and month( u.CreateDate)=:month and year( u.CreateDate)=:year",nativeQuery = true)
	int countaccountrole(String role,int month,int year);


//	@Query("select count(aspnetuserroles.Id) from aspnetroles inner join aspnetuserroles on aspnetroles.Id=aspnetuserroles.RoleId join aspnetusers \n" +
//			"on aspnetuserroles.UserId=aspnetusers.Id where aspnetroles.Name=ROLE_UTV and month(aspnetusers.CreateDate)=:month and year( aspnetusers.CreateDate)=:year")
//	int countUTVbymonth(int month,int year);
//
//	@Query("select count(aspnetuserroles.Id) from aspnetroles inner join aspnetuserroles on aspnetroles.Id=aspnetuserroles.RoleId join aspnetusers \n" +
//			"on aspnetuserroles.UserId=aspnetusers.Id where aspnetroles.Name=ROLE_NTD and month(aspnetusers.CreateDate)=:month and year( aspnetusers.CreateDate)=:year")
//	int countNTDbymonth(int month,int year);

}
