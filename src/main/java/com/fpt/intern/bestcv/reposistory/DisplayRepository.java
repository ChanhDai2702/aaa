package com.fpt.intern.bestcv.reposistory;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fpt.intern.bestcv.entity.Display;
@Repository
public interface DisplayRepository extends CrudRepository<Display, Integer>{
	@Query(value = "select d from Display d where d.isActive = 1")
	List<Display> findDisplayIsActive();
	
	@Transactional
	@Modifying
	@Query(value = "update Display d set d.isActive = 0")
	void setActiveToFalse();
	
	@Transactional
	@Modifying
	@Query(value = "update Display d set d.isActive = 1 where d.id = :id")
	void activeDisplayById(@Param(value = "id") int id);
}
