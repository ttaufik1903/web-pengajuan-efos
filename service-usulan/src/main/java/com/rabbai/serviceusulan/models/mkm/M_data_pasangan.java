package com.rabbai.serviceusulan.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rabbai.serviceusulan.models.Par_agama;
import com.rabbai.serviceusulan.models.Par_bidang_usaha;
import com.rabbai.serviceusulan.models.Par_dati2;
import com.rabbai.serviceusulan.models.Par_kebangsaan;
import com.rabbai.serviceusulan.models.Par_kec;
import com.rabbai.serviceusulan.models.Par_kel;
import com.rabbai.serviceusulan.models.Par_pendidikan;
import com.rabbai.serviceusulan.models.Par_profesi;
import com.rabbai.serviceusulan.models.Par_provinsi;
import com.rabbai.serviceusulan.models.Par_status_perusahaan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "m_data_pasangan")
public class M_data_pasangan {

	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String id_debitur;
	private String nama;
	private String golongan_deb;
	private Integer golongan_bi;
	private Integer hubungan_bank;
	private String passport;
	private String npwp;
	private Integer pendidikan;
	private Integer agama;
	private String tmp_lahir;
	private LocalDate tgl_lahir;
	private Integer kebangsaan;
	private String alamat;
	private String no_telp;
	private Integer provinsi;
	private Integer dati2;
	private Integer kecamatan;
	private BigDecimal kelurahan;
	private Integer kdpos;
	private String alamat_ktp;
	private Integer provinsi_ktp;
	private Integer dati2_ktp;
	private Integer kecamatan_ktp;
	private BigDecimal kelurahan_ktp;
	private Integer kdpos_ktp;
	private Integer penghasilan;
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
	private String desc_penghasilan_lainnya1;
	private Double jumlah_penghasilan_lainnya1;
	private String desc_penghasilan_lainnya2;
	private Double jumlah_penghasilan_lainnya2;
	private String desc_penghasilan_lainnya3;
	private Double jumlah_penghasilan_lainnya3;
	private Double kolektif;
	private Integer cab_open;
	private String userid_open;
	private LocalDateTime datepost_open;
	private Integer cab_edit;
	private String userid_edit;
	private LocalDateTime datepost_edit;
	private String sistem_byr_cust;
	private String sistem_byr_supp;

	public M_data_pasangan() {
		super();
	}
	
	public M_data_pasangan (String id,
			String id_debitur,
			String nama,
			String golongan_deb,
			Integer golongan_bi,
			Integer hubungan_bank,
			String passport,
			String npwp,
			Integer pendidikan,
			Integer agama,
			String tmp_lahir,
			LocalDate tgl_lahir,
			Integer kebangsaan,
			String alamat,
			String no_telp,
			Integer provinsi,
			Integer dati2,
			Integer kecamatan,
			BigDecimal kelurahan,
			Integer kdpos,
			String alamat_ktp,
			Integer provinsi_ktp,
			Integer dati2_ktp,
			Integer kecamatan_ktp,
			BigDecimal kelurahan_ktp,
			Integer kdpos_ktp,
			Integer penghasilan,
			String profesi,
			String nama_instansi,
			String status_perusahaan,
			String jabatan,
			String bidang_usaha,
			Integer tahun_bekerja,
			Double gaji,
			Double tunjangan,
			Double potongan,
			Double netto,
			String desc_penghasilan_lainnya1,
			Double jumlah_penghasilan_lainnya1,
			String desc_penghasilan_lainnya2,
			Double jumlah_penghasilan_lainnya2,
			String desc_penghasilan_lainnya3,
			Double jumlah_penghasilan_lainnya3,
			Double kolektif,
			Integer cab_open,
			String userid_open,
			LocalDateTime datepost_open,
			String sistem_byr_cust,
			String sistem_byr_supp) {
		this.id=id;
		this.id_debitur=id_debitur;
		this.nama=nama;
		this.golongan_deb=golongan_deb;
		this.golongan_bi=golongan_bi;
		this.hubungan_bank=hubungan_bank;
		this.passport=passport;
		this.npwp=npwp;
		this.pendidikan=pendidikan;
		this.agama=agama;
		this.tmp_lahir=tmp_lahir;
		this.tgl_lahir=tgl_lahir;
		this.kebangsaan=kebangsaan;
		this.alamat=alamat;
		this.no_telp=no_telp;
		this.provinsi=provinsi;
		this.dati2=dati2;
		this.kecamatan=kecamatan;
		this.kelurahan=kelurahan;
		this.kdpos=kdpos;
		this.alamat_ktp=alamat_ktp;
		this.provinsi_ktp=provinsi_ktp;
		this.dati2_ktp=dati2_ktp;
		this.kecamatan_ktp=kecamatan_ktp;
		this.kelurahan_ktp=kelurahan_ktp;
		this.kdpos_ktp=kdpos_ktp;
		this.penghasilan=penghasilan;
		this.profesi=profesi;
		this.nama_instansi=nama_instansi;
		this.status_perusahaan=status_perusahaan;
		this.jabatan=jabatan;
		this.bidang_usaha=bidang_usaha;
		this.tahun_bekerja=tahun_bekerja;
		this.gaji=gaji;
		this.tunjangan=tunjangan;
		this.potongan=potongan;
		this.netto=netto;
		this.desc_penghasilan_lainnya1=desc_penghasilan_lainnya1;
		this.jumlah_penghasilan_lainnya1=jumlah_penghasilan_lainnya1;
		this.desc_penghasilan_lainnya2=desc_penghasilan_lainnya2;
		this.jumlah_penghasilan_lainnya2=jumlah_penghasilan_lainnya2;
		this.desc_penghasilan_lainnya3=desc_penghasilan_lainnya3;
		this.jumlah_penghasilan_lainnya3=jumlah_penghasilan_lainnya3;
		this.kolektif=kolektif;
		this.cab_open=cab_open;
		this.userid_open=userid_open;
		this.datepost_open=datepost_open;
		this.sistem_byr_cust=sistem_byr_cust;
		this.sistem_byr_supp=sistem_byr_supp;
	}


