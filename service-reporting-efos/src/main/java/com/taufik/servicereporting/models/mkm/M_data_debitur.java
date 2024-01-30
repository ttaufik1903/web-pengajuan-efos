package com.taufik.servicereporting.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taufik.servicereporting.models.Par_dati2;
import com.taufik.servicereporting.models.Par_kebangsaan;
import com.taufik.servicereporting.models.Par_kec;
import com.taufik.servicereporting.models.Par_kel;
import com.taufik.servicereporting.models.Par_kelamin;
import com.taufik.servicereporting.models.Par_pendidikan;
import com.taufik.servicereporting.models.Par_provinsi;
import com.taufik.servicereporting.models.Par_status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "m_data_debitur")
public class M_data_debitur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String cif;
	private String nama;
	private String tmp_lahir;
	private LocalDate tgl_lahir;
	private String golongan_deb;
	private Integer golongan_bi;
	private String passport;
	private String npwp;
	private Integer pendidikan;
	private Integer agama;
	private String no_telp;
	private String status_pernikahan;
	private Integer kelamin;
	private Integer kebangsaan;
	private String alamat;
	private Integer provinsi;
	private Integer dati2;
	private Integer kecamatan;
	private BigDecimal kelurahan;
	private Integer kdpos;
	private String tahun_domisili;
	private String alamat_ktp;
	private Integer provinsi_ktp;
	private Integer dati2_ktp;
	private Integer kecamatan_ktp;
	private BigDecimal kelurahan_ktp;
	private Integer kdpos_ktp;
	private String is_keluarga;
	private String nama_darurat1;
	private String alamat_darurat1;
	private String hub_darurat1;
	private String telp_darurat1;
	private String kdpos_darurat1;
	private String nama_darurat2;
	private String alamat_darurat2;
	private String hub_darurat2;
	private String telp_darurat2;
	private String kdpos_darurat2;
	private Integer tanggungan;
	private Double biaya_tanggungan;
	private Integer penghasilan;
	private Integer cab_open;
	private String userid_open;
	private LocalDateTime datepost_open;
	private Integer cab_edit;
	private String userid_edit;
	private LocalDateTime datepost_edit;
	private String status_tempat_tinggal;


	// CHILD
		@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "debitur")
		private List<M_data_loan> loan;

//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_debitur", referencedColumnName = "id")
//	private List<M_data_pasangan> pasangan;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_debitur", referencedColumnName = "id")
	@OrderBy("datepost_open DESC")
	private List<M_data_penghasilan> m_data_penghasilan;



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

	public String getTmp_lahir() {
		return tmp_lahir;
	}

	public void setTmp_lahir(String tmp_lahir) {
		this.tmp_lahir = tmp_lahir;
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

	public BigDecimal getKelurahan() {
		return kelurahan;
	}

	public void setKelurahan(BigDecimal kelurahan) {
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

	public BigDecimal getKelurahan_ktp() {
		return kelurahan_ktp;
	}

	public void setKelurahan_ktp(BigDecimal kelurahan_ktp) {
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

//	public List<M_data_pasangan> getPasangan() {
//		return pasangan;
//	}
//
//	public void setPasangan(List<M_data_pasangan> m_data_pasangan) {
//		this.pasangan = m_data_pasangan;
//	}
//
//	public List<M_data_penghasilan> getPekerjaan() {
//		return m_data_penghasilan;
//	}
//
//	public void setPekerjaan(List<M_data_penghasilan> m_data_penghasilan) {
//		this.m_data_penghasilan = m_data_penghasilan;
//	}

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

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getTgl_lahir() {
		return tgl_lahir;
	}

	public void setTgl_lahir(LocalDate tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}

	public String getTahun_domisili() {
		return tahun_domisili;
	}

	public void setTahun_domisili(String tahun_domisili) {
		this.tahun_domisili = tahun_domisili;
	}

	public String getIs_keluarga() {
		return is_keluarga;
	}

	public void setIs_keluarga(String is_keluarga) {
		this.is_keluarga = is_keluarga;
	}

	public String getNama_darurat1() {
		return nama_darurat1;
	}

	public void setNama_darurat1(String nama_darurat1) {
		this.nama_darurat1 = nama_darurat1;
	}

	public String getAlamat_darurat1() {
		return alamat_darurat1;
	}

	public void setAlamat_darurat1(String alamat_darurat1) {
		this.alamat_darurat1 = alamat_darurat1;
	}

	public String getHub_darurat1() {
		return hub_darurat1;
	}

	public void setHub_darurat1(String hub_darurat1) {
		this.hub_darurat1 = hub_darurat1;
	}

	public String getTelp_darurat1() {
		return telp_darurat1;
	}

	public void setTelp_darurat1(String telp_darurat1) {
		this.telp_darurat1 = telp_darurat1;
	}

	public String getKdpos_darurat1() {
		return kdpos_darurat1;
	}

	public void setKdpos_darurat1(String kdpos_darurat1) {
		this.kdpos_darurat1 = kdpos_darurat1;
	}

	public String getNama_darurat2() {
		return nama_darurat2;
	}

	public void setNama_darurat2(String nama_darurat2) {
		this.nama_darurat2 = nama_darurat2;
	}

	public String getAlamat_darurat2() {
		return alamat_darurat2;
	}

	public void setAlamat_darurat2(String alamat_darurat2) {
		this.alamat_darurat2 = alamat_darurat2;
	}

	public String getHub_darurat2() {
		return hub_darurat2;
	}

	public void setHub_darurat2(String hub_darurat2) {
		this.hub_darurat2 = hub_darurat2;
	}

	public String getTelp_darurat2() {
		return telp_darurat2;
	}

	public void setTelp_darurat2(String telp_darurat2) {
		this.telp_darurat2 = telp_darurat2;
	}

	public String getKdpos_darurat2() {
		return kdpos_darurat2;
	}

	public void setKdpos_darurat2(String kdpos_darurat2) {
		this.kdpos_darurat2 = kdpos_darurat2;
	}

	public List<M_data_penghasilan> getM_data_penghasilan() {
		return m_data_penghasilan;
	}

	public void setM_data_penghasilan(List<M_data_penghasilan> m_data_penghasilan) {
		this.m_data_penghasilan = m_data_penghasilan;
	}

//	public List<M_data_loan> getLoan() {
//		return loan;
//	}
//
//	public void setLoan(List<M_data_loan> loan) {
//		this.loan = loan;
//	}

//	public List<M_data_penghasilan> getM_data_penghasilan() {
//		return m_data_penghasilan;
//	}
//
//	public void setM_data_penghasilan(List<M_data_penghasilan> m_data_penghasilan) {
//		this.m_data_penghasilan = m_data_penghasilan;
//	}

}
