package com.fpt.intern.bestcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HobbyDetail")
public class HobbyDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HobbyId")
	private int id;
	@Column(name = "Hobby", columnDefinition = "TEXT")
	private String hobby;
	
	@ManyToOne
	@JoinColumn(name = "CurriculumVitaeDetailId", nullable = false)
	private CurriculumVitaeDetail curriculumVitaeDetail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public CurriculumVitaeDetail getCurriculumVitaeDetail() {
		return curriculumVitaeDetail;
	}

	public void setCurriculumVitaeDetail(CurriculumVitaeDetail curriculumVitaeDetail) {
		this.curriculumVitaeDetail = curriculumVitaeDetail;
	}

	public HobbyDetail(String hobby) {
		super();
		this.hobby = hobby;
	}

	public HobbyDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "HobbyDetail [hobby=" + hobby + "]";
	}
	
}
