package com.fpt.intern.bestcv.reposistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>{

	Notification findById(int id);
	
	@Query(value= "SELECT * FROM bestcv_db.notification order by NotificationId DESC",nativeQuery = true)
	Page<Notification> getorderByDESC(Pageable pageable);
	
	@Query(value = "SELECT * FROM bestcv_db.notification n where n.UserId=?1",nativeQuery = true)
	Page<Notification> getNotificationByaspNetUsers(String id,Pageable pageable);
	
	@Query(value = "SELECT * FROM bestcv_db.notification n where n.UserId=?1 order by NotificationId DESC",nativeQuery = true)
	Page<Notification> getNotificationByaspNetUsersDESC(String id,Pageable pageable);
}
