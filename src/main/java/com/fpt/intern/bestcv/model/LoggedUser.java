package com.fpt.intern.bestcv.model;

import com.fpt.intern.bestcv.entity.AspNetUsers;

public class LoggedUser {
	private Object info;
	private AspNetUsers users;
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public AspNetUsers getUsers() {
		return users;
	}
	public void setUsers(AspNetUsers users) {
		this.users = users;
	}
	public LoggedUser(Object info, AspNetUsers users) {
		super();
		this.info = info;
		this.users = users;
	}
	public LoggedUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LoggedUser [info=" + info + ", users=" + users + "]";
	}
	
}
