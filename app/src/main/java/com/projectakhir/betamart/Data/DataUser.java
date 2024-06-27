package com.projectakhir.betamart.Data;

import com.google.gson.annotations.SerializedName;

public class DataUser {

	@SerializedName("role")
	private int role;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("password")
	private String password;

	public void setRole(int role){
		this.role = role;
	}

	public int getRole(){
		return role;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}