	// PARENT PAR AGAMA
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agama", insertable = false, updatable = false)
	private Par_agama desc_agama;

	@JsonProperty("desc_agama")
	public String getPar_agama() {
		if (desc_agama == null) {
			return "";
		} else {
			return desc_agama.getName();
		}
	}

	// PARENT PAR KEBANGSAAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kebangsaan", insertable = false, updatable = false)
	private Par_kebangsaan desc_kebangsaan;

	@JsonProperty("desc_kebangsaan")
	public String getPar_kebangsaan() {
		if (desc_kebangsaan == null) {
			return "";
		} else {
			return desc_kebangsaan.getName();
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
	private Par_pendidikan desc_pendidikan;

	@JsonProperty("desc_pendidikan")
	public String getPar_pendidikan() {
		if (desc_pendidikan == null) {
			return "";
		} else {
			return desc_pendidikan.getName();
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

	// PARENT PROFESI
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesi", insertable = false, updatable = false)
	private Par_profesi desc_profesi;

	@JsonProperty("desc_profesi")
	public String getPar_profesi() {
		if (desc_profesi == null) {
			return "";
		} else {
			return desc_profesi.getName();
		}
	}

	// PARENT BIDANG USAHA
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bidang_usaha", insertable = false, updatable = false)
	private Par_bidang_usaha desc_bidang_usaha;

	@JsonProperty("desc_bidang_usaha")
	public String getPar_bidang_usaha() {
		if (desc_bidang_usaha == null) {
			return "";
		} else {
			return desc_bidang_usaha.getName();
		}
	}

	// PARENT STATUS PERUSAHAAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_perusahaan", insertable = false, updatable = false)
	private Par_status_perusahaan desc_status_perusahaan;

	@JsonProperty("desc_status_perusahaan")
	public String getPar_status_perusahaan() {
		if (desc_status_perusahaan == null) {
			return "";
		} else {
			return desc_status_perusahaan.getName();
		}
	}

	// DESC KOLEKTIF
	@JsonProperty("desc_kolektif")
	public String getDesc_kolektif() {
		if (kolektif == null) {
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

	public Integer getPenghasilan() {
		return penghasilan;
	}

	public void setPenghasilan(Integer penghasilan) {
		this.penghasilan = penghasilan;
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

	public Integer getTahun_bekerja() {
		return tahun_bekerja;
	}

	public void setTahun_bekerja(Integer lama_bekerja) {
		this.tahun_bekerja = lama_bekerja;
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

	public Integer getCab_open() {
		return cab_open;
	}

	public void setCab_open(Integer cab_open) {
		this.cab_open = cab_open;
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

	public String getId_debitur() {
		return id_debitur;
	}

	public void setId_debitur(String id_debitur) {
		this.id_debitur = id_debitur;
	}

	public String getNo_telp() {
		return no_telp;
	}

	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}

	public String getSistem_byr_cust() {
		return sistem_byr_cust;
	}

	public void setSistem_byr_cust(String sistem_byr_cust) {
		this.sistem_byr_cust = sistem_byr_cust;
	}

	public String getSistem_byr_supp() {
		return sistem_byr_supp;
	}

	public void setSistem_byr_supp(String sistem_byr_supp) {
		this.sistem_byr_supp = sistem_byr_supp;
	}

	public String getDesc_penghasilan_lainnya1() {
		return desc_penghasilan_lainnya1;
	}

	public void setDesc_penghasilan_lainnya1(String desc_penghasilan_lainnya1) {
		this.desc_penghasilan_lainnya1 = desc_penghasilan_lainnya1;
	}

	public Double getJumlah_penghasilan_lainnya1() {
		return jumlah_penghasilan_lainnya1;
	}

	public void setJumlah_penghasilan_lainnya1(Double jumlah_penghasilan_lainnya1) {
		this.jumlah_penghasilan_lainnya1 = jumlah_penghasilan_lainnya1;
	}

	public String getDesc_penghasilan_lainnya2() {
		return desc_penghasilan_lainnya2;
	}

	public void setDesc_penghasilan_lainnya2(String desc_penghasilan_lainnya2) {
		this.desc_penghasilan_lainnya2 = desc_penghasilan_lainnya2;
	}

	public Double getJumlah_penghasilan_lainnya2() {
		return jumlah_penghasilan_lainnya2;
	}

	public void setJumlah_penghasilan_lainnya2(Double jumlah_penghasilan_lainnya2) {
		this.jumlah_penghasilan_lainnya2 = jumlah_penghasilan_lainnya2;
	}

	public String getDesc_penghasilan_lainnya3() {
		return desc_penghasilan_lainnya3;
	}

	public void setDesc_penghasilan_lainnya3(String desc_penghasilan_lainnya3) {
		this.desc_penghasilan_lainnya3 = desc_penghasilan_lainnya3;
	}

	public Double getJumlah_penghasilan_lainnya3() {
		return jumlah_penghasilan_lainnya3;
	}

	public void setJumlah_penghasilan_lainnya3(Double jumlah_penghasilan_lainnya3) {
		this.jumlah_penghasilan_lainnya3 = jumlah_penghasilan_lainnya3;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getKolektif() {
		return kolektif;
	}

	public void setKolektif(Double kolektif) {
		this.kolektif = kolektif;
	}

	public String getUserid_open() {
		return userid_open;
	}

	public void setUserid_open(String userid_open) {
		this.userid_open = userid_open;
	}

}
