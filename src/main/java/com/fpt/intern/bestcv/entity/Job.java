package com.fpt.intern.bestcv.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Job")
public class Job {
	@Id
	@Column(name = "JobId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "JobName", length = 60, nullable = false,unique = true)
	private String jobName;
	//
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IndustryId", nullable = false)
	private Industry industry;
	
	public Job(String jobName, Industry industry) {
		super();
		this.jobName = jobName;
		this.industry = industry;
	}
	//
	public Job(String jobName) {
		super();
		this.jobName = jobName;
	}
	public Job() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Industry getIndustry() {
		return industry;
	}
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", jobName=" + jobName + "]";
	}
	
}
