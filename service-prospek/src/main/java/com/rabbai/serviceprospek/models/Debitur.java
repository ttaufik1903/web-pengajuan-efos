package com.rabbai.serviceprospek.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "data_debitur_i")
public class Debitur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String ktp;
	private String nama;
	private String golongan_deb;
	private Integer golongan_bi;
	private Integer hubungan_bank;
	private String passport;
	private String npwp;
	private Integer pendidikan;
	private Integer agama;
	private String status_pernikahan;
	private String tmp_lahir;
	private LocalDate tgl_lahir;
	private Integer kelamin;
	private Integer kebangsaan;
	private String alamat;
	private Integer kelurahan;
	private Integer kecamatan;
	private Integer dati2;
	private Integer provinsi;
	private Integer kdpos;
	private String alamat_ktp;
	private Integer kelurahan_ktp;
	private Integer kecamatan_ktp;
	private Integer dati2_ktp;
	private Integer provinsi_ktp;
	private Integer kdpos_ktp;
	private String nama_darurat;
	private String alamat_darurat;
	private String hub_darurat;
	private String telp_darurat;
	private String kdpos_darurat;
	private Integer tanggungan;
	private Integer penghasilan;
	private Integer cab_open;
	private String userid_open;
	private LocalDateTime datepost_open;
	private Integer cab_edit;
	private String userid_edit;
	private LocalDateTime datepost_edit;
	private String spv_edit;
	private String status;
	private String status_tempat_tinggal;
	private String no_telp;
	private Double biaya_tanggungan;

	// CHILD
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "debitur")
	private List<Loan> loan;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_debitur", referencedColumnName = "ktp")
	private List<Pasangan> pasangan;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_debitur", referencedColumnName = "ktp")
