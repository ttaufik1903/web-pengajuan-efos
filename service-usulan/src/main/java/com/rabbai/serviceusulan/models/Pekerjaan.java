package com.rabbai.serviceusulan.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "data_pekerjaan")
public class Pekerjaan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String id_debitur;
	private String id_loan;
	private String profesi;
	private String nama_instansi;
	private String status_perusahaan;
	private String jabatan;
	private String bidang_usaha;
	private Integer tahun_bekerja;
	private Double gaji;
	private Double tunjangan;
	private Double potongan;
	private Double netto;
	private String share;
	private String kolektif;
	private String kode_dinas;
	private String kode_sub_dinas;
	private String kode_sub_sub_dinas;
	private String status;
	private Integer cab_open;
	private String userid_open;
	private LocalDateTime datepost_open;
	private Integer cab_edit;
	private String userid_edit;
	private LocalDateTime datepost_edit;
	private String spv_edit;
	private String no_telp;
	private Double b_operasional;
	private Double b_gaji;
	
	// PARENT PROFESI
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesi", insertable = false, updatable = false)
	private Par_profesi par_profesi;

	@JsonProperty("nama_profesi")
	public String getPar_profesi() {
		if (par_profesi == null) {
			return "";
		} else {
			return par_profesi.getName();
		}
	}

	// PARENT BIDANG USAHA
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bidang_usaha", insertable = false, updatable = false)
	private Par_bidang_usaha Par_bidang_usaha;

	@JsonProperty("nama_bidang_usaha")
	public String getPar_bidang_usaha() {
		if (Par_bidang_usaha == null) {
			return "";
		} else {
			return Par_bidang_usaha.getName();
		}
	}

	// PARENT STATUS PERUSAHAAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_perusahaan", insertable = false, updatable = false)
	private Par_status_perusahaan par_status_perusahaan;

	@JsonProperty("nama_status_perusahaan")
	public String getPar_status_perusahaan() {
		if (par_status_perusahaan == null) {
			return "";
		} else {
			return par_status_perusahaan.getName();
		}
	}

	// PARENT PAR DINAS
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "kode_dinas", referencedColumnName = "id", insertable = false, updatable = false),
			@JoinColumn(name = "cab_open", referencedColumnName = "id_cabang", insertable = false, updatable = false) })
	private Par_dinas desc_dinas;

	@JsonProperty("desc_dinas")
	public String getDesc_dinas() {
		if (desc_dinas == null) {
			return "";
		} else {
			return desc_dinas.getName();
		}
	}

	// PARENT PAR SUB DINAS
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "kode_sub_dinas", referencedColumnName = "id", insertable = false, updatable = false),
			@JoinColumn(name = "cab_open", referencedColumnName = "id_cabang", insertable = false, updatable = false) })
	private Par_sub_dinas desc_sub_dinas;

	@JsonProperty("desc_sub_dinas")
	public String getDesc_sub_dinas() {
		if (desc_sub_dinas == null) {
			return "";
		} else {
			return desc_sub_dinas.getName();
		}
	}

	// PARENT PAR SUB SUB DINAS
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "kode_sub_sub_dinas", referencedColumnName = "id", insertable = false, updatable = false),
			@JoinColumn(name = "cab_open", referencedColumnName = "id_cabang", insertable = false, updatable = false) })
	private Par_sub_sub_dinas desc_sub_sub_dinas;

	@JsonProperty("desc_sub_sub_dinas")
	public String getDesc_sub_sub_dinas() {
		if (desc_sub_sub_dinas == null) {
			return "";
		} else {
			return desc_sub_sub_dinas.getName();
		}
	}

	// DESC KOLEKTIF
		@JsonProperty("desc_kolektif")
		public String getDesc_kolektif() {
			if (kolektif.equals(null)) {
				return "";
			} else if (kolektif.equals("1")) {
				return "Non kolektif";
			} else if (kolektif.equals("2")) {
				return "Kolektif, gaji tidak melalui Bank";
			} else if (kolektif.equals("3")) {
				return "Kolektif, gaji melalui Bank";	
			} else {
				return "";
			}
		}
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getId_debitur() {
		return id_debitur;
	}

	public void setId_debitur(String id_debitur) {
		this.id_debitur = id_debitur;
	}

	public String getProfesi() {
		return profesi;
	}

	public void setProfesi(String profesi) {
		this.profesi = profesi;
	}

	public String getNama_instansi() {
		return nama_instansi;
	}

	public void setNama_instansi(String nama_instansi) {
		this.nama_instansi = nama_instansi;
	}

	public String getStatus_perusahaan() {
		return status_perusahaan;
	}

	public void setStatus_perusahaan(String status_perusahaan) {
		this.status_perusahaan = status_perusahaan;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public String getBidang_usaha() {
		return bidang_usaha;
	}

	public void setBidang_usaha(String bidang_usaha) {
		this.bidang_usaha = bidang_usaha;
	}

	public Double getGaji() {
		return gaji;
	}

	public void setGaji(Double gaji) {
		this.gaji = gaji;
	}

	public Double getTunjangan() {
		return tunjangan;
	}

	public void setTunjangan(Double tunjangan) {
		this.tunjangan = tunjangan;
	}

	public Double getPotongan() {
		return potongan;
	}

	public void setPotongan(Double potongan) {
		this.potongan = potongan;
	}

	public Double getNetto() {
		return netto;
	}

	public void setNetto(Double netto) {
		this.netto = netto;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public String getKolektif() {
		return kolektif;
	}

	public void setKolektif(String kolektif) {
		this.kolektif = kolektif;
	}

	public String getKode_dinas() {
		return kode_dinas;
	}

	public void setKode_dinas(String kode_dinas) {
		this.kode_dinas = kode_dinas;
	}

	public String getKode_sub_dinas() {
		return kode_sub_dinas;
	}

	public void setKode_sub_dinas(String kode_sub_dinas) {
		this.kode_sub_dinas = kode_sub_dinas;
	}

	public String getKode_sub_sub_dinas() {
		return kode_sub_sub_dinas;
	}

	public void setKode_sub_sub_dinas(String kode_sub_sub_dinas) {
		this.kode_sub_sub_dinas = kode_sub_sub_dinas;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCab_open() {
		return cab_open;
	}

	public void setCab_open(Integer cab_open) {
		this.cab_open = cab_open;
	}

	public String getUserid_open() {
		return userid_open;
	}

	public void setUserid_open(String userid_open) {
		this.userid_open = userid_open;
	}

	public LocalDateTime getDatepost_open() {
		return datepost_open;
	}

	public void setDatepost_open(LocalDateTime datepost_open) {
		this.datepost_open = datepost_open;
	}

	public Integer getCab_edit() {
		return cab_edit;
	}

	public void setCab_edit(Integer cab_edit) {
		this.cab_edit = cab_edit;
	}

	public String getUserid_edit() {
		return userid_edit;
	}

	public void setUserid_edit(String userid_edit) {
		this.userid_edit = userid_edit;
	}

	public LocalDateTime getDatepost_edit() {
		return datepost_edit;
	}

	public void setDatepost_edit(LocalDateTime datepost_edit) {
		this.datepost_edit = datepost_edit;
	}

	public String getSpv_edit() {
		return spv_edit;
	}

	public void setSpv_edit(String spv_edit) {
		this.spv_edit = spv_edit;
	}

	public String getId_loan() {
		return id_loan;
	}

	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}

	public String getNo_telp() {
		return no_telp;
	}

	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}

	public Integer getTahun_bekerja() {
		return tahun_bekerja;
	}

	public void setTahun_bekerja(Integer tahun_bekerja) {
		this.tahun_bekerja = tahun_bekerja;
	}

	public Double getB_gaji() {
		return b_gaji;
	}

	public void setB_gaji(Double b_gaji) {
		this.b_gaji = b_gaji;
	}

	public Double getB_operasional() {
		return b_operasional;
	}

	public void setB_operasional(Double b_operasional) {
		this.b_operasional = b_operasional;
	}

}
