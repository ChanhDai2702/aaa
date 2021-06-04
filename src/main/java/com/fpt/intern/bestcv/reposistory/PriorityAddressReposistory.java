package com.fpt.intern.bestcv.reposistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.PriorityAddress;

@Repository
public interface PriorityAddressReposistory extends JpaRepository<PriorityAddress, Integer>{
	Optional<PriorityAddress> findByPriorityAddressName(String priorityAddressName);
}