//    @Where(clause = "status = '1'")
	@OrderBy("datepost_open DESC")
	private List<Pekerjaan> pekerjaan;

	// PARENT PAR TEMPAT TINGGAL
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_tempat_tinggal", insertable = false, updatable = false)
	private Par_tempat_tinggal par_tempat_tinggal;

	@JsonProperty("desc_status_tempat_tinggal")
	public String getPar_tempat_tinggal() {
		if (par_tempat_tinggal == null) {
			return "";
		} else {
			return par_tempat_tinggal.getName();
		}
	}

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

	// PARENT PAR AGAMA
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agama", insertable = false, updatable = false)
	private Par_agama par_agama;

	@JsonProperty("desc_agama")
	public String getPar_agama() {
		if (par_agama == null) {
			return "";
		} else {
			return par_agama.getName();
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

	// PARENT PAR KEBANGSAAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kebangsaan", insertable = false, updatable = false)
	private Par_kebangsaan par_kebangsaan;

	@JsonProperty("desc_kebangsaan")
	public String getPar_kebangsaan() {
		if (par_kebangsaan == null) {
			return "";
		} else {
			return par_kebangsaan.getName();
		}
	}

	// PENGHASILAN DESC
	@JsonProperty("desc_penghasilan")
	public String getDesc_penghasilan() {
		if (penghasilan == null) {
			return "";
		} else if (penghasilan == 1) {
			return "TETAP";
		} else if (penghasilan == 0) {
			return "TIDAK TITAP";
		} else {
			return "";
		}
	}

	// PARENT PAR PENDIDIKAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pendidikan", insertable = false, updatable = false)
	private Par_pendidikan par_pendidikan;

	@JsonProperty("desc_pendidikan")
	public String getPar_pendidikan() {
		if (par_pendidikan == null) {
			return "";
		} else {
			return par_pendidikan.getName();
		}
	}

	// DESC GOL BI
	@JsonProperty("desc_golongan_bi")
	public String getDesc_golongan_bi() {
		if (golongan_bi == null) {
			return "";
		} else if (golongan_bi == 6) {
			return "INDIVIDUAL TANPA NPWP";
		} else if (golongan_bi == 9) {
			return "INDIVIDUAL DENGAN NPWP";
		} else {
			return "";
		}
	}

	// DESC HUBUNGAN BANK
	@JsonProperty("desc_hubungan_bank")
	public String getDesc_hubungan_bank() {
		if (hubungan_bank == null) {
			return "";
		} else if (hubungan_bank == 1) {
			return "TERKAIT DGN BANK";
		} else if (hubungan_bank == 2) {
			return "TIDAK TERKAIT DGN BANK";
		} else {
			return "";
		}
	}

	// PARENT PAR PROVINSI
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provinsi", insertable = false, updatable = false)
	private Par_provinsi desc_provinsi;

	@JsonProperty("desc_provinsi")
	public String getDesc_provinsi() {
		if (desc_provinsi == null) {
			return "";
		} else {
			return desc_provinsi.getName();
		}
	}

	// PARENT PAR PROVINSI KTP
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provinsi_ktp", insertable = false, updatable = false)
	private Par_provinsi desc_provinsi_ktp;

	@JsonProperty("desc_provinsi_ktp")
	public String getDesc_provinsi_ktp() {
		if (desc_provinsi_ktp == null) {
			return "";
		} else {
			return desc_provinsi_ktp.getName();
		}
	}

	// PARENT PAR DATI 2
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dati2", insertable = false, updatable = false)
	private Par_dati2 desc_dati2;

	@JsonProperty("desc_dati2")
	public String getDesc_dati2() {
		if (desc_dati2 == null) {
			return "";
		} else {
			return desc_dati2.getName();
		}
	}

	// PARENT PAR DATI 2 KTP
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dati2", insertable = false, updatable = false)
	private Par_dati2 desc_dati2_ktp;

	@JsonProperty("desc_dati2_ktp")
	public String getDesc_dati2_ktp() {
		if (desc_dati2_ktp == null) {
			return "";
		} else {
			return desc_dati2_ktp.getName();
		}
	}

	// PARENT PAR KECAMATAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kecamatan", insertable = false, updatable = false)
	private Par_kec desc_kecamatan;

	@JsonProperty("desc_kecamatan")
	public String getDesc_kecamatan() {
		if (desc_kecamatan == null) {
			return "";
		} else {
			return desc_kecamatan.getName();
		}
	}

	// PARENT PAR KECAMATAN KTP
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kecamatan_ktp", insertable = false, updatable = false)
	private Par_kec desc_kecamatan_ktp;

	@JsonProperty("desc_kecamatan_ktp")
	public String getDesc_kecamatan_ktp() {
		if (desc_kecamatan_ktp == null) {
			return "";
		} else {
			return desc_kecamatan_ktp.getName();
		}
	}

	// PARENT PAR KELURAHAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kelurahan", insertable = false, updatable = false)
	private Par_kel desc_kelurahan;

	@JsonProperty("desc_kelurahan")
	public String getDesc_kelurahan() {
		if (desc_kelurahan == null) {
			return "";
		} else {
			return desc_kelurahan.getName();
		}
	}

	// PARENT PAR KELURAHAN KTP
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kelurahan_ktp", insertable = false, updatable = false)
	private Par_kel desc_kelurahan_ktp;

	@JsonProperty("desc_kelurahan_ktp")
	public String getDesc_kelurahan_ktp() {
		if (desc_kelurahan_ktp == null) {
			return "";
		} else {
			return desc_kelurahan_ktp.getName();
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

	public String getGolongan_deb() {
		return golongan_deb;
	}

	public void setGolongan_deb(String golongan_deb) {
		this.golongan_deb = golongan_deb;
	}

	public Integer getGolongan_bi() {
		return golongan_bi;
	}

	public void setGolongan_bi(Integer golongan_bi) {
		this.golongan_bi = golongan_bi;
	}

	public Integer getHubungan_bank() {
		return hubungan_bank;
	}

	public void setHubungan_bank(Integer hubungan_bank) {
		this.hubungan_bank = hubungan_bank;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public Integer getPendidikan() {
		return pendidikan;
	}

	public void setPendidikan(Integer pendidikan) {
		this.pendidikan = pendidikan;
	}

	public Integer getAgama() {
		return agama;
	}

	public void setAgama(Integer agama) {
		this.agama = agama;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getKebangsaan() {
		return kebangsaan;
	}

	public void setKebangsaan(Integer kebangsaan) {
		this.kebangsaan = kebangsaan;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Integer getKelurahan() {
		return kelurahan;
	}

	public void setKelurahan(Integer kelurahan) {
		this.kelurahan = kelurahan;
	}

	public Integer getKecamatan() {
		return kecamatan;
	}

	public void setKecamatan(Integer kecamatan) {
		this.kecamatan = kecamatan;
	}

	public Integer getDati2() {
		return dati2;
	}

	public void setDati2(Integer dati2) {
		this.dati2 = dati2;
	}

	public Integer getProvinsi() {
		return provinsi;
	}

	public void setProvinsi(Integer provinsi) {
		this.provinsi = provinsi;
	}

	public Integer getKdpos() {
		return kdpos;
	}

	public void setKdpos(Integer kdpos) {
		this.kdpos = kdpos;
	}

	public String getAlamat_ktp() {
		return alamat_ktp;
	}

	public void setAlamat_ktp(String alamat_ktp) {
		this.alamat_ktp = alamat_ktp;
	}

	public Integer getKelurahan_ktp() {
		return kelurahan_ktp;
	}

	public void setKelurahan_ktp(Integer kelurahan_ktp) {
		this.kelurahan_ktp = kelurahan_ktp;
	}

	public Integer getKecamatan_ktp() {
		return kecamatan_ktp;
	}

	public void setKecamatan_ktp(Integer kecamatan_ktp) {
		this.kecamatan_ktp = kecamatan_ktp;
	}

	public Integer getDati2_ktp() {
		return dati2_ktp;
	}

	public void setDati2_ktp(Integer dati2_ktp) {
		this.dati2_ktp = dati2_ktp;
	}

	public Integer getProvinsi_ktp() {
		return provinsi_ktp;
	}

	public void setProvinsi_ktp(Integer provinsi_ktp) {
		this.provinsi_ktp = provinsi_ktp;
	}

	public Integer getKdpos_ktp() {
		return kdpos_ktp;
	}

	public void setKdpos_ktp(Integer kdpos_ktp) {
		this.kdpos_ktp = kdpos_ktp;
	}

	public String getNama_darurat() {
		return nama_darurat;
	}

	public void setNama_darurat(String nama_darurat) {
		this.nama_darurat = nama_darurat;
	}

	public String getAlamat_darurat() {
		return alamat_darurat;
	}

	public void setAlamat_darurat(String alamat_darurat) {
		this.alamat_darurat = alamat_darurat;
	}

	public String getHub_darurat() {
		return hub_darurat;
	}

	public void setHub_darurat(String hub_darurat) {
		this.hub_darurat = hub_darurat;
	}

	public String getTelp_darurat() {
		return telp_darurat;
	}

	public void setTelp_darurat(String telp_darurat) {
		this.telp_darurat = telp_darurat;
	}

	public String getKdpos_darurat() {
		return kdpos_darurat;
	}

	public void setKdpos_darurat(String kdpos_darurat) {
		this.kdpos_darurat = kdpos_darurat;
	}

	public Integer getTanggungan() {
		return tanggungan;
	}

	public void setTanggungan(Integer tanggungan) {
		this.tanggungan = tanggungan;
	}

	public Integer getPenghasilan() {
		return penghasilan;
	}

	public void setPenghasilan(Integer penghasilan) {
		this.penghasilan = penghasilan;
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

	public LocalDateTime getDatepost_open() {
		return datepost_open;
	}

	public void setDatepost_open(LocalDateTime datepost_open) {
		this.datepost_open = datepost_open;
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

	public List<Pasangan> getPasangan() {
		return pasangan;
	}

	public void setPasangan(List<Pasangan> pasangan) {
		this.pasangan = pasangan;
	}

	public List<Pekerjaan> getPekerjaan() {
		return pekerjaan;
	}

	public void setPekerjaan(List<Pekerjaan> pekerjaan) {
		this.pekerjaan = pekerjaan;
	}

	public String getStatus_pernikahan() {
		return status_pernikahan;
	}

	public void setStatus_pernikahan(String status_pernikahan) {
		this.status_pernikahan = status_pernikahan;
	}

	public String getStatus_tempat_tinggal() {
		return status_tempat_tinggal;
	}

	public void setStatus_tempat_tinggal(String status_tempat_tinggal) {
		this.status_tempat_tinggal = status_tempat_tinggal;
	}

	public String getNo_telp() {
		return no_telp;
	}

	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}

	public Double getBiaya_tanggungan() {
		return biaya_tanggungan;
	}

	public void setBiaya_tanggungan(Double biaya_tanggungan) {
		this.biaya_tanggungan = biaya_tanggungan;
	}

}
