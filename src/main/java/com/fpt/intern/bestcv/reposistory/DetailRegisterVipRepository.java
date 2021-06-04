package com.fpt.intern.bestcv.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.DetailRegisterVip;
import com.fpt.intern.bestcv.entity.pk.DetailRegisterVip_PK;

@Repository
public interface DetailRegisterVipRepository extends JpaRepository<DetailRegisterVip, DetailRegisterVip_PK> {
	@Query(value = "select count(*)> 0 from detailregistervip d where d.RecruiterId = ?1 and ExpirationDate >= CURDATE()", nativeQuery = true)
	int existsVipPackageNotExpByRecruiterId(int recruiterId);

	@Query(value = "select sum(v.Price) from detailregistervip de join vippackage v on v.VipPackageId=de.VipPackageId", nativeQuery = true)
	double getDoanhThu();

	@Query(value = "select ROUND(( sum(v.Price) - (select sum(v.Price) from detailregistervip de join vippackage v on v.VipPackageId=de.VipPackageId  where PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'),DATE_FORMAT(de.RegisterDate, '%Y%m'))=1))*100/ (select sum(v.Price) from detailregistervip de join vippackage v on v.VipPackageId=de.VipPackageId  where PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'),DATE_FORMAT(de.RegisterDate, '%Y%m'))=1 ),2)"
			+ "		 from detailregistervip de join vippackage v on v.VipPackageId=de.VipPackageId where  month(de.RegisterDate)=month(now()) and YEAR(de.RegisterDate)=YEAR(now()) ", nativeQuery = true)
	double getDoanhThuIncrease() throws NullPointerException;
	
	@Query(value = "select sum(v.Price) from detailregistervip de join vippackage v on v.VipPackageId=de.VipPackageId "
			+ " where PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'),DATE_FORMAT(de.RegisterDate, '%Y%m'))=?1", nativeQuery = true)
	double getDoanhThuByMonth(int month) throws NullPointerException;
}
