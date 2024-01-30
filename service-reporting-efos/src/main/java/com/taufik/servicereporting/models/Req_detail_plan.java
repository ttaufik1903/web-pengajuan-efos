package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

public class Req_detail_plan {
	@NotBlank(message = "kode_plan is mandatory")
	private String kode_plan;
	@NotBlank(message = "tgl_awal is mandatory")
	private String tgl_awal;
	@NotBlank(message = "tgl_akhir is mandatory")
	private String tgl_akhir;



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
		return "Reporting [status=" + kode_plan + ", tgl_awal=" + tgl_awal + ", tgl_akhir=" + tgl_akhir + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getKode_plan() {
		return kode_plan;
	}

	public void setKode_plan(String kode_plan) {
		this.kode_plan = kode_plan;
	}


}
