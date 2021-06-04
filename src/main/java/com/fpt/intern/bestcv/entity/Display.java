package com.fpt.intern.bestcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Display")
public class Display {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DisplayId")
	private int id;
	
	@Column(name = "ImageBanner", length = 160)
	private String imageBanner;
	
	@Column(name = "FontSize", nullable = false)
	private int fontSize;
	
	@Column(name = "Font", length = 40)
	private String font;
	
	@Column(name = "DisplayName", length = 100)
	private String displayName;
	
	@Column(name = "ImageReview", length = 4000)
	private String imageReview;
	
	@Column(name = "NavColor", length = 20)
	private String navColor;
	
	@Column(name = "BackgroundColor", length = 20)
	private String backgroundColor;
	
	@Column(name = "FontColor", length = 20)
	private String fontColor;
	
	@Column(name = "IsActive", columnDefinition = "bit not null default 0")
	private Boolean isActive;

	public Display() {
		super();
	}

	public Display(String imageBanner, int fontSize, String font, String displayName,
			String imageReview, String navColor, String backgroundColor, String fontColor, Boolean isActive) {
		super();
		this.imageBanner = imageBanner;
		this.fontSize = fontSize;
		this.font = font;
		this.displayName = displayName;
		this.imageReview = imageReview;
		this.navColor = navColor;
		this.backgroundColor = backgroundColor;
		this.fontColor = fontColor;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageBanner() {
		return imageBanner;
	}

	public void setImageBanner(String imageBanner) {
		this.imageBanner = imageBanner;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getImageReview() {
		return imageReview;
	}

	public void setImageReview(String imageReview) {
		this.imageReview = imageReview;
	}

	public String getNavColor() {
		return navColor;
	}

	public void setNavColor(String navColor) {
		this.navColor = navColor;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Display [displayId=" + id + ", imageBanner=" + imageBanner + ", fontSize=" + fontSize + ", font="
				+ font + ", displayName=" + displayName + ", imageReview=" + imageReview + ", navColor=" + navColor
				+ ", backgroundColor=" + backgroundColor + ", fontColor=" + fontColor + ", isActive=" + isActive + "]";
	}
}
