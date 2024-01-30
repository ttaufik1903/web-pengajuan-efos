package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

public class Req_qaca_cab_detail {
	@NotBlank(message = "status_review is mandatory")
	private String status_review;
	@NotBlank(message = "tgl_awal is mandatory")
	private String tgl_awal;
	@NotBlank(message = "tgl_akhir is mandatory")
	private String tgl_akhir;
	@NotBlank(message = "kode_cabang is mandatory")
	private String kode_cabang;

	public String getTgl_awal() {
		return tgl_awal;
	}

	public void setTgl_awal(String tgl_awal) {
		this.tgl_awal = tgl_awal;
	}

	public String getTgl_akhir() {
		return tgl_akhir;
	}

	public void setTgl_akhir(String tgl_akhir) {
		this.tgl_akhir = tgl_akhir;
	}
	

	@Override
	public String toString() {
		return "Reporting [status=" + status_review + ", tgl_awal=" + tgl_awal + ", tgl_akhir=" + tgl_akhir + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getStatus_review() {
		return status_review;
	}

	public void setStatus_review(String status_review) {
		this.status_review = status_review;
	}

	public String getKode_cabang() {
		return kode_cabang;
	}

	public void setKode_cabang(String kode_cabang) {
		this.kode_cabang = kode_cabang;
	}


	

}
