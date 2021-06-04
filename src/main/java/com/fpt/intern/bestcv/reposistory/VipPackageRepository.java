package com.fpt.intern.bestcv.reposistory;

import com.fpt.intern.bestcv.entity.VipPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VipPackageRepository extends JpaRepository<VipPackage,Integer> {
    @Query(value ="select sum(v.price) from vip_package v left join detail_register_vip d on v.vip_package_id=d.vip_package_id " +
            "where day(d.register_date)=:day and month(d.register_date)=:month and year(d.register_date)=:year",nativeQuery = true)
    double statisticalByDate(int day,int month,int year);

    @Query(value ="select sum(v.price) from vip_package v left join detail_register_vip d on v.vip_package_id=d.vip_package_id " +
            "where month(d.register_date)=:month and year(d.register_date)=:year",nativeQuery = true)
    double statisticalByMonth(int month,int year);

    @Query(value ="select sum(v.price) from vip_package v left join detail_register_vip d on v.vip_package_id=d.vip_package_id " +
            "where year(d.register_date)=:year",nativeQuery = true)
    double statisticalByYear(int year);




}
