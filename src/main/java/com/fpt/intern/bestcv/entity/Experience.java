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
@Table(name = "Experience")
public class Experience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "Description", columnDefinition = "text")
	private String description;
	@Column(name = "StartDate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@Column(name = "FinishDate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finishDate;
	@Column(name = "CompanyName", length = 60, nullable = false)
	private String companyName;
	@Column(name = "Position", length = 40, nullable = false)
	private String position;
	@ManyToOne
	@JoinColumn(name = "CurriculumVitaeDetailId", nullable = false)
	private CurriculumVitaeDetail curriculumVitaeDetail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public CurriculumVitaeDetail getCurriculumVitaeDetail() {
		return curriculumVitaeDetail;
	}
	public void setCurriculumVitaeDetail(CurriculumVitaeDetail curriculumVitaeDetail) {
		this.curriculumVitaeDetail = curriculumVitaeDetail;
	}
	public Experience() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Experience(String description, Date startDate, Date finishDate, String companyName, String position) {
		super();
		this.description = description;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.companyName = companyName;
		this.position = position;
	}
	@Override
	public String toString() {
		return "Experience [description=" + description + ", startDate=" + startDate + ", finishDate=" + finishDate
				+ ", companyName=" + companyName + ", position=" + position + "]";
	}
	
}