package com.rabbai.serviceusulan.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "par_cabang")
public class Par_cabang implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String cabang;
	private String alamat;
	private String kota;
	private String inisial;
	private String jenis_cab;
	private String no_surat_direksi;
	private LocalDate no_surat_direksi_date;
	private String nm_notaris;
	private String kota_notaris;
	private String cab_induk;
	private String is_pinbag;
	private Double limit_konsumtif;
	private Double limit_produktif;
	private String level_approve;
	private String no_telp;
	private String email;
	private String no_akta;
	private String tgl_akta;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCabang() {
		return cabang;
	}
	public void setCabang(String cabang) {
		this.cabang = cabang;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getKota() {
		return kota;
	}
	public void setKota(String kota) {
		this.kota = kota;
	}

	public String getJenis_cab() {
		return jenis_cab;
	}
	public void setJenis_cab(String jenis_cab) {
		this.jenis_cab = jenis_cab;
	}
	public String getNo_surat_direksi() {
		return no_surat_direksi;
	}
	public void setNo_surat_direksi(String no_surat_direksi) {
		this.no_surat_direksi = no_surat_direksi;
	}
	public LocalDate getNo_surat_direksi_date() {
		return no_surat_direksi_date;
	}
	public void setNo_surat_direksi_date(LocalDate no_surat_direksi_date) {
		this.no_surat_direksi_date = no_surat_direksi_date;
	}
	public String getNm_notaris() {
		return nm_notaris;
	}
	public void setNm_notaris(String nm_notaris) {
		this.nm_notaris = nm_notaris;
	}
	public String getKota_notaris() {
		return kota_notaris;
	}
	public void setKota_notaris(String kota_notaris) {
		this.kota_notaris = kota_notaris;
	}
	public String getCab_induk() {
		return cab_induk;
	}
	public void setCab_induk(String cab_induk) {
		this.cab_induk = cab_induk;
	}
	public String getIs_pinbag() {
		return is_pinbag;
	}
	public void setIs_pinbag(String is_pinbag) {
		this.is_pinbag = is_pinbag;
	}
	public Double getLimit_konsumtif() {
		return limit_konsumtif;
	}
	public void setLimit_konsumtif(Double limit_konsumtif) {
		this.limit_konsumtif = limit_konsumtif;
	}
	public Double getLimit_produktif() {
		return limit_produktif;
	}
	public void setLimit_produktif(Double limit_produktif) {
		this.limit_produktif = limit_produktif;
	}
	public String getLevel_approve() {
		return level_approve;
	}
	public void setLevel_approve(String level_approve) {
		this.level_approve = level_approve;
	}
	public String getInisial() {
		return inisial;
	}
	public void setInisial(String inisial) {
		this.inisial = inisial;
	}
	public String getNo_telp() {
		return no_telp;
	}
	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNo_akta() {
		return no_akta;
	}
	public void setNo_akta(String no_akta) {
		this.no_akta = no_akta;
	}
	public String getTgl_akta() {
		return tgl_akta;
	}
	public void setTgl_akta(String tgl_akta) {
		this.tgl_akta = tgl_akta;
	}

	
	
}
