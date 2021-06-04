package com.fpt.intern.bestcv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Curriculumvitae")
public class CurriculumVitae {
	@Id
	@Column(name = "CurriculumVitaeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "CreatedDate", nullable = false)
	private Date createdDate;
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "UpdatedDate", nullable = false)
	private Date updatedDate;
	@Column(name = "Views", nullable = false)
	private int views=0;
	@Column(name = "Status", length = 40)
	private String status;
	@Column(name = "formType", columnDefinition = "int not null default 0")
	private int formType;
	@Column(name = "FileName", length = 4000)
	private String fileName;
	//
	@ManyToOne
	@JoinColumn(name = "CandidateId", nullable = false)
	private Candidate candidate;
	@ManyToOne
	@JoinColumn(name = "JobId", nullable = false)
	private Job job;
	//
	public CurriculumVitae(Date createdDate, Date updatedDate, int views, String status, int formType,
			String fileName) {
		super();
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.views = views;
		this.status = status;
		this.formType = formType;
		this.fileName = fileName;
	}
	public CurriculumVitae() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFormType() {
		return formType;
	}
	public void setFormType(int formType) {
		this.formType = formType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	@Override
	public String toString() {
		return "CurriculumVitae [id=" + id + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", views=" + views + ", status=" + status + ", formType=" + formType + ", fileName=" + fileName
				+ "]";
	}
	
	
}
