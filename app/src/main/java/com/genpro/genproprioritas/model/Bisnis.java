package com.genpro.genproprioritas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Bisnis implements Serializable
{

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<BisnisData> data = null;
    private final static long serialVersionUID = 2894104676950267967L;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BisnisData> getData() {
        return data;
    }

    public void setData(List<BisnisData> data) {
        this.data = data;
    }

    public class BisnisData implements Serializable
    {

        @SerializedName("id_bisnis_info")
        @Expose
        private String idBisnisInfo;
        @SerializedName("nm_bisnis_lain")
        @Expose
        private String nmBisnisLain;
        @SerializedName("nm_usaha")
        @Expose
        private String nmUsaha;
        @SerializedName("merk")
        @Expose
        private String merk;
        @SerializedName("tgl_terdaftar")
        @Expose
        private String tglTerdaftar;
        @SerializedName("jml_karyawan")
        @Expose
        private String jmlKaryawan;
        @SerializedName("jml_cabang")
        @Expose
        private String jmlCabang;
        @SerializedName("omset_tahunan")
        @Expose
        private String omsetTahunan;
        @SerializedName("no_tlp")
        @Expose
        private String noTlp;
        @SerializedName("facebook")
        @Expose
        private String facebook;
        @SerializedName("instagram")
        @Expose
        private String instagram;
        @SerializedName("nm_usaha_lain")
        @Expose
        private String nmUsahaLain;
        @SerializedName("tentang_usaha")
        @Expose
        private String tentangUsaha;
        private final static long serialVersionUID = -4230851234545639150L;

        public String getIdBisnisInfo() {
            return idBisnisInfo;
        }

        public void setIdBisnisInfo(String idBisnisInfo) {
            this.idBisnisInfo = idBisnisInfo;
        }

        public String getNmBisnisLain() {
            return nmBisnisLain;
        }

        public void setNmBisnisLain(String nmBisnisLain) {
            this.nmBisnisLain = nmBisnisLain;
        }

        public String getNmUsaha() {
            return nmUsaha;
        }

        public void setNmUsaha(String nmUsaha) {
            this.nmUsaha = nmUsaha;
        }

        public String getMerk() {
            return merk;
        }

        public void setMerk(String merk) {
            this.merk = merk;
        }

        public String getTglTerdaftar() {
            return tglTerdaftar;
        }

        public void setTglTerdaftar(String tglTerdaftar) {
            this.tglTerdaftar = tglTerdaftar;
        }

        public String getJmlKaryawan() {
            return jmlKaryawan;
        }

        public void setJmlKaryawan(String jmlKaryawan) {
            this.jmlKaryawan = jmlKaryawan;
        }

        public String getJmlCabang() {
            return jmlCabang;
        }

        public void setJmlCabang(String jmlCabang) {
            this.jmlCabang = jmlCabang;
        }

        public String getOmsetTahunan() {
            return omsetTahunan;
        }

        public void setOmsetTahunan(String omsetTahunan) {
            this.omsetTahunan = omsetTahunan;
        }

        public String getNoTlp() {
            return noTlp;
        }

        public void setNoTlp(String noTlp) {
            this.noTlp = noTlp;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getNmUsahaLain() {
            return nmUsahaLain;
        }

        public void setNmUsahaLain(String nmUsahaLain) {
            this.nmUsahaLain = nmUsahaLain;
        }

        public String getTentangUsaha() {
            return tentangUsaha;
        }

        public void setTentangUsaha(String tentangUsaha) {
            this.tentangUsaha = tentangUsaha;
        }

    }

}