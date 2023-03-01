package com.training.model;

public class Member {
	// member
	private String id;
	private String name;
	private String pwd;
	private String pwdCheck;

	// get set
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdCheck() {
		return pwdCheck;
	}

	public void setPwdCheck(String pwdCheck) {
		this.pwdCheck = pwdCheck;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", pwd=" + pwd + ", pwdCheck=" + pwdCheck + "]";
	}
}
