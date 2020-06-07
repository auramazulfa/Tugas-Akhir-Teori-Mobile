package com.aurama.sholatyuk.model.shalat.jadwalsholat;

import com.google.gson.annotations.SerializedName;

public class JadwalSholatModel {

	@SerializedName("data")
	private DataModel dataModel;

	@SerializedName("status")
	private String status;

	public void setDataModel(DataModel dataModel){
		this.dataModel = dataModel;
	}

	public DataModel getDataModel(){
		return dataModel;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Jadwal{" + 
			"data = '" + dataModel + '\'' +
			",status = '" + status + '\'' + 
			"}";
		}
}