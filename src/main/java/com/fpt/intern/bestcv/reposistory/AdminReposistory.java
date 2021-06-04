package com.fpt.intern.bestcv.reposistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.intern.bestcv.entity.Admin;

@Repository
public interface AdminReposistory extends JpaRepository<Admin, Integer>{
    @Query(value = "SELECT * FROM admin n WHERE n.UserId=?", nativeQuery = true)
	Optional<Admin> findByUserId(String id);
    
    Optional<Admin> findByUsersId(String userId);
}
