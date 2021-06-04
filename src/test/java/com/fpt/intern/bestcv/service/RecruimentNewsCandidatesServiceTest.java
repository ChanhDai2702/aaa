package com.fpt.intern.bestcv.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fpt.intern.bestcv.entity.AspNetRoles;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Industry;
import com.fpt.intern.bestcv.entity.PriorityAddress;
import com.fpt.intern.bestcv.entity.RecruimentNews;
import com.fpt.intern.bestcv.entity.RecruimentNewsCandidates;
import com.fpt.intern.bestcv.service.impl.RecruimentNewsCandidatesServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RecruimentNewsCandidatesServiceTest {
	
	@TestConfiguration
	public static class TodoServiceTest2Configuration {

		@Bean
		RecruimentNewsCandidatesService recruimentNewsCandidatesService() {
			return new RecruimentNewsCandidatesServiceImpl();
		}
	}
	
	@Autowired
	private RecruimentNewsCandidatesService recruimentNewsCandidatesService;
	
	@Test
	public void whenValidNewsId_thenShouldBeReturnQuantityLike() {
		int id= 1;
		long expected= recruimentNewsCandidatesService.countByLike(id);
		assertThat(expected).isGreaterThanOrEqualTo(0);
	}
	
	@Test
	public void whenNotValidNewsId_thenShouldBeReturnZeroValue() {
		int id= -1;
		long expected=0;
		long actual= recruimentNewsCandidatesService.countByLike(id);
		assertEquals(expected, actual);
	}
	
	@Test
	public void whenValidCandidatesIdAndValidRecruimentNewsId_thenShouldBeFound() {
		int candidatesId=1;
		int recruimentNewsId=1;
		RecruimentNewsCandidates actual = recruimentNewsCandidatesService.getRecruimentNewsCandidatesByRecruimentNewsIdAndCandidatesId(recruimentNewsId, candidatesId);
		assertNotNull(actual);
	}
	
	@Test
	public void whenNotValidCandidatesIdAndNotValidRecruimentNewsId_thenShouldNotBeFound() {
		int candidatesId=-1;
		int recruimentNewsId=-1;
		RecruimentNewsCandidates actual = recruimentNewsCandidatesService.getRecruimentNewsCandidatesByRecruimentNewsIdAndCandidatesId(recruimentNewsId, candidatesId);
		assertNull(actual);
	}
	
	@Test
	public void whenValidCandidatesIdAndNotValidRecruimentNewsId_thenShouldNotBeFound() {
		int candidatesId=1;
		int recruimentNewsId=-1;
		RecruimentNewsCandidates actual = recruimentNewsCandidatesService.getRecruimentNewsCandidatesByRecruimentNewsIdAndCandidatesId(recruimentNewsId, candidatesId);
		assertNull(actual);
	}
	
	@Test
	public void whenNotValidCandidatesIdAndValidRecruimentNewsId_thenShouldNotBeFound() {
		int candidatesId=-1;
		int recruimentNewsId=1;
		RecruimentNewsCandidates actual = recruimentNewsCandidatesService.getRecruimentNewsCandidatesByRecruimentNewsIdAndCandidatesId(recruimentNewsId, candidatesId);
		assertNull(actual);
	}
	@Test
	public void whenValidRecruimentNewsCandidates_thenShouldBeSaved() {
		AspNetUsers utv = new AspNetUsers();
		utv.setId("c329dc08-c83c-406c-9e51-0a717c6e979c");
		utv.setEmail("utv@bestcv.com");
		utv.setEmailComfirmed(true);
		utv.setUserName("utv@bestcv.com");
		utv.setPasswordHash("");
		HashSet<AspNetRoles> roles = new HashSet<>();
		AspNetRoles role = new AspNetRoles("","ROLE_UTV");
		roles.add(role);
		utv.setRoles(roles);

		Candidate candidate = new Candidate("Nguyễn Ngọc Hà", "656/80 Quang Trung, p11, quận Gò Vấp, tp. Hồ Chí Minh", "/images/emoji.png", new Date(), "Nam");
		candidate.setId(1);
		Industry industry = new Industry("Công nghệ thông tin");
		industry.setId(1);
		candidate.setIndustry(industry);
		PriorityAddress address= new PriorityAddress("TP HCM");
		address.setPriorityAddressId(1);
		candidate.setPriorityAddress(address);
		candidate.setUsers(utv);
		
		RecruimentNews news= new RecruimentNews(0, new Date(), "", "", "Đã duyệt", new Date());
		news.setId(1);
		
		RecruimentNewsCandidates recruimentNewsCandidates= new RecruimentNewsCandidates(news, candidate, true);
		recruimentNewsCandidatesService.saveRecruimentNewsCandidates(recruimentNewsCandidates);
		RecruimentNewsCandidates recruimentNewsCandidatesFound= recruimentNewsCandidatesService.getRecruimentNewsCandidatesByRecruimentNewsIdAndCandidatesId(news.getId(), candidate.getId());
		assertNotNull(recruimentNewsCandidatesFound);
	}
}
