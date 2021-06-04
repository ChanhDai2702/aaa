package com.fpt.intern.bestcv.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "AspNetUsers")
@EntityListeners(AuditingEntityListener.class)
public class AspNetUsers {
	@Id
	@Column(name = "Id", length = 128)
	private String id;
	@Column(name = "Email", length = 256)
	private String email;
	@Column(name = "EmailComfirmed", nullable = false)
	private boolean emailComfirmed=false;
	@Column(name = "PasswordHash", columnDefinition = "TEXT")
	private String passwordHash;
	@Column(name = "SecurityStamp", columnDefinition = "TEXT")
	private String securityStamp;
	@Column(name = "PhoneNumber", columnDefinition = "TEXT")
	private String phoneNumber;
	@Column(name = "PhoneNumberConfirmed", nullable = false)
	private boolean phoneNumberConfirmed=false;
	@Column(name = "TwoFactorEnabled", nullable = false)
	private boolean twoFactorEnabled=false;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LockoutEndDateUtc")
	private Date lockoutEndDateUtc;
	@Column(name = "LockoutEnabled", nullable = false)
	private boolean lockoutEnabled=false;
	@Column(name = "AccessFailedCount", nullable = false)
	private int accessFailedCount=0;
	@Column(name = "UserName", length = 256, nullable = false)
	private String userName;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateDate", insertable = true, updatable = true, columnDefinition = "DATETIME NOT NULL default '1900-01-01T00:00:00.000'")
	private Date createDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "AspNetUserRoles", joinColumns = {@JoinColumn(name="UserId")}, inverseJoinColumns = {@JoinColumn(name="RoleId")})
	private Set<AspNetRoles> roles;
	//
	public AspNetUsers(String id, String email, boolean emailComfirmed, String passwordHash, String securityStamp,
			String phoneNumber, boolean phoneNumberConfirmed, boolean twoFactorEnabled, Date lockoutEndDateUtc,
			boolean lockoutEnabled, int accessFailedCount, String userName, Date createDate) {
		super();
		this.id = id;
		this.email = email;
		this.emailComfirmed = emailComfirmed;
		this.passwordHash = passwordHash;
		this.securityStamp = securityStamp;
		this.phoneNumber = phoneNumber;
		this.phoneNumberConfirmed = phoneNumberConfirmed;
		this.twoFactorEnabled = twoFactorEnabled;
		this.lockoutEndDateUtc = lockoutEndDateUtc;
		this.lockoutEnabled = lockoutEnabled;
		this.accessFailedCount = accessFailedCount;
		this.userName = userName;
		this.createDate = createDate;
	}
	public AspNetUsers() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEmailComfirmed() {
		return emailComfirmed;
	}
	public void setEmailComfirmed(boolean emailComfirmed) {
		this.emailComfirmed = emailComfirmed;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getSecurityStamp() {
		return securityStamp;
	}
	public void setSecurityStamp(String securityStamp) {
		this.securityStamp = securityStamp;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isPhoneNumberConfirmed() {
		return phoneNumberConfirmed;
	}
	public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
		this.phoneNumberConfirmed = phoneNumberConfirmed;
	}
	public boolean isTwoFactorEnabled() {
		return twoFactorEnabled;
	}
	public void setTwoFactorEnabled(boolean twoFactorEnabled) {
		this.twoFactorEnabled = twoFactorEnabled;
	}
	public Date getLockoutEndDateUtc() {
		return lockoutEndDateUtc;
	}
	public void setLockoutEndDateUtc(Date lockoutEndDateUtc) {
		this.lockoutEndDateUtc = lockoutEndDateUtc;
	}
	public boolean isLockoutEnabled() {
		return lockoutEnabled;
	}
	public void setLockoutEnabled(boolean lockoutEnabled) {
		this.lockoutEnabled = lockoutEnabled;
	}
	public int getAccessFailedCount() {
		return accessFailedCount;
	}
	public void setAccessFailedCount(int accessFailedCount) {
		this.accessFailedCount = accessFailedCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Set<AspNetRoles> getRoles() {
		return roles;
	}
	public void setRoles(Set<AspNetRoles> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "AspNetUsers [id=" + id + ", email=" + email + ", emailComfirmed=" + emailComfirmed + ", passwordHash="
				+ passwordHash + ", securityStamp=" + securityStamp + ", phoneNumber=" + phoneNumber
				+ ", phoneNumberConfirmed=" + phoneNumberConfirmed + ", twoFactorEnabled=" + twoFactorEnabled
				+ ", lockoutEndDateUtc=" + lockoutEndDateUtc + ", lockoutEnabled=" + lockoutEnabled
				+ ", accessFailedCount=" + accessFailedCount + ", userName=" + userName + ", createDate=" + createDate
				+ "]";
	}
	
}
