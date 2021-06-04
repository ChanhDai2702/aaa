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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Education")
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EducationId")
	private int id;
	@Column(name = "Major", nullable = false)
	private String major;
	@Column(name = "StartDate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@Column(name = "FinishDate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finishDate;
	@Column(name = "Description", columnDefinition = "TEXT")
	private String description;
	@ManyToOne
	@JoinColumn(name = "CurriculumVitaeDetailId", nullable = false)
	private CurriculumVitaeDetail curriculumVitaeDetail;
	@Column(name = "SchoolName", columnDefinition = "varchar(160) not null default ''")
	private String schoolName;
	@Column(name = "GPA", columnDefinition = "DECIMAL(18,2) not null default 0")
	private double gpa;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	public CurriculumVitaeDetail getCurriculumVitaeDetail() {
		return curriculumVitaeDetail;
	}
	public void setCurriculumVitaeDetail(CurriculumVitaeDetail curriculumVitaeDetail) {
		this.curriculumVitaeDetail = curriculumVitaeDetail;
	}
	public Education() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Education(String major, Date startDate, Date finishDate, String description, String schoolName, double gpa) {
		super();
		this.major = major;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.description = description;
		this.schoolName = schoolName;
		this.gpa = gpa;
	}
	@Override
	public String toString() {
		return "Education [id=" + id + ", major=" + major + ", startDate=" + startDate + ", finishDate=" + finishDate
				+ ", description=" + description + ", schoolName=" + schoolName + ", gpa=" + gpa + "]";
	}
	
}
