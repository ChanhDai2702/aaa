package com.fpt.intern.bestcv.service.impl;

import java.util.List;

import javax.management.NotificationEmitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.Notification;
import com.fpt.intern.bestcv.reposistory.NotificationRepository;
import com.fpt.intern.bestcv.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationRepository notificationRepository;
	
	@Override
	public Notification saveNotifications(Notification notifi) {
		// TODO Auto-generated method stub
		return notificationRepository.save(notifi);
	}

	@Override
	public Page<Notification> listAllNotify(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return notificationRepository.findAll(pageable);
	}

	@Override
	public List<Notification> getorderByDESC() {
	
		return null;
	}

//	@Override
//	public Page<Notification> listFiveNotify(int pageNum, int pageSize) {
////		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
////		return notificationRepository.getorderByDESC(pageable);
//		return null;
//	}

	@Override
	public Page<Notification> listNotifyByUserID(String id, int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return notificationRepository.getNotificationByaspNetUsers(id, pageable);
	}

	@Override
	public Page<Notification> listFiveNotifyByUserID(String id, int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return notificationRepository.getNotificationByaspNetUsersDESC(id,pageable);
	}

}
