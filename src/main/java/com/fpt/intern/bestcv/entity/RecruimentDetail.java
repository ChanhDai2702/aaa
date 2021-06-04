package com.fpt.intern.bestcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RecruimentDetail")
public class RecruimentDetail {
	@Id
	@Column(name = "RecruitmentDetailId")
	private int id;
	@Column(name = "Quantity", nullable = false)
	private int quantity; 
	@Column(name = "Position", length = 40, nullable = false)
	private String position;
	@Column(name = "Gender", length = 6, nullable = false)
	private String gender;
	@Column(name = "Salary", columnDefinition = "DECIMAL(18,2) NOT NULL")
	private double salary;
	@Column(name = "JobType",length = 20, nullable = false)
	private String jobType;
	@Column(name = "Experiences",length = 10)
	private String experiences;
	@OneToOne
	@JoinColumn(name = "RecruimentNewsId", nullable = false)
	private RecruimentNews recruimentNews;
	@ManyToOne
	@JoinColumn(name = "JopId", nullable = false)
	private Job job;
	public RecruimentDetail(int id, int quantity, String position, String gender, double salary, String jobType,
			String experiences, RecruimentNews recruimentNews, Job job) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.position = position;
		this.gender = gender;
		this.salary = salary;
		this.jobType = jobType;
		this.experiences = experiences;
		this.recruimentNews = recruimentNews;
		this.job = job;
	}
	public RecruimentDetail() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getGender() {
		return this.gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getJobType() {
		return this.jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getExperiences() {
		return experiences;
	}
	public void setExperiences(String experiences) {
		this.experiences = experiences;
	}
	public RecruimentNews getRecruimentNews() {
		return recruimentNews;
	}
	public void setRecruimentNews(RecruimentNews recruimentNews) {
		this.recruimentNews = recruimentNews;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	@Override
	public String toString() {
		return "RecruimentDetail [id=" + id + ", quantity=" + quantity + ", position=" + position + ", gender=" + gender
				+ ", salary=" + salary + ", jobType=" + jobType + ", experiences=" + experiences + ", recruimentNews="
				+ recruimentNews + ", job=" + job + "]";
	}
	
}
