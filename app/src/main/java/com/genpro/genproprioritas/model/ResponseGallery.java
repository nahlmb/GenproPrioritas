package com.genpro.genproprioritas.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGallery{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("success")
	private boolean success;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public class DataItem{

		@SerializedName("date")
		private String date;

		@SerializedName("judul")
		private String judul;

		@SerializedName("url")
		private String url;

		public void setDate(String date){
			this.date = date;
		}

		public String getDate(){
			return date;
		}

		public void setJudul(String judul){
			this.judul = judul;
		}

		public String getJudul(){
			return judul;
		}

		public void setUrl(String url){
			this.url = url;
		}

		public String getUrl(){
			return url;
		}
	}
}