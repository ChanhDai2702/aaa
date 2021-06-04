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
@Table(name = "SkillDetail")
public class SkillDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SkillDetailId")
	private int id;
	@Column(name = "SkillName", length = 60)
	private String skillName;
	@Column(name = "SkillRating", length = 10)
	private String skillRating;
	@Column(name = "typeofSkill", length = 60)
	private String typeofSkill;
	@ManyToOne
	@JoinColumn(name = "CurriculumVitaeDetailId", nullable = false)
	private CurriculumVitaeDetail curriculumVitaeDetail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getSkillRating() {
		return skillRating;
	}
	public void setSkillRating(String skillRating) {
		this.skillRating = skillRating;
	}
	public CurriculumVitaeDetail getCurriculumVitaeDetail() {
		return curriculumVitaeDetail;
	}
	public void setCurriculumVitaeDetail(CurriculumVitaeDetail curriculumVitaeDetail) {
		this.curriculumVitaeDetail = curriculumVitaeDetail;
	}
	
	public String getTypeofSkill() {
		return typeofSkill;
	}
	public void setTypeofSkill(String typeofSkill) {
		this.typeofSkill = typeofSkill;
	}
	public SkillDetail() {
		super();
	}
	
	public SkillDetail(String skillName, String skillRating) {
		super();
		this.skillName = skillName;
		this.skillRating = skillRating;
	}
	
	public SkillDetail(String skillName, String skillRating, String typeofSkill) {
		super();
		this.skillName = skillName;
		this.skillRating = skillRating;
		this.typeofSkill = typeofSkill;
	}
	@Override
	public String toString() {
		return "SkillDetail [skillName=" + skillName + ", skillRating=" + skillRating + ", typeofSkill=" + typeofSkill
				+ "]";
	}
}
