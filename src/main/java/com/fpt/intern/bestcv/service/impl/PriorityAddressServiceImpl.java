package com.fpt.intern.bestcv.service.impl;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.PriorityAddress;
import com.fpt.intern.bestcv.reposistory.PriorityAddressReposistory;
import com.fpt.intern.bestcv.service.PriorityAddressService;


@Service
public class PriorityAddressServiceImpl implements PriorityAddressService {

	@Autowired
	private PriorityAddressReposistory priorityAddressReposistory;
	@Override
	public List<PriorityAddress> findAll() {
		return priorityAddressReposistory.findAll();
	}
	@Override
	public void addPriorityAddress(PriorityAddress pa) {
	
		priorityAddressReposistory.save(pa);
	}

	@Override
	public void removePriorityAddressById(int id) {
		
		priorityAddressReposistory.deleteById(id);
	}

	@Override
	public List<PriorityAddress> getAllPriorityAddress() {
		
		return (List<PriorityAddress>) priorityAddressReposistory.findAll();
	}

	@Override
	public PriorityAddress getPriorityAddressById(int id) {
		Optional<PriorityAddress> prior =  priorityAddressReposistory.findById(id);
		if(!prior.isPresent())
			return null;
		return prior.get();

	}

	@Override
	public PriorityAddress findByPriorityAddressName(String string) {
		Optional<PriorityAddress> prior = priorityAddressReposistory.findByPriorityAddressName(string);
		if(!prior.isPresent())
			return null;
		return prior.get();
		
	}
}
