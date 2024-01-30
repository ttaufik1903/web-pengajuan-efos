package com.rabbai.serviceusulan.models.mkm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rabbai.serviceusulan.models.Par_bagi_hasil;
import com.rabbai.serviceusulan.models.Par_bagian_dijamin;
import com.rabbai.serviceusulan.models.Par_bi_sifat_akad;
import com.rabbai.serviceusulan.models.Par_bi_status_piutang;
import com.rabbai.serviceusulan.models.Par_bv_cara_restruk;
import com.rabbai.serviceusulan.models.Par_bv_golongan_debitur;
import com.rabbai.serviceusulan.models.Par_bv_golongan_piutang;
import com.rabbai.serviceusulan.models.Par_bv_jenis_akad;
import com.rabbai.serviceusulan.models.Par_bv_jenis_garansi;
import com.rabbai.serviceusulan.models.Par_bv_jenis_penggunaan;
import com.rabbai.serviceusulan.models.Par_bv_jenis_piutang;
import com.rabbai.serviceusulan.models.Par_bv_kode_frekuensirestruk;
import com.rabbai.serviceusulan.models.Par_bv_kode_kondisi;
import com.rabbai.serviceusulan.models.Par_bv_kode_sebab_macet;
import com.rabbai.serviceusulan.models.Par_bv_sifat_kredit;
import com.rabbai.serviceusulan.models.Par_bv_status_debitur;
import com.rabbai.serviceusulan.models.Par_bv_tujuan_garansi;
import com.rabbai.serviceusulan.models.Par_bv_tujuan_kredit;
import com.rabbai.serviceusulan.models.Par_gol_debitur_sid;
import com.rabbai.serviceusulan.models.Par_gol_pihak_lawan;
import com.rabbai.serviceusulan.models.Par_jenis_aktiva_ijarah;
import com.rabbai.serviceusulan.models.Par_jenis_aset;
import com.rabbai.serviceusulan.models.Par_jenis_suku_imbalan;
import com.rabbai.serviceusulan.models.Par_jenis_valuta;
import com.rabbai.serviceusulan.models.Par_kategori_portofolio;
import com.rabbai.serviceusulan.models.Par_kategori_debitur;
import com.rabbai.serviceusulan.models.Par_lembaga_pemeringkat;
import com.rabbai.serviceusulan.models.Par_metode_amortisasi;
import com.rabbai.serviceusulan.models.Par_orientasi_penggunaan;
import com.rabbai.serviceusulan.models.Par_periode_sewa;
import com.rabbai.serviceusulan.models.Par_program_pemerintah;
import com.rabbai.serviceusulan.models.Par_rating_pihak_lawan;
import com.rabbai.serviceusulan.models.Par_sandi_bi;
import com.rabbai.serviceusulan.models.Par_sektor_ekonomi;
import com.rabbai.serviceusulan.models.Par_sektor_ekonomi_kur;
import com.rabbai.serviceusulan.models.Par_sifat_investasi;
import com.rabbai.serviceusulan.models.Par_sifat_pembiayaan;
import com.rabbai.serviceusulan.models.Par_skim_pembiayaan;
import com.rabbai.serviceusulan.models.Par_sumber_dana;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "m_data_bi", schema = "pelaporan")
public class M_data_bi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	private String id_loan;
	private String kategori_debitur;
	private Integer kategori_portofolio;
	private String skim_pembiayaan;
	private String sumber_dana;
	private String sifat_investasi;
	private String bagi_hasil;
	private Integer persentase_nisbah;
	private Double persentase_rbh;
	private Integer sifat_pembiayaan;
	private Integer orientasi_penggunaan;
	private BigInteger jenis_valuta;
	private String program_pemerintah;
	private String sektor_ekonomi_kur;
	private String sektor_ekonomi;
	private String lokasi_pengguna;
	private String jenis_aset;
	private LocalDate waktu_perolehan_aset;
	private String jenis_valuta_aset;
	private Double harga_perolehan_aset;
	private Double jumlah_amortisasi;
	private String metode_amortisasi;
	private String periode_sewa;
	private Integer nilai_sewa_periode;
	private Integer amortisasi;
	private Double pecadangan_penurunan_aset;
	private Double uang_muka_ijarah;
	private Integer jenis_suku_imbalan;
	private Double persentase_imbalan_awal_kontrak;
	private Double persentase_imbalan_bulan_laporan;
	// private Integer kualitas;
