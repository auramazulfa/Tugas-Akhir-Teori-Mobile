package com.aurama.sholatyuk.model.shalat.listkota;

import com.google.gson.annotations.SerializedName;

public class Query{

	@SerializedName("format")
	private String format;

	public void setFormat(String format){
		this.format = format;
	}

	public String getFormat(){
		return format;
	}

	@Override
 	public String toString(){
		return 
			"Query{" + 
			"format = '" + format + '\'' + 
			"}";
		}
}