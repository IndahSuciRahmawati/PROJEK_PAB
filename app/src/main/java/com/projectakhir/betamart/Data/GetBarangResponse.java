package com.projectakhir.betamart.Data;

import java.util.List;

public class GetBarangResponse{
	private List<DataItem> data;
	private String status;

	public List<DataItem> getData(){
		return data;
	}

	public String getStatus(){
		return status;
	}
}