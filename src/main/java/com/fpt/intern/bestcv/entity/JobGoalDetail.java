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
@Table(name = "JobGoalDetail")
public class JobGoalDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JobGoalId")
	private int id;
	
	@Column(name = "JobGoal", columnDefinition = "TEXT")
	private String jobGoal;
	
	@ManyToOne
	@JoinColumn(name = "CurriculumVitaeDetailId", nullable = false)
	private CurriculumVitaeDetail curriculumVitaeDetail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobGoal() {
		return jobGoal;
	}

	public void setJobGoal(String jobGoal) {
		this.jobGoal = jobGoal;
	}

	public CurriculumVitaeDetail getCurriculumVitaeDetail() {
		return curriculumVitaeDetail;
	}

	public void setCurriculumVitaeDetail(CurriculumVitaeDetail curriculumVitaeDetail) {
		this.curriculumVitaeDetail = curriculumVitaeDetail;
	}

	public JobGoalDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobGoalDetail(String jobGoal) {
		super();
		this.jobGoal = jobGoal;
	}

	@Override
	public String toString() {
		return "JobGoalDetail [jobGoal=" + jobGoal + "]";
	}


}
