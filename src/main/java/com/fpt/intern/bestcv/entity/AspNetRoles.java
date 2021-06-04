package com.fpt.intern.bestcv.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AspNetRoles")
public class AspNetRoles {
	@Id
	@Column(name = "Id", length = 128)
	private String id;
	@Column(name = "Name", length = 256, nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Set<AspNetUsers> users;
	
	public AspNetRoles(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public AspNetRoles() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<AspNetUsers> getUsers() {
		return users;
	}
	public void setUsers(Set<AspNetUsers> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return name;
	}
	
	
}
