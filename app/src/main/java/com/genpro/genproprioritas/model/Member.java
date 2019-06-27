package com.genpro.genproprioritas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Member{

	@SerializedName("nama_depan")
	private String namaDepan;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("no_anggota")
	private String noAnggota;

	@SerializedName("nama_belakang")
	private String namaBelakang;

	private List<Member> members;

	public List<Member> getMembers() {
		return members;
	}

	public String getNamaDepan(){
		return namaDepan;
	}

	public String getUserId(){
		return userId;
	}

	public String getNoAnggota(){
		return noAnggota;
	}

	public String getNamaBelakang(){
		return namaBelakang;
	}
}