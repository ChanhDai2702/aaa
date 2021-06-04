package com.fpt.intern.bestcv.service;


import java.util.List;

import com.fpt.intern.bestcv.entity.PriorityAddress;


public interface PriorityAddressService {
	
	List<PriorityAddress> findAll();

	void addPriorityAddress(PriorityAddress pa);
	void removePriorityAddressById(int id);
	List<PriorityAddress> getAllPriorityAddress();
	PriorityAddress getPriorityAddressById(int id);
	PriorityAddress findByPriorityAddressName(String string);
}
