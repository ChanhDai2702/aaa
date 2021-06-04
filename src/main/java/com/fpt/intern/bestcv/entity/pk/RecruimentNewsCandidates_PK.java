package com.fpt.intern.bestcv.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RecruimentNewsCandidates_PK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int recruimentNews;
	private int candidate;
	public RecruimentNewsCandidates_PK(int recruimentNews, int candidate) {
		super();
		this.recruimentNews = recruimentNews;
		this.candidate = candidate;
	}
	public RecruimentNewsCandidates_PK() {
		super();
	}
	public int getRecruimentNews() {
		return recruimentNews;
	}
	public void setRecruimentNews(int recruimentNews) {
		this.recruimentNews = recruimentNews;
	}
	public int getCandidate() {
		return candidate;
	}
	public void setCandidate(int candidate) {
		this.candidate = candidate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + candidate;
		result = prime * result + recruimentNews;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecruimentNewsCandidates_PK other = (RecruimentNewsCandidates_PK) obj;
		if (candidate != other.candidate)
			return false;
		if (recruimentNews != other.recruimentNews)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RecruimentNewsCandidates_PK [recruimentNews=" + recruimentNews + ", candidate=" + candidate + "]";
	}
	
}
