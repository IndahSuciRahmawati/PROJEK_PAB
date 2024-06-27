package com.projectakhir.betamart.Data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetPesananResponse{

	@SerializedName("data")
	private List<PesananItem> data;

	@SerializedName("status")
	private String status;

	public void setData(List<PesananItem> data){
		this.data = data;
	}

	public List<PesananItem> getData(){
		return data;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}