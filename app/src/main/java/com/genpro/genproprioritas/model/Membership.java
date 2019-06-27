package com.genpro.genproprioritas.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Membership implements Serializable {

    @SerializedName("no_anggota")
    @Expose
    private String noAnggota;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("nama_depan")
    @Expose
    private String namaDepan;
    @SerializedName("nama_belakang")
    @Expose
    private String namaBelakang;

    private List<Membership> data;

    private final static long serialVersionUID = -5301893988127891081L;

    public List<Membership> getData() {
        return data;
    }

    public String getNoAnggota() {
        return noAnggota;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

}

