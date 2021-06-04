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

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Notification")
public class Notification {
	@Id
	@Column(name = "NotificationId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "Content", length = 200, nullable = false)
	private String content;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateDate", updatable = false, nullable = false)
	private Date createDate;
	@Column(name = "Status", columnDefinition = "TEXT")
	private String status;
	@ManyToOne
	@JoinColumn(name = "RecruimentNewsId")
	private RecruimentNews recruimentNews;
	@ManyToOne
	@JoinColumn(name = "CurriculumVitaeId")
	private CurriculumVitae curriculumVitae;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private AspNetUsers aspNetUsers;
	@Column(name = "Type", columnDefinition = "varchar(20) not null default ''")
	private String type;
	public Notification(String content, Date createDate, String status, RecruimentNews recruimentNews,
			CurriculumVitae curriculumVitae, AspNetUsers aspNetUsers, String type) {
		this.content = content;
		this.createDate = createDate;
		this.status = status;
		this.recruimentNews = recruimentNews;
		this.curriculumVitae = curriculumVitae;
		this.aspNetUsers = aspNetUsers;
		this.type = type;
	}
	public Notification() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public RecruimentNews getRecruimentNews() {
		return recruimentNews;
	}
	public void setRecruimentNews(RecruimentNews recruimentNews) {
		this.recruimentNews = recruimentNews;
	}
	public CurriculumVitae getCurriculumVitae() {
		return curriculumVitae;
	}
	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}
	public AspNetUsers getAspNetUsers() {
		return aspNetUsers;
	}
	public void setAspNetUsers(AspNetUsers aspNetUsers) {
		this.aspNetUsers = aspNetUsers;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Notification [id=" + id + ", content=" + content + ", createDate=" + createDate + ", status=" + status
				+ ", type=" + type + "]";
	}
	
}
