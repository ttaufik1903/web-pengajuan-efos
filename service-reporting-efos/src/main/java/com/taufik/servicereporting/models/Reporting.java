package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

public class Reporting {

	@NotBlank(message = "tgl_awal is mandatory")
	private String tgl_awal;
	@NotBlank(message = "tgl_akhir is mandatory")
	private String tgl_akhir;
	@NotBlank(message = "Cabang is mandatory")
	private String cab;

	public String getCab() {
		return cab;
	}

	public void setCab(String cab) {
		this.cab = cab;
	}

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
		return "Reporting [tgl_awal=" + tgl_awal + ", tgl_akhir=" + tgl_akhir + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
