package com.taufik.servicereporting.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "data_prospek")
public class Data_prospek implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ktp;
	private String nama;
	private String npwp;
	private String status_pernikahan;
	private String tmp_lahir;
	private LocalDate tgl_lahir;
	private Integer kelamin;
	private String alamat;
	private Integer cab;
	private LocalDateTime datepost_open;
	private String status;
	private String no_telp;
	private String email;
	private String tujuan_pembiayaan;
	private String no_tiket;
	private String keterangan;
	private Double plafon_pengajuan;
	private Integer tenor_pengajuan;
	private LocalDateTime calling_date;
	private String calling_by;
	private LocalDateTime proses_date;
	private String proses_by;
	private String jenis_pembiayaan;
	private Integer id_jenis_pembiayaan;
	private Double penghasilan;
	private String pekerjaan;

	// PARENT PAR STATUS
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "status_pernikahan", insertable = false, updatable = false)
		private Par_status par_status;

		@JsonProperty("desc_status_perkawinan")
		public String getPar_status() {
			if (par_status == null) {
				return "";
			} else {
				return par_status.getName();
			}
		}
		
		// PARENT PAR KELAMIN
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "kelamin", insertable = false, updatable = false)
		private Par_kelamin par_kelamin;

		@JsonProperty("desc_kelamin")
		public String getPar_kelamin() {
			if (par_kelamin == null) {
				return "";
			} else {
				return par_kelamin.getName();
			}
		}
		
	public String getKtp() {
		return ktp;
	}
	public void setKtp(String ktp) {
		this.ktp = ktp;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}
	public String getStatus_pernikahan() {
		return status_pernikahan;
	}
	public void setStatus_pernikahan(String status_pernikahan) {
		this.status_pernikahan = status_pernikahan;
	}
	public String getTmp_lahir() {
		return tmp_lahir;
	}
	public void setTmp_lahir(String tmp_lahir) {
		this.tmp_lahir = tmp_lahir;
	}
	public LocalDate getTgl_lahir() {
		return tgl_lahir;
	}
	public void setTgl_lahir(LocalDate tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}
	public Integer getKelamin() {
		return kelamin;
	}
	public void setKelamin(Integer kelamin) {
		this.kelamin = kelamin;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public Integer getCab() {
		return cab;
	}
	public void setCab(Integer cab) {
		this.cab = cab;
	}
	public LocalDateTime getDatepost_open() {
		return datepost_open;
	}
	public void setDatepost_open(LocalDateTime datepost_open) {
		this.datepost_open = datepost_open;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getTujuan_pembiayaan() {
		return tujuan_pembiayaan;
	}
	public void setTujuan_pembiayaan(String tujuan_pembiayaan) {
		this.tujuan_pembiayaan = tujuan_pembiayaan;
	}
	public String getNo_tiket() {
		return no_tiket;
	}
	public void setNo_tiket(String no_tiket) {
		this.no_tiket = no_tiket;
	}
	public Double getPlafon_pengajuan() {
		return plafon_pengajuan;
	}
	public void setPlafon_pengajuan(Double plafon_pengajuan) {
		this.plafon_pengajuan = plafon_pengajuan;
	}
	public Integer getTenor_pengajuan() {
		return tenor_pengajuan;
	}
	public void setTenor_pengajuan(Integer tenor_pengajuan) {
		this.tenor_pengajuan = tenor_pengajuan;
	}

	public LocalDateTime getCalling_date() {
		return calling_date;
	}

	public void setCalling_date(LocalDateTime calling_date) {
		this.calling_date = calling_date;
	}

	public String getCalling_by() {
		return calling_by;
	}

	public void setCalling_by(String calling_by) {
		this.calling_by = calling_by;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public LocalDateTime getProses_date() {
		return proses_date;
	}

	public void setProses_date(LocalDateTime proses_date) {
		this.proses_date = proses_date;
	}

	public String getProses_by() {
		return proses_by;
	}

	public void setProses_by(String proses_by) {
		this.proses_by = proses_by;
	}

	public String getJenis_pembiayaan() {
		return jenis_pembiayaan;
	}

	public void setJenis_pembiayaan(String jenis_pembiayaan) {
		this.jenis_pembiayaan = jenis_pembiayaan;
	}

	public Integer getId_jenis_pembiayaan() {
		return id_jenis_pembiayaan;
	}

	public void setId_jenis_pembiayaan(Integer id_jenis_pembiayaan) {
		this.id_jenis_pembiayaan = id_jenis_pembiayaan;
	}

	public Double getPenghasilan() {
		return penghasilan;
	}

	public void setPenghasilan(Double penghasilan) {
		this.penghasilan = penghasilan;
	}

	public String getPekerjaan() {
		return pekerjaan;
	}

	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}

}
