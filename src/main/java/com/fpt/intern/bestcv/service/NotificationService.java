package com.fpt.intern.bestcv.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fpt.intern.bestcv.entity.Notification;


public interface NotificationService {
	
	Notification saveNotifications(Notification notifi);
	
	Page<Notification> listAllNotify(int pageNum, int pageSize);
	
	Page<Notification> listFiveNotifyByUserID(String id,int pageNum,int pageSize);
	
	Page<Notification> listNotifyByUserID(String id,int pageNum, int pageSize);
	
	List<Notification> getorderByDESC();
}
