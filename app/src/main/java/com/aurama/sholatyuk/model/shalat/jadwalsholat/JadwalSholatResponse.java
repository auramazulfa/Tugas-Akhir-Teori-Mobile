package com.aurama.sholatyuk.model.shalat.jadwalsholat;

import com.google.gson.annotations.SerializedName;

public class JadwalSholatResponse {

	@SerializedName("jadwal")
	private JadwalSholatModel jadwalSholatModel;

	@SerializedName("query")
	private Query query;

	@SerializedName("status")
	private String status;

	public void setJadwalSholatModel(JadwalSholatModel jadwalSholatModel){
		this.jadwalSholatModel = jadwalSholatModel;
	}

	public JadwalSholatModel getJadwalSholatModel(){
		return jadwalSholatModel;
	}

	public void setQuery(Query query){
		this.query = query;
	}

	public Query getQuery(){
		return query;
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
			"Response{" + 
			"jadwal = '" + jadwalSholatModel + '\'' +
			",query = '" + query + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}