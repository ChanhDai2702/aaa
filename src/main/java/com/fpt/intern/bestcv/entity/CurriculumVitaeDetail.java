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
@Table(name = "CurriculumVitaeDetail")
public class CurriculumVitaeDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CurriculumVitaeDetailId")
	private int id;
	@Column(name = "Image", length = 255)
	private String image;
	@Column(name = "JobPosition", length = 40)
	private String jobPosition;
	@ManyToOne
	@JoinColumn(name = "CurriculumVitaeId", nullable = false)
	private CurriculumVitae curriculumVitae;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	public CurriculumVitae getCurriculumVitae() {
		return curriculumVitae;
	}
	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}
	public CurriculumVitaeDetail(String image,String jobPosition) {
		super();
		this.image = image;
		this.jobPosition = jobPosition;
	}
	public CurriculumVitaeDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CurriculumVitaeDetail [id=" + id + ", image=" + image + ", jobPosition=" + jobPosition + "]";
	}
	
}
