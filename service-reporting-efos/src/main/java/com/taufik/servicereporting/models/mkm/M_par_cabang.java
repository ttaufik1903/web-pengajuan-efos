package com.taufik.servicereporting.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.taufik.servicereporting.models.Par_cabanginduk;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "M_par_cabang")
@Table(name = "par_cabang")
public class M_par_cabang implements Serializable {

	private static final long serialVersionUID = -2517851941873251699L;
	@Id
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
	private String input_by;
	private LocalDateTime input_date;
	private String update_by;
	private LocalDateTime update_date;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "par_cabang")
	private List<M_data_loan> loans = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "id", referencedColumnName = "cab_induk")
	private Par_cabanginduk cabanginduk;

	public Par_cabanginduk getCabanginduk() {
		return cabanginduk;
	}

	public void setCabanginduk(Par_cabanginduk cabanginduk) {
		this.cabanginduk = cabanginduk;
	}

	public List<M_data_loan> getLoans() {
		return loans;
	}

	public void setLoans(List<M_data_loan> loans) {
		this.loans = loans;
	}

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

	public String getInput_by() {
		return input_by;
	}

	public void setInput_by(String input_by) {
		this.input_by = input_by;
	}

	public LocalDateTime getInput_date() {
		return input_date;
	}

	public void setInput_date(LocalDateTime input_date) {
		this.input_date = input_date;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public LocalDateTime getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}

	public String getInisial() {
		return inisial;
	}

	public void setInisial(String inisial) {
		this.inisial = inisial;
	}

}
