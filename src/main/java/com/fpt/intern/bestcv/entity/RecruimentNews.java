package com.fpt.intern.bestcv.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "RecruimentNews")
public class RecruimentNews {
	@Id
	@Column(name = "RecruimentNewsId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "Views", nullable = false)
	private int views;
	@Column(name = "FillingTime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fillingTime;
	@Column(name = "WorkDescription", columnDefinition = "TEXT")
	private String workDescription;
	@Column(name = "Requirements", columnDefinition = "TEXT")
	private String requirements;
	@Column(name = "Status", columnDefinition = "TEXT")
	private String status="Chờ duyệt";
	@ManyToOne
	@JoinColumn(name = "RecruiterId", nullable = false)
	private Recruiter recruiter;
	
	@OneToOne(mappedBy = "recruimentNews", cascade = CascadeType.ALL)
	private RecruimentDetail recruimentDetail;
	
	@UpdateTimestamp
	@Column(name = "ModifiedDate", columnDefinition = "DATETIME NOT NULL default '1900-01-01T00:00:00.000'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	public RecruimentNews(int views, Date fillingTime, String workDescription, String requirements,
			String status, Date modifiedDate) {
		super();
		this.views = views;
		this.fillingTime = fillingTime;
		this.workDescription = workDescription;
		this.requirements = requirements;
		this.status = status;
		this.modifiedDate = modifiedDate;
	}
	
	public RecruimentNews() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Date getFillingTime() {
		return fillingTime;
	}
	public void setFillingTime(Date fillingTime) {
		this.fillingTime = fillingTime;
	}
	public String getWorkDescription() {
		return workDescription;
	}
	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Recruiter getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public RecruimentDetail getRecruimentDetail() {
		return recruimentDetail;
	}

	public void setRecruimentDetail(RecruimentDetail recruimentDetail) {
		this.recruimentDetail = recruimentDetail;
	}

	@Override
	public String toString() {
		return "RecruimentNews [id=" + id + ", views=" + views + ", fillingTime=" + fillingTime + ", workDescription="
				+ workDescription + ", requirements=" + requirements + ", status=" + status + ", recruiter=" + recruiter
				+ ", recruimentDetail=" + recruimentDetail + ", modifiedDate=" + modifiedDate + "]";
	}

}
