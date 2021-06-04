package com.fpt.intern.bestcv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fpt.intern.bestcv.entity.pk.CVRecruiment_PK;

@Entity
@Table(name = "CVRecruiment")
@IdClass(CVRecruiment_PK.class)
public class CVRecruiment {
	@Id
	@ManyToOne
	@JoinColumn(name = "CurriculumVitae")
	private CurriculumVitae curriculumVitae;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "RecruiterId")
	private Recruiter recruiter;
	@Column(name = "[Like]", nullable = false)
	private boolean like=false;
	@Column(name = "Apply", nullable = false)
	private boolean apply=false;
	@Column(name = "Approved", columnDefinition = "bit not null default 0")
	private boolean approved=false;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ApprovedDate")
	private Date approvedDate;
	public CurriculumVitae getCurriculumVitae() {
		return curriculumVitae;
	}
	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}
	public Recruiter getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public boolean isApply() {
		return apply;
	}
	public void setApply(boolean apply) {
		this.apply = apply;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public CVRecruiment(CurriculumVitae curriculumVitae, Recruiter recruiter, boolean like, boolean apply,
			boolean approved, Date approvedDate) {
		super();
		this.curriculumVitae = curriculumVitae;
		this.recruiter = recruiter;
		this.like = like;
		this.apply = apply;
		this.approved = approved;
		this.approvedDate = approvedDate;
	}
	public CVRecruiment() {
		super();
	}
	@Override
	public String toString() {
		return "CVRecruiment [curriculumVitae=" + curriculumVitae + ", recruiter=" + recruiter + ", like=" + like
				+ ", apply=" + apply + ", approved=" + approved + ", approvedDate=" + approvedDate + "]";
	}
	
}
