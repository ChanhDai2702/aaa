package com.fpt.intern.bestcv.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fpt.intern.bestcv.entity.RecruimentNews;
import com.fpt.intern.bestcv.service.impl.RecruimentNewsServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RecruimentNewsServiceTest {
	@TestConfiguration
	public static class TodoServiceTest2Configuration {

		@Bean
		RecruimentNewsService recruimentNewsService() {
			return new RecruimentNewsServiceImpl();
		}
	}
	
	@Autowired
	private RecruimentNewsService recruimentNewsService;
	
	@Test
	public void whenValidNewsId_thenRecruimentNewsShouldBeFound() {
		int id= 1;
		RecruimentNews news = recruimentNewsService.getRecruimentNewsById(id);
		
		assertNotNull(news);
	}
	@Test
	public void whenNotValidNewsId_thenRecruimentNewsShouldNotBeFound() {
		int id= 999999;
		RecruimentNews news = recruimentNewsService.getRecruimentNewsById(id);
		
		assertNull(news);
	}
	@Test
	public void whenValidNewsIdAndRecuiterId_thenRecruimentNewsShouldBeFound() {
		int id= 1;
		RecruimentNews news = recruimentNewsService.getRecruimentNewsById(id);
		
		assertNotNull(news);
	}
	@Test
	public void whenNotValidNewsIdAndRecuiterId_thenRecruimentNewsShouldBeFound() {
		int id= 999999;
		RecruimentNews news = recruimentNewsService.getRecruimentNewsById(id);
		
		assertNull(news);
	}
	@Test
	public void whenValidNewsId_thenAddOneViewToNews() {
		int id= 1;
		int viewOld = recruimentNewsService.getRecruimentNewsById(id).getViews();
		recruimentNewsService.addOneViewToNews(id);
		int viewNew = recruimentNewsService.getRecruimentNewsById(id).getViews();
		assertThat(viewNew).isGreaterThan(viewOld);
	}
}
