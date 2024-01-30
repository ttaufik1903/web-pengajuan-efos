package net.javaguides.springboot.model;

import java.math.BigDecimal;

public class Kalkulator {

	private String harga_beli;
	private String uang_muka;
	private Integer jangka_waktu;
	private String periode;
	private String margin;
	private String angsuran;

	public String getHarga_beli() {
		return harga_beli;
	}

	public void setHarga_beli(String harga_beli) {
		this.harga_beli = harga_beli;
	}

	public String getUang_muka() {
		return uang_muka;
	}

	public void setUang_muka(String uang_muka) {
		this.uang_muka = uang_muka;
	}

	public Integer getJangka_waktu() {
		return jangka_waktu;
	}

	public void setJangka_waktu(Integer jangka_waktu) {
		this.jangka_waktu = jangka_waktu;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public String getAngsuran() {
		return angsuran;
	}

	public void setAngsuran(String angsuran) {
		this.angsuran = angsuran;
	}

}
