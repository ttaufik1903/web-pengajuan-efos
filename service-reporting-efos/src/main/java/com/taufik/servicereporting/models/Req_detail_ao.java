package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

public class Req_detail_ao {
	@NotBlank(message = "kode_referal is mandatory")
	private String kode_referal;
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
	
	public String getKode_referal() {
		return kode_referal;
	}

	public void setKode_referal(String kode_referal) {
		this.kode_referal = kode_referal;
	}

	@Override
	public String toString() {
		return "Reporting [status=" + kode_referal + ", tgl_awal=" + tgl_awal + ", tgl_akhir=" + tgl_akhir + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	

}
