package com.projectakhir.betamart.Data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("role")
	private int role;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

	@SerializedName("username")
	private String username;

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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}