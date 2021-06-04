package com.fpt.intern.bestcv.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CVRecruiment_PK implements Serializable {
	private static final long serialVersionUID = 1L;
	private int recruiter;
	private int curriculumVitae;
	public int getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(int recruiter) {
		this.recruiter = recruiter;
	}
	public int getCurriculumVitae() {
		return curriculumVitae;
	}
	public void setCurriculumVitae(int curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}
	public CVRecruiment_PK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CVRecruiment_PK(int recruiter, int curriculumVitae) {
		super();
		this.recruiter = recruiter;
		this.curriculumVitae = curriculumVitae;
	}
	@Override
	public String toString() {
		return "CVRecruiment_PK [recruiter=" + recruiter + ", curriculumVitae=" + curriculumVitae + "]";
	}
	
}