//	private Double plafon_awal;
//	private Double plafon;
	// private Double saldo_margin_ditangguhkan;
	// private Double realisasi_bulan_berjalan;
	// private Double kelonggaran_tarik_com;
	// private Double kelonggaran_tarik_uncom;
	// private Double nilai_anggunan_diperhitungkan_kredit;
	// private Double nilai_anggunan_diperhitungkan_kelonggaran_tarik;
	private String bagian_dijamin;
	private String golongan_pihak_lawan;
	private String lembaga_pemeringkat;
	private String rating_pihak_lawan;
	// private LocalDate tanggal_peringkat;

	private String status_debitur;
	private String jenis_penggunaan;
	private String jenis_piutang;
	private String jenis_akad;
	private String bi_status_piutang;
	private String bi_sifat_akad;
	private String golongan_debitur;
	private String jenis_aktiva_ijarah;
	private String golongan_piutang;
	private String sifat_kredit_bi;
	private String bi_tujuan_kredit;
	private String tujuan_garansi;
	private String tgl_pemeringkat;

	private String cara_restruktur;
	private String kode_sebab_macet;
	private String freq_restruktur;
	private String jenis_garansi;
	private String kode_kondisi;
	private String is_restruktur;
	private String keterangan;
	private String sumber_dana_bi;
	private String kode_sektor_ekonomi_bi;
	private String gol_debitur_sid;
	private String jenis_penggunaan_bi;
	private String sifat_piutang;
	private String badan_hukum;
	private String hubungan_bank;

	// PARENT PAR kategori_debitur
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kategori_debitur", insertable = false, updatable = false, nullable = true)
	private Par_kategori_debitur par_kategori_debitur;

	@JsonProperty("desc_kategori_debitur")
	public String getDesc_kategori_debitur() {
		if (par_kategori_debitur == null) {
			return "";
		} else {
			return par_kategori_debitur.getKeterangan();
		}
	}

	// PARENT PAR kategori_portofolio
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kategori_portofolio", insertable = false, updatable = false, nullable = true)
	private Par_kategori_portofolio par_kategori_portofolio;

	@JsonProperty("desc_kategori_portofolio")
	public String getDesc_kategori_portofolio() {
		if (par_kategori_portofolio == null) {
			return "";
		} else {
			return par_kategori_portofolio.getName();
		}
	}

	// PARENT PAR skim_pembiayaan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skim_pembiayaan", insertable = false, updatable = false, nullable = true)
	private Par_skim_pembiayaan par_skim_pembiayaan;

	@JsonProperty("desc_skim_pembiayaan")
	public String getDesc_skim_pembiayaan() {
		if (par_skim_pembiayaan == null) {
			return "";
		} else {
			return par_skim_pembiayaan.getName();
		}
	}

	// PARENT PAR sumber_dana
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sumber_dana", insertable = false, updatable = false, nullable = true)
	private Par_sumber_dana par_sumber_dana;

	@JsonProperty("desc_sumber_dana")
	public String getDesc_sumber_dana() {
		if (par_sumber_dana == null) {
			return "";
		} else {
			return par_sumber_dana.getName();
		}
	}

	// PARENT PAR bagi_hasil
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bagi_hasil", insertable = false, updatable = false, nullable = true)
	private Par_bagi_hasil par_bagi_hasil;

	@JsonProperty("desc_bagi_hasil")
	public String getDesc_bagi_hasil() {
		if (par_bagi_hasil == null) {
			return "";
		} else {
			return par_bagi_hasil.getName();
		}
	}

	// PARENT PAR sifat_investasi
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sifat_investasi", insertable = false, updatable = false, nullable = true)
	private Par_sifat_investasi par_sifat_investasi;

	@JsonProperty("desc_sifat_investasi")
	public String getDesc_sifat_investasi() {
		if (par_sifat_investasi == null) {
			return "";
		} else {
			return par_sifat_investasi.getName();
		}
	}

	// PARENT PAR orientasi_penggunaan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orientasi_penggunaan", insertable = false, updatable = false, nullable = true)
	private Par_orientasi_penggunaan par_orientasi_penggunaan;

	@JsonProperty("desc_orientasi_penggunaan")
	public String getDesc_orientasi_penggunaan() {
		if (par_orientasi_penggunaan == null) {
			return "";
		} else {
			return par_orientasi_penggunaan.getName();
		}
	}

	// PARENT PAR sifat_pembiayaan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sifat_pembiayaan", insertable = false, updatable = false, nullable = true)
	private Par_sifat_pembiayaan par_sifat_pembiayaan;

	@JsonProperty("desc_sifat_pembiayaan")
	public String getDesc_sifat_pembiayaan() {
		if (par_sifat_pembiayaan == null) {
			return "";
		} else {
			return par_sifat_pembiayaan.getName();
		}
	}

	// PARENT PAR jenis_valuta
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_valuta", insertable = false, updatable = false, nullable = true)
	private Par_jenis_valuta par_jenis_valuta;

	@JsonProperty("desc_jenis_valuta")
	public String getDesc_jenis_valuta() {
		if (par_jenis_valuta == null) {
			return "";
		} else {
			return par_jenis_valuta.getName();
		}
	}

	// PARENT PAR program_pemerintah
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "program_pemerintah", insertable = false, updatable = false, nullable = true)
	private Par_program_pemerintah par_program_pemerintah;

	@JsonProperty("desc_program_pemerintah")
	public String getDesc_program_pemerintah() {
		if (par_program_pemerintah == null) {
			return "";
		} else {
			return par_program_pemerintah.getName();
		}
	}

	// PARENT PAR sektor_ekonomi_kur
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sektor_ekonomi_kur", insertable = false, updatable = false, nullable = true)
	private Par_sektor_ekonomi_kur par_sektor_ekonomi_kur;

	@JsonProperty("desc_sektor_ekonomi_kur")
	public String getDesc_sektor_ekonomi_kur() {
		if (par_sektor_ekonomi_kur == null) {
			return "";
		} else {
			return par_sektor_ekonomi_kur.getName();
		}
	}

	// PARENT PAR sektor_ekonomi
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sektor_ekonomi", insertable = false, updatable = false, nullable = true)
	private Par_sektor_ekonomi par_sektor_ekonomi;

	@JsonProperty("desc_sektor_ekonomi")
	public String getDesc_sektor_ekonomi() {
		if (par_sektor_ekonomi == null) {
			return "";
		} else {
			return par_sektor_ekonomi.getName();
		}
	}

	// PARENT PAR lokasi_pengguna
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lokasi_pengguna", insertable = false, updatable = false, nullable = true)
	private Par_sandi_bi par_sandi_bi;

	@JsonProperty("desc_lokasi_pengguna")
	public String getDesc_lokasi_pengguna() {
		if (par_sandi_bi == null) {
			return "";
		} else {
			return par_sandi_bi.getName();
		}
	}

	// PARENT PAR jenis_aset
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_aset", insertable = false, updatable = false, nullable = true)
	private Par_jenis_aset par_jenis_aset;

	@JsonProperty("desc_jenis_aset")
	public String getDesc_jenis_aset() {
		if (par_jenis_aset == null) {
			return "";
		} else {
			return par_jenis_aset.getName();
		}
	}

	// PARENT PAR metode_amortisasi
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "metode_amortisasi", insertable = false, updatable = false, nullable = true)
	private Par_metode_amortisasi par_metode_amortisasi;

	@JsonProperty("desc_metode_amortisasi")
	public String getDesc_metode_amortisasi() {
		if (par_metode_amortisasi == null) {
			return "";
		} else {
			return par_metode_amortisasi.getName();
		}
	}

	// PARENT PAR periode_sewa
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "periode_sewa", insertable = false, updatable = false, nullable = true)
	private Par_periode_sewa par_periode_sewa;

	@JsonProperty("desc_periode_sewa")
	public String getDesc_periode_sewa() {
		if (par_periode_sewa == null) {
			return "";
		} else {
			return par_periode_sewa.getName();
		}
	}

	// PARENT PAR jenis_suku_imbalan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_suku_imbalan", insertable = false, updatable = false, nullable = true)
	private Par_jenis_suku_imbalan par_jenis_suku_imbalan;

	@JsonProperty("desc_jenis_suku_imbalan")
	public String getDesc_jenis_suku_imbalan() {
		if (par_jenis_suku_imbalan == null) {
			return "";
		} else {
			return par_jenis_suku_imbalan.getName();
		}
	}

	// PARENT PAR kualitas
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "kualitas", insertable = false, updatable = false, nullable = true)
//	private Par_kualitas par_kualitas;
//
//	@JsonProperty("desc_kualitas")
//	public String getDesc_kualitas() {
//		if (par_kualitas == null) {
//			return "";
//		} else {
//			return par_kualitas.getName();
//		}
//	}

	// PARENT PAR lembaga_pemeringkat
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lembaga_pemeringkat", insertable = false, updatable = false, nullable = true)
	private Par_lembaga_pemeringkat par_lembaga_pemeringkat;

	@JsonProperty("desc_lembaga_pemeringkat")
	public String getDesc_lembaga_pemeringkat() {
		if (par_lembaga_pemeringkat == null) {
			return "";
		} else {
			return par_lembaga_pemeringkat.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bagian_dijamin", insertable = false, updatable = false, nullable = true)
	private Par_bagian_dijamin par_bagian_dijamin;

	@JsonProperty("desc_bagian_dijamin")
	public String getDesc_bagian_dijamin() {
		if (par_bagian_dijamin == null) {
			return "";
		} else {
			return par_bagian_dijamin.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bi_sifat_akad", insertable = false, updatable = false, nullable = true)
	private Par_bi_sifat_akad par_bi_sifat_akad;

	@JsonProperty("desc_bi_sifat_akad")
	public String getDesc_bi_sifat_akad() {
		if (par_bi_sifat_akad == null) {
			return "";
		} else {
			return par_bi_sifat_akad.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bi_status_piutang", insertable = false, updatable = false, nullable = true)
	private Par_bi_status_piutang par_bi_status_piutang;

	@JsonProperty("desc_bi_status_piutang")
	public String getDesc_bi_status_piutang() {
		if (par_bi_status_piutang == null) {
			return "";
		} else {
			return par_bi_status_piutang.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cara_restruktur", insertable = false, updatable = false, nullable = true)
	private Par_bv_cara_restruk par_cara_restruktur;

	@JsonProperty("desc_cara_restruktur")
	public String getDesc_cara_restruktur() {
		if (par_cara_restruktur == null) {
			return "";
		} else {
			return par_cara_restruktur.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "freq_restruktur", insertable = false, updatable = false, nullable = true)
	private Par_bv_kode_frekuensirestruk par_freq_restruktur;

	@JsonProperty("desc_freq_restruktur")
	public String getDesc_freq_restruktur() {
		if (par_freq_restruktur == null) {
			return "";
		} else {
			return par_freq_restruktur.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "golongan_debitur", insertable = false, updatable = false, nullable = true)
	private Par_bv_golongan_debitur par_golongan_debitur;

	@JsonProperty("desc_golongan_debitur")
	public String getDesc_golongan_debitur() {
		if (par_golongan_debitur == null) {
			return "";
		} else {
			return par_golongan_debitur.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gol_debitur_sid", insertable = false, updatable = false, nullable = true)
	private Par_gol_debitur_sid par_gol_debitur_sid;

	@JsonProperty("desc_gol_debitur_sid")
	public String getDesc_gol_debitur_sid() {
		if (par_gol_debitur_sid == null) {
			return "";
		} else {
			return par_gol_debitur_sid.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "golongan_pihak_lawan", insertable = false, updatable = false, nullable = true)
	private Par_gol_pihak_lawan par_golongan_pihak_lawan;

	@JsonProperty("desc_golongan_pihak_lawan")
	public String getDesc_golongan_pihak_lawan() {
		if (par_golongan_pihak_lawan == null) {
			return "";
		} else {
			return par_golongan_pihak_lawan.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "golongan_piutang", insertable = false, updatable = false, nullable = true)
	private Par_bv_golongan_piutang par_golongan_piutang;

	@JsonProperty("desc_golongan_piutang")
	public String getDesc_golongan_piutang() {
		if (par_golongan_piutang == null) {
			return "";
		} else {
			return par_golongan_piutang.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_akad", insertable = false, updatable = false, nullable = true)
	private Par_bv_jenis_akad par_jenis_akad;

	@JsonProperty("desc_jenis_akad")
	public String getDesc_jenis_akad() {
		if (par_jenis_akad == null) {
			return "";
		} else {
			return par_jenis_akad.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_aktiva_ijarah", insertable = false, updatable = false, nullable = true)
	private Par_jenis_aktiva_ijarah par_jenis_aktiva_ijarah;

	@JsonProperty("desc_jenis_aktiva_ijarah")
	public String getDesc_jenis_aktiva_ijarah() {
		if (par_jenis_aktiva_ijarah == null) {
			return "";
		} else {
			return par_jenis_aktiva_ijarah.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_garansi", insertable = false, updatable = false, nullable = true)
	private Par_bv_jenis_garansi par_jenis_garansi;

	@JsonProperty("desc_jenis_garansi")
	public String getDesc_jenis_garansi() {
		if (par_jenis_garansi == null) {
			return "";
		} else {
			return par_jenis_garansi.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_penggunaan", insertable = false, updatable = false, nullable = true)
	private Par_bv_jenis_penggunaan par_jenis_penggunaan;

	@JsonProperty("desc_jenis_penggunaan")
	public String getDesc_jenis_penggunaan() {
		if (par_jenis_penggunaan == null) {
			return "";
		} else {
			return par_jenis_penggunaan.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_piutang", insertable = false, updatable = false, nullable = true)
	private Par_bv_jenis_piutang par_jenis_piutang;

	@JsonProperty("desc_jenis_piutang")
	public String getDesc_jenis_piutang() {
		if (par_jenis_piutang == null) {
			return "";
		} else {
			return par_jenis_piutang.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_valuta_aset", insertable = false, updatable = false, nullable = true)
	private Par_jenis_valuta par_jenis_valuta_aset;

	@JsonProperty("desc_jenis_valuta_aset")
	public String getDesc_jenis_valuta_aset() {
		if (par_jenis_valuta_aset == null) {
			return "";
		} else {
			return par_jenis_valuta_aset.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_kondisi", insertable = false, updatable = false, nullable = true)
	private Par_bv_kode_kondisi par_kode_kondisi;

	@JsonProperty("desc_kode_kondisi")
	public String getDesc_kode_kondisi() {
		if (par_kode_kondisi == null) {
			return "";
		} else {
			return par_kode_kondisi.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_sebab_macet", insertable = false, updatable = false, nullable = true)
	private Par_bv_kode_sebab_macet par_kode_sebab_macet;

	@JsonProperty("desc_kode_sebab_macet")
	public String getDesc_kode_sebab_macet() {
		if (par_kode_sebab_macet == null) {
			return "";
		} else {
			return par_kode_sebab_macet.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rating_pihak_lawan", insertable = false, updatable = false, nullable = true)
	private Par_rating_pihak_lawan par_rating_pihak_lawan;

	@JsonProperty("desc_rating_pihak_lawan")
	public String getDesc_rating_pihak_lawan() {
		if (par_rating_pihak_lawan == null) {
			return "";
		} else {
			return par_rating_pihak_lawan.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sifat_kredit_bi", insertable = false, updatable = false, nullable = true)
	private Par_bv_sifat_kredit par_sifat_kredit_bi;

	@JsonProperty("desc_sifat_kredit_bi")
	public String getDesc_sifat_kredit_bi() {
		if (par_sifat_kredit_bi == null) {
			return "";
		} else {
			return par_sifat_kredit_bi.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_debitur", insertable = false, updatable = false, nullable = true)
	private Par_bv_status_debitur par_status_debitur;

	@JsonProperty("desc_status_debitur")
	public String getDesc_status_debitur() {
		if (par_status_debitur == null) {
			return "";
		} else {
			return par_status_debitur.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tujuan_garansi", insertable = false, updatable = false, nullable = true)
	private Par_bv_tujuan_garansi par_tujuan_garansi;

	@JsonProperty("desc_tujuan_garansi")
	public String getDesc_tujuan_garansi() {
		if (par_tujuan_garansi == null) {
			return "";
		} else {
			return par_tujuan_garansi.getName();
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bi_tujuan_kredit", insertable = false, updatable = false, nullable = true)
	private Par_bv_tujuan_kredit par_bi_tujuan_kredit;

	@JsonProperty("desc_bi_tujuan_kredit")
	public String getDesc_bi_tujuan_kredit() {
		if (par_bi_tujuan_kredit == null) {
			return "";
		} else {
			return par_bi_tujuan_kredit.getName();
		}
	}

	public String getId_loan() {
		return id_loan;
	}

	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}


//	public Integer getKategori_portofolio() {
//		return kategori_portofolio;
//	}

//	public void setKategori_portofolio(String kategori_portofolio) {
//		if (kategori_portofolio.equals("") || kategori_portofolio.equals("null")) {
//			this.kategori_portofolio = null;
//		} else {
//			this.kategori_portofolio = Integer.valueOf(kategori_portofolio);
//		}
//	}

	public String getSkim_pembiayaan() {
		return skim_pembiayaan;
	}

	public void setSkim_pembiayaan(String skim_pembiayaan) {
		this.skim_pembiayaan = skim_pembiayaan;
	}

	public String getSumber_dana() {
		return sumber_dana;
	}

	public void setSumber_dana(String sumber_dana) {
		this.sumber_dana = sumber_dana;
	}

	public String getSifat_investasi() {
		return sifat_investasi;
	}

	public void setSifat_investasi(String sifat_investasi) {
		this.sifat_investasi = sifat_investasi;
	}

	public String getBagi_hasil() {
		return bagi_hasil;
	}

	public void setBagi_hasil(String bagi_hasil) {
		this.bagi_hasil = bagi_hasil;
	}

	public Integer getPersentase_nisbah() {
		return persentase_nisbah;
	}

	public void setPersentase_nisbah(Integer persentase_nisbah) {
		this.persentase_nisbah = persentase_nisbah;
	}

	public Double getPersentase_rbh() {
		return persentase_rbh;
	}

	public void setPersentase_rbh(String persentase_rbh) {
		if (persentase_rbh.equals("") || persentase_rbh.equals("null")) {
			this.persentase_rbh = null;
		} else {
			this.persentase_rbh = Double.valueOf(persentase_rbh);
		}
	}



	public String getSektor_ekonomi_kur() {
		return sektor_ekonomi_kur;
	}

	public void setSektor_ekonomi_kur(String sektor_ekonomi_kur) {
		this.sektor_ekonomi_kur = sektor_ekonomi_kur;
	}

	public String getSektor_ekonomi() {
		return sektor_ekonomi;
	}

	public void setSektor_ekonomi(String sektor_ekonomi) {
		this.sektor_ekonomi = sektor_ekonomi;
	}

	public String getLokasi_pengguna() {
		return lokasi_pengguna;
	}

	public void setLokasi_pengguna(String lokasi_pengguna) {
		this.lokasi_pengguna = lokasi_pengguna;
	}

	public String getJenis_aset() {
		return jenis_aset;
	}

	public void setJenis_aset(String jenis_aset) {
		this.jenis_aset = jenis_aset;
	}

	public LocalDate getWaktu_perolehan_aset() {
		return waktu_perolehan_aset;
	}

	public void setWaktu_perolehan_aset(LocalDate waktu_perolehan_aset) {
		this.waktu_perolehan_aset = waktu_perolehan_aset;
	}

	public String getJenis_valuta_aset() {
		return jenis_valuta_aset;
	}

	public void setJenis_valuta_aset(String jenis_valuta_aset) {
		this.jenis_valuta_aset = jenis_valuta_aset;
	}





	public String getMetode_amortisasi() {
		return metode_amortisasi;
	}

	public void setMetode_amortisasi(String metode_amortisasi) {
		this.metode_amortisasi = metode_amortisasi;
	}

	public String getPeriode_sewa() {
		return periode_sewa;
	}

	public void setPeriode_sewa(String periode_sewa) {
		this.periode_sewa = periode_sewa;
	}

	public Integer getNilai_sewa_periode() {
		return nilai_sewa_periode;
	}

	public void setNilai_sewa_periode(Integer nilai_sewa_periode) {
		this.nilai_sewa_periode = nilai_sewa_periode;
	}



	public Double getPecadangan_penurunan_aset() {
		return pecadangan_penurunan_aset;
	}

	public void setPecadangan_penurunan_aset(Double pecadangan_penurunan_aset) {

		this.pecadangan_penurunan_aset = pecadangan_penurunan_aset;

	}

	public Double getUang_muka_ijarah() {
		return uang_muka_ijarah;
	}

	public void setUang_muka_ijarah(Double uang_muka_ijarah) {
		this.uang_muka_ijarah = uang_muka_ijarah;
	}


	public String getGolongan_pihak_lawan() {
		return golongan_pihak_lawan;
	}

	public void setGolongan_pihak_lawan(String golongan_pihak_lawan) {
		this.golongan_pihak_lawan = golongan_pihak_lawan;
	}

	public String getLembaga_pemeringkat() {
		return lembaga_pemeringkat;
	}

	public void setLembaga_pemeringkat(String lembaga_pemeringkat) {
		this.lembaga_pemeringkat = lembaga_pemeringkat;
	}

	public String getRating_pihak_lawan() {
		return rating_pihak_lawan;
	}

	public void setRating_pihak_lawan(String rating_pihak_lawan) {
		this.rating_pihak_lawan = rating_pihak_lawan;
	}

	public String getStatus_debitur() {
		return status_debitur;
	}

	public void setStatus_debitur(String status_debitur) {
		this.status_debitur = status_debitur;
	}

	public String getJenis_penggunaan() {
		return jenis_penggunaan;
	}

	public void setJenis_penggunaan(String jenis_penggunaan) {
		this.jenis_penggunaan = jenis_penggunaan;
	}

	public String getJenis_piutang() {
		return jenis_piutang;
	}

	public void setJenis_piutang(String jenis_piutang) {
		this.jenis_piutang = jenis_piutang;
	}

	public String getJenis_akad() {
		return jenis_akad;
	}

	public void setJenis_akad(String jenis_akad) {
		this.jenis_akad = jenis_akad;
	}

	public String getBi_status_piutang() {
		return bi_status_piutang;
	}

	public void setBi_status_piutang(String bi_status_piutang) {
		this.bi_status_piutang = bi_status_piutang;
	}

	public String getBi_sifat_akad() {
		return bi_sifat_akad;
	}

	public void setBi_sifat_akad(String bi_sifat_akad) {
		this.bi_sifat_akad = bi_sifat_akad;
	}

	public String getGolongan_debitur() {
		return golongan_debitur;
	}

	public void setGolongan_debitur(String golongan_debitur) {
		this.golongan_debitur = golongan_debitur;
	}

	public String getJenis_aktiva_ijarah() {
		return jenis_aktiva_ijarah;
	}

	public void setJenis_aktiva_ijarah(String jenis_aktiva_ijarah) {
		this.jenis_aktiva_ijarah = jenis_aktiva_ijarah;
	}

	public String getGolongan_piutang() {
		return golongan_piutang;
	}

	public void setGolongan_piutang(String golongan_piutang) {
		this.golongan_piutang = golongan_piutang;
	}

	public String getSifat_kredit_bi() {
		return sifat_kredit_bi;
	}

	public void setSifat_kredit_bi(String sifat_kredit_bi) {
		this.sifat_kredit_bi = sifat_kredit_bi;
	}

	public String getBi_tujuan_kredit() {
		return bi_tujuan_kredit;
	}

	public void setBi_tujuan_kredit(String bi_tujuan_kredit) {
		this.bi_tujuan_kredit = bi_tujuan_kredit;
	}

	public String getTujuan_garansi() {
		return tujuan_garansi;
	}

	public void setTujuan_garansi(String tujuan_garansi) {
		this.tujuan_garansi = tujuan_garansi;
	}

	public String getTgl_pemeringkat() {
		return tgl_pemeringkat;
	}

	public void setTgl_pemeringkat(String tgl_pemeringkat) {
		this.tgl_pemeringkat = tgl_pemeringkat;
	}

	public String getCara_restruktur() {
		return cara_restruktur;
	}

	public void setCara_restruktur(String cara_restruktur) {
		this.cara_restruktur = cara_restruktur;
	}

	public String getKode_sebab_macet() {
		return kode_sebab_macet;
	}

	public void setKode_sebab_macet(String kode_sebab_macet) {
		this.kode_sebab_macet = kode_sebab_macet;
	}

	public String getFreq_restruktur() {
		return freq_restruktur;
	}

	public void setFreq_restruktur(String freq_restruktur) {
		this.freq_restruktur = freq_restruktur;
	}

	public String getJenis_garansi() {
		return jenis_garansi;
	}

	public void setJenis_garansi(String jenis_garansi) {
		this.jenis_garansi = jenis_garansi;
	}

	public String getKode_kondisi() {
		return kode_kondisi;
	}

	public void setKode_kondisi(String kode_kondisi) {
		this.kode_kondisi = kode_kondisi;
	}

	public String getIs_restruktur() {
		return is_restruktur;
	}

	public void setIs_restruktur(String is_restruktur) {
		this.is_restruktur = is_restruktur;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getSumber_dana_bi() {
		return sumber_dana_bi;
	}

	public void setSumber_dana_bi(String sumber_dana_bi) {
		this.sumber_dana_bi = sumber_dana_bi;
	}

	public String getKode_sektor_ekonomi_bi() {
		return kode_sektor_ekonomi_bi;
	}

	public void setKode_sektor_ekonomi_bi(String kode_sektor_ekonomi_bi) {
		this.kode_sektor_ekonomi_bi = kode_sektor_ekonomi_bi;
	}

	public String getGol_debitur_sid() {
		return gol_debitur_sid;
	}

	public void setGol_debitur_sid(String gol_debitur_sid) {
		this.gol_debitur_sid = gol_debitur_sid;
	}

	public String getJenis_penggunaan_bi() {
		return jenis_penggunaan_bi;
	}

	public void setJenis_penggunaan_bi(String jenis_penggunaan_bi) {
		this.jenis_penggunaan_bi = jenis_penggunaan_bi;
	}

	public String getSifat_piutang() {
		return sifat_piutang;
	}

	public void setSifat_piutang(String sifat_piutang) {
		this.sifat_piutang = sifat_piutang;
	}

	public String getBadan_hukum() {
		return badan_hukum;
	}

	public void setBadan_hukum(String badan_hukum) {
		this.badan_hukum = badan_hukum;
	}

	public Integer getKategori_portofolio() {
		return kategori_portofolio;
	}

	public void setKategori_portofolio(Integer kategori_portofolio) {
		this.kategori_portofolio = kategori_portofolio;
	}

	public Integer getSifat_pembiayaan() {
		return sifat_pembiayaan;
	}

	public void setSifat_pembiayaan(Integer sifat_pembiayaan) {
		this.sifat_pembiayaan = sifat_pembiayaan;
	}

	public Integer getOrientasi_penggunaan() {
		return orientasi_penggunaan;
	}

	public void setOrientasi_penggunaan(Integer orientasi_penggunaan) {
		this.orientasi_penggunaan = orientasi_penggunaan;
	}

	public void setPersentase_rbh(Double persentase_rbh) {
		this.persentase_rbh = persentase_rbh;
	}

	public String getProgram_pemerintah() {
		return program_pemerintah;
	}

	public void setProgram_pemerintah(String program_pemerintah) {
		this.program_pemerintah = program_pemerintah;
	}

	public Double getHarga_perolehan_aset() {
		return harga_perolehan_aset;
	}

	public void setHarga_perolehan_aset(Double harga_perolehan_aset) {
		this.harga_perolehan_aset = harga_perolehan_aset;
	}

	public Double getJumlah_amortisasi() {
		return jumlah_amortisasi;
	}

	public void setJumlah_amortisasi(Double jumlah_amortisasi) {
		this.jumlah_amortisasi = jumlah_amortisasi;
	}

	public Integer getAmortisasi() {
		return amortisasi;
	}

	public void setAmortisasi(Integer amortisasi) {
		this.amortisasi = amortisasi;
	}

	public Integer getJenis_suku_imbalan() {
		return jenis_suku_imbalan;
	}

	public void setJenis_suku_imbalan(Integer jenis_suku_imbalan) {
		this.jenis_suku_imbalan = jenis_suku_imbalan;
	}

	public Double getPersentase_imbalan_awal_kontrak() {
		return persentase_imbalan_awal_kontrak;
	}

	public void setPersentase_imbalan_awal_kontrak(Double persentase_imbalan_awal_kontrak) {
		this.persentase_imbalan_awal_kontrak = persentase_imbalan_awal_kontrak;
	}

	public String getBagian_dijamin() {
		return bagian_dijamin;
	}

	public void setBagian_dijamin(String bagian_dijamin) {
		this.bagian_dijamin = bagian_dijamin;
	}

	public String getKategori_debitur() {
		return kategori_debitur;
	}

	public void setKategori_debitur(String kategori_debitur) {
		this.kategori_debitur = kategori_debitur;
	}

	public String getHubungan_bank() {
		return hubungan_bank;
	}

	public void setHubungan_bank(String hubungan_bank) {
		this.hubungan_bank = hubungan_bank;
	}

}
