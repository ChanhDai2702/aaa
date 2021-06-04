package com.fpt.intern.bestcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fpt.intern.bestcv.entity.pk.RecruimentNewsCandidates_PK;

@Entity
@Table(name = "RecruimentNewsCandidates")
@IdClass(RecruimentNewsCandidates_PK.class)
public class RecruimentNewsCandidates {
	@Id
	@ManyToOne
	@JoinColumn(name = "RecruimentNewsId")
	private RecruimentNews recruimentNews;
	@Id
	@ManyToOne
	@JoinColumn(name = "CandidateId")
	private Candidate candidate;
	@Column(name = "[Like]", nullable = false)
	private boolean like=false;
	public RecruimentNewsCandidates(RecruimentNews recruimentNews, Candidate candidate, boolean like) {
		super();
		this.recruimentNews = recruimentNews;
		this.candidate = candidate;
		this.like = like;
	}
	public RecruimentNewsCandidates() {
		super();
	}
	public RecruimentNews getRecruimentNews() {
		return recruimentNews;
	}
	public void setRecruimentNews(RecruimentNews recruimentNews) {
		this.recruimentNews = recruimentNews;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "RecruimentNewsCandidates [recruimentNews=" + recruimentNews + ", candidate=" + candidate + ", like="
				+ like + "]";
	}
	
}
