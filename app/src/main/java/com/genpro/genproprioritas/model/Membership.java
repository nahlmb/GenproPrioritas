package com.genpro.genproprioritas.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Membership{

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public class DataItem{

		@SerializedName("nama_depan")
		private String namaDepan;

		@SerializedName("user_id")
		private String userId;

		@SerializedName("no_anggota")
		private String noAnggota;

		@SerializedName("nama_belakang")
		private String namaBelakang;

		public void setNamaDepan(String namaDepan){
			this.namaDepan = namaDepan;
		}

		public String getNamaDepan(){
			return namaDepan;
		}

		public void setUserId(String userId){
			this.userId = userId;
		}

		public String getUserId(){
			return userId;
		}

		public void setNoAnggota(String noAnggota){
			this.noAnggota = noAnggota;
		}

		public String getNoAnggota(){
			return noAnggota;
		}

		public void setNamaBelakang(String namaBelakang){
			this.namaBelakang = namaBelakang;
		}

		public String getNamaBelakang(){
			return namaBelakang;
		}
	}
}
