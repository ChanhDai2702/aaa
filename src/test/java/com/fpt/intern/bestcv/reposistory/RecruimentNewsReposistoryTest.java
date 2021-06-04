package com.fpt.intern.bestcv.reposistory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fpt.intern.bestcv.reposistory.RecruimentNewsReposistory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RecruimentNewsReposistoryTest {
	
	@Autowired
	RecruimentNewsReposistory recruimentNewsReposistory;
	
	@Test
	public void whenValidIdRecruimentNewsFindById_thenExist() {
		int id= 1;
		boolean isPresent = recruimentNewsReposistory.findById(id).isPresent();
		
		Assert.assertTrue(isPresent);
	}
	@Test
	public void whenNotValidIdRecruimentNewsFindById_thenNotExist() {
		int id= -1;
		boolean isPresent = recruimentNewsReposistory.findById(id).isPresent();
		
		Assert.assertFalse(isPresent);
	}
	@Test
	public void whenNotValidRecruiterIdAndValidNewsIdExistsByIdAndRecruiterId_thenNotExist() {
		int recruiterId=-1;
		int newsId=1;
		boolean expectedResult = false;
		boolean actualResult = recruimentNewsReposistory.existsByIdAndRecruiterId(newsId, recruiterId);
		assertEquals(expectedResult, actualResult);
	}
	@Test
	public void whenValidRecruiterIdAndValidNewsIdExistsByIdAndRecruiterId_thenExist() {
		int recruiterId=1;
		int newsId=1;
		boolean expectedResult = true;
		boolean actualResult = recruimentNewsReposistory.existsByIdAndRecruiterId(newsId, recruiterId);
		assertEquals(expectedResult, actualResult);
	}
	@Test
	public void whenNotValidRecruiterIdAndNotValidNewsIdExistsByIdAndRecruiterId_thenNotExist() {
		int recruiterId=-1;
		int newsId=-1;
		boolean expectedResult = false;
		boolean actualResult = recruimentNewsReposistory.existsByIdAndRecruiterId(newsId, recruiterId);
		assertEquals(expectedResult, actualResult);
	}
	@Test
	public void whenValidRecruiterIdAndNotValidNewsIdExistsByIdAndRecruiterId_thenNotExist() {
		int recruiterId=1;
		int newsId=-1;
		boolean expectedResult = false;
		boolean actualResult = recruimentNewsReposistory.existsByIdAndRecruiterId(newsId, recruiterId);
		assertEquals(expectedResult, actualResult);
	}
}