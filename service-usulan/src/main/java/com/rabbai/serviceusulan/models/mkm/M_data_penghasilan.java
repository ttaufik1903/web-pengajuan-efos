package com.rabbai.serviceusulan.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rabbai.serviceusulan.models.Par_bidang_usaha;
import com.rabbai.serviceusulan.models.Par_dinas;
import com.rabbai.serviceusulan.models.Par_profesi;
import com.rabbai.serviceusulan.models.Par_status_perusahaan;
import com.rabbai.serviceusulan.models.Par_sub_dinas;
import com.rabbai.serviceusulan.models.Par_sub_sub_dinas;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "m_data_penghasilan")
public class M_data_penghasilan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String id_debitur;
	private String profesi;
	private String nama_instansi;
	private String no_telp;
	private String alamat;
	private String kode_post;
	private Double gaji;
	private Double tunjangan;
	private Double potongan;
	private Double netto;
	private String share;
	private String kolektif;
	private String status_perusahaan;
	private String nomor_kapling;
	private Double luas_kebun_m;
	private String jenis_kebun;
	private String off_taker;
	private String no_off_taker;
	private LocalDate tgl_off_taker;
	private Double luas_kebun_h;
	private String jabatan;
	private Integer tahun_mulai;
	private String bidang_usaha;
	private String legalitas_usaha1;
	private String no_legalitas_usaha1;
	private String legalitas_usaha2;
	private String no_legalitas_usaha2;
	private String legalitas_usaha3;
	private String no_legalitas_usaha3;
	private String legalitas_usaha4;
	private String no_legalitas_usaha4;
	private String legalitas_usaha5;
	private String no_legalitas_usaha5;
	private String legalitas_usaha6;
	private String no_legalitas_usaha6;
	private Integer manajemen_usaha;
	private Integer komposisi_modal;
	private Integer kepemilikan_usaha;
	private Integer kondisi_usaha;
	private Integer prospek_usaha;
	private Integer pengaruh_iklim_usaha;
	private Integer penjualan;
	private Integer usaha_menghasilkan_laba;
	private String ip_kpmu;
	private String ip_kper;
	private String ip_gupa;
	private String ip_lain1;
	private String ku_izun;
	private String ku_ccmp;
	private String ku_pmup;
	private String ku_lain;
	private String ku_pryd;
	private String ku_ppsp;
	private String ku_stpe;
	private String ku_lain2;
	private String ku_spyd;
	private String ku_lumd;
	private String ku_jtkd;
	private String ku_lain3;
	private String pu_pepa;
	private String pu_prpa;
	private String kode_dinas;
	private String kode_sub_dinas;
	private String kode_sub_sub_dinas;
	private Integer cab_open;
	private String userid_open;
	private LocalDateTime datepost_open;
	private Integer cab_edit;
	private String userid_edit;
	private LocalDateTime datepost_edit;
	private String sistem_byr_cust;
	private String sistem_byr_supp;
	private String nama_koperasi;
	private String status_kepemilikan_kebun;

	public M_data_penghasilan() {
		super();
	}
	
	public M_data_penghasilan (String id_debitur, String nama_instansi,Integer tahun_mulai, Integer manajemen_usaha, Integer komposisi_modal,
			Integer kepemilikan_usaha, Integer kondisi_usaha, Integer prospek_usaha, Integer pengaruh_iklim_usaha, Integer penjualan,
			Integer usaha_menghasilkan_laba, String bidang_usaha) {
		this.id_debitur=id_debitur;
		this.nama_instansi=nama_instansi;
		this.tahun_mulai=tahun_mulai;
		this.manajemen_usaha=manajemen_usaha;
		this.komposisi_modal=komposisi_modal;
		this.kepemilikan_usaha=kepemilikan_usaha;
		this.kondisi_usaha=kondisi_usaha;
		this.prospek_usaha=prospek_usaha;
		this.pengaruh_iklim_usaha=pengaruh_iklim_usaha;
		this.penjualan=penjualan;
		this.usaha_menghasilkan_laba=usaha_menghasilkan_laba;
		this.bidang_usaha=bidang_usaha;

	}
	
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
	private Par_bidang_usaha par_bidang_usaha;

	@JsonProperty("nama_bidang_usaha")
	public String getPar_bidang_usaha() {
		if (par_bidang_usaha == null) {
			return "";
		} else {
			return par_bidang_usaha.getName();
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
			if (kolektif==null) {
				return "";
			} else if (kolektif.equals("")) {
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



	public String getNo_telp() {
		return no_telp;
	}

	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getKode_post() {
		return kode_post;
	}

	public void setKode_post(String kode_post) {
		this.kode_post = kode_post;
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

	public String getNomor_kapling() {
		return nomor_kapling;
	}

	public void setNomor_kapling(String nomor_kapling) {
		this.nomor_kapling = nomor_kapling;
	}

	public Double getLuas_kebun_m() {
		return luas_kebun_m;
	}

	public void setLuas_kebun_m(Double luas_kebun_m) {
		this.luas_kebun_m = luas_kebun_m;
	}

	public String getJenis_kebun() {
		return jenis_kebun;
	}

	public void setJenis_kebun(String jenis_kebun) {
		this.jenis_kebun = jenis_kebun;
	}

	public String getOff_taker() {
		return off_taker;
	}

	public void setOff_taker(String off_taker) {
		this.off_taker = off_taker;
	}

	public String getNo_off_taker() {
		return no_off_taker;
	}

	public void setNo_off_taker(String no_off_taker) {
		this.no_off_taker = no_off_taker;
	}

	public LocalDate getTgl_off_taker() {
		return tgl_off_taker;
	}

	public void setTgl_off_taker(LocalDate tgl_off_taker) {
		this.tgl_off_taker = tgl_off_taker;
	}

	public Double getLuas_kebun_h() {
		return luas_kebun_h;
	}

	public void setLuas_kebun_h(Double luas_kebun_h) {
		this.luas_kebun_h = luas_kebun_h;
	}

	public Integer getTahun_mulai() {
		return tahun_mulai;
	}

	public void setTahun_mulai(Integer tahun_mulai) {
		this.tahun_mulai = tahun_mulai;
	}

	public String getLegalitas_usaha1() {
		return legalitas_usaha1;
	}

	public void setLegalitas_usaha1(String legalitas_usaha1) {
		this.legalitas_usaha1 = legalitas_usaha1;
	}

	public String getNo_legalitas_usaha1() {
		return no_legalitas_usaha1;
	}

	public void setNo_legalitas_usaha1(String no_legalitas_usaha1) {
		this.no_legalitas_usaha1 = no_legalitas_usaha1;
	}

	public String getLegalitas_usaha2() {
		return legalitas_usaha2;
	}

	public void setLegalitas_usaha2(String legalitas_usaha2) {
		this.legalitas_usaha2 = legalitas_usaha2;
	}

	public String getNo_legalitas_usaha2() {
		return no_legalitas_usaha2;
	}

	public void setNo_legalitas_usaha2(String no_legalitas_usaha2) {
		this.no_legalitas_usaha2 = no_legalitas_usaha2;
	}

	public String getLegalitas_usaha3() {
		return legalitas_usaha3;
	}

	public void setLegalitas_usaha3(String legalitas_usaha3) {
		this.legalitas_usaha3 = legalitas_usaha3;
	}

	public String getNo_legalitas_usaha3() {
		return no_legalitas_usaha3;
	}

	public void setNo_legalitas_usaha3(String no_legalitas_usaha3) {
		this.no_legalitas_usaha3 = no_legalitas_usaha3;
	}

	public String getLegalitas_usaha4() {
		return legalitas_usaha4;
	}

	public void setLegalitas_usaha4(String legalitas_usaha4) {
		this.legalitas_usaha4 = legalitas_usaha4;
	}

	public String getNo_legalitas_usaha4() {
		return no_legalitas_usaha4;
	}

	public void setNo_legalitas_usaha4(String no_legalitas_usaha4) {
		this.no_legalitas_usaha4 = no_legalitas_usaha4;
	}

	public String getLegalitas_usaha5() {
		return legalitas_usaha5;
	}

	public void setLegalitas_usaha5(String legalitas_usaha5) {
		this.legalitas_usaha5 = legalitas_usaha5;
	}

	public String getNo_legalitas_usaha5() {
		return no_legalitas_usaha5;
	}

	public void setNo_legalitas_usaha5(String no_legalitas_usaha5) {
		this.no_legalitas_usaha5 = no_legalitas_usaha5;
	}

	public String getLegalitas_usaha6() {
		return legalitas_usaha6;
	}

	public void setLegalitas_usaha6(String legalitas_usaha6) {
		this.legalitas_usaha6 = legalitas_usaha6;
	}

	public String getNo_legalitas_usaha6() {
		return no_legalitas_usaha6;
	}

	public void setNo_legalitas_usaha6(String no_legalitas_usaha6) {
		this.no_legalitas_usaha6 = no_legalitas_usaha6;
	}

	public Integer getManajemen_usaha() {
		return manajemen_usaha;
	}

	public void setManajemen_usaha(Integer manajemen_usaha) {
		this.manajemen_usaha = manajemen_usaha;
	}

	public Integer getKomposisi_modal() {
		return komposisi_modal;
	}

	public void setKomposisi_modal(Integer komposisi_modal) {
		this.komposisi_modal = komposisi_modal;
	}

	public Integer getKepemilikan_usaha() {
		return kepemilikan_usaha;
	}

	public void setKepemilikan_usaha(Integer kepemilikan_usaha) {
		this.kepemilikan_usaha = kepemilikan_usaha;
	}

	public Integer getKondisi_usaha() {
		return kondisi_usaha;
	}

	public void setKondisi_usaha(Integer kondisi_usaha) {
		this.kondisi_usaha = kondisi_usaha;
	}

	public Integer getProspek_usaha() {
		return prospek_usaha;
	}

	public void setProspek_usaha(Integer prospek_usaha) {
		this.prospek_usaha = prospek_usaha;
	}

	public Integer getPengaruh_iklim_usaha() {
		return pengaruh_iklim_usaha;
	}

	public void setPengaruh_iklim_usaha(Integer pengaruh_iklim_usaha) {
		this.pengaruh_iklim_usaha = pengaruh_iklim_usaha;
	}

	public Integer getPenjualan() {
		return penjualan;
	}

	public void setPenjualan(Integer penjualan) {
		this.penjualan = penjualan;
	}

	public Integer getUsaha_menghasilkan_laba() {
		return usaha_menghasilkan_laba;
	}

	public void setUsaha_menghasilkan_laba(Integer usaha_menghasilkan_laba) {
		this.usaha_menghasilkan_laba = usaha_menghasilkan_laba;
	}

	public String getIp_kpmu() {
		return ip_kpmu;
	}

	public void setIp_kpmu(String ip_kpmu) {
		this.ip_kpmu = ip_kpmu;
	}

	public String getIp_kper() {
		return ip_kper;
	}

	public void setIp_kper(String ip_kper) {
		this.ip_kper = ip_kper;
	}

	public String getIp_gupa() {
		return ip_gupa;
	}

	public void setIp_gupa(String ip_gupa) {
		this.ip_gupa = ip_gupa;
	}

	public String getIp_lain1() {
		return ip_lain1;
	}

	public void setIp_lain1(String ip_lain1) {
		this.ip_lain1 = ip_lain1;
	}

	public String getKu_izun() {
		return ku_izun;
	}

	public void setKu_izun(String ku_izun) {
		this.ku_izun = ku_izun;
	}

	public String getKu_ccmp() {
		return ku_ccmp;
	}

	public void setKu_ccmp(String ku_ccmp) {
		this.ku_ccmp = ku_ccmp;
	}

	public String getKu_pmup() {
		return ku_pmup;
	}

	public void setKu_pmup(String ku_pmup) {
		this.ku_pmup = ku_pmup;
	}

	public String getKu_lain() {
		return ku_lain;
	}

	public void setKu_lain(String ku_lain) {
		this.ku_lain = ku_lain;
	}

	public String getKu_pryd() {
		return ku_pryd;
	}

	public void setKu_pryd(String ku_pryd) {
		this.ku_pryd = ku_pryd;
	}

	public String getKu_ppsp() {
		return ku_ppsp;
	}

	public void setKu_ppsp(String ku_ppsp) {
		this.ku_ppsp = ku_ppsp;
	}

	public String getKu_stpe() {
		return ku_stpe;
	}

	public void setKu_stpe(String ku_stpe) {
		this.ku_stpe = ku_stpe;
	}

	public String getKu_lain2() {
		return ku_lain2;
	}

	public void setKu_lain2(String ku_lain2) {
		this.ku_lain2 = ku_lain2;
	}

	public String getKu_spyd() {
		return ku_spyd;
	}

	public void setKu_spyd(String ku_spyd) {
		this.ku_spyd = ku_spyd;
	}

	public String getKu_lumd() {
		return ku_lumd;
	}

	public void setKu_lumd(String ku_lumd) {
		this.ku_lumd = ku_lumd;
	}

	public String getKu_jtkd() {
		return ku_jtkd;
	}

	public void setKu_jtkd(String ku_jtkd) {
		this.ku_jtkd = ku_jtkd;
	}

	public String getKu_lain3() {
		return ku_lain3;
	}

	public void setKu_lain3(String ku_lain3) {
		this.ku_lain3 = ku_lain3;
	}

	public String getPu_pepa() {
		return pu_pepa;
	}

	public void setPu_pepa(String pu_pepa) {
		this.pu_pepa = pu_pepa;
	}

	public String getPu_prpa() {
		return pu_prpa;
	}

	public void setPu_prpa(String pu_prpa) {
		this.pu_prpa = pu_prpa;
	}

	public String getNama_koperasi() {
		return nama_koperasi;
	}

	public void setNama_koperasi(String nama_koperasi) {
		this.nama_koperasi = nama_koperasi;
	}

	public String getStatus_kepemilikan_kebun() {
		return status_kepemilikan_kebun;
	}

	public void setStatus_kepemilikan_kebun(String status_kepemilikan_kebun) {
		this.status_kepemilikan_kebun = status_kepemilikan_kebun;
	}

}
