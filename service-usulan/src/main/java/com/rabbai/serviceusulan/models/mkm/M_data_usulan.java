package com.rabbai.serviceusulan.models.mkm;

import javax.persistence.CascadeType;
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

import com.rabbai.serviceusulan.models.MScoringLoan;
import com.rabbai.serviceusulan.models.Par_asu;
import com.rabbai.serviceusulan.models.Par_broker;
import com.rabbai.serviceusulan.models.Par_cabang;
import com.rabbai.serviceusulan.models.Par_kategori_produk;
import com.rabbai.serviceusulan.models.Par_kode_aset_ijarah;
import com.rabbai.serviceusulan.models.Par_kode_margin;
import com.rabbai.serviceusulan.models.Par_plan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "m_data_usulan")
public class M_data_usulan implements Serializable {
	private static final long serialVersionUID = -2517851941873251699L;
	@Id
	private String id;
	private String id_cab;
	private LocalDateTime tgl_pengajuan;
	private String id_debitur;
	private Integer id_wallet;
	private Integer id_neraca;
	private Integer id_penghasilan;
	private String id_pasangan;
	private Double caoi;
	private Double pcda;
	private String ideb;
	private Integer hubungan_bank;
	private String norek;
	private String nama_bank;
	private String atas_nama;
	private Integer status_rekening;
	private Double saldo_rata_bulanan;
	private Integer hubungan_bank_lain;
	private String nama_bank_lain;
	private String slik_ref_pengguna;
	private String slik_nomor_laporan;
	private LocalDate slik_tgl_data_terakhir;
	private LocalDate slik_tgl_permintaan;
	private Integer is_take_over;
	private String take_over_nama_bank;
	private Double take_over_total;
	private String cp_jenis1;
	private Double cp_plafon1;
	private Double cp_baki_debet1;
	private Integer cp_jangka_waktu1;
	private Double cp_margin1;
	private Double cp_angsuran1;
	private String cp_tujuan_pinjaman1;
	private Double cp_tunggakan1;
	private String cp_kolektibilitas1;
	private String cp_jenis2;
	private Double cp_plafon2;
	private Double cp_baki_debet2;
	private Integer cp_jangka_waktu2;
	private Double cp_margin2;
	private Double cp_angsuran2;
	private String cp_tujuan_pinjaman2;
	private Double cp_tunggakan2;
	private String cp_kolektibilitas2;
	private String cp_jenis3;
	private Double cp_plafon3;
	private Double cp_baki_debet3;
	private Integer cp_jangka_waktu3;
	private Double cp_margin3;
	private Double cp_angsuran3;
	private String cp_tujuan_pinjaman3;
	private Double cp_tunggakan3;
	private String cp_kolektibilitas3;
	private String jenis_pembiayaan;
	private Double premi_asuransi_lama;
	private String tgl_kredit_lama;
	private String benefit_kredit_lama;
	private Integer tenor_kredit_lama;
	private Integer kuasa_pemotongan;
	private Integer persentase_kuasa_pemotongan;
	private String kode_officer_1;
	private String kode_officer_2;
	private String kode_referal;
	private String kode_notaris;
	private String tujuan_pembiayaan;
	private Integer id_kategori_produk;
	private Integer id_plan;
	private String kategori_template;
	private String frequensi_pembayaran_margin;
	private Integer frequensi_pembayaran_number;
	private Integer grace_period;
	private Double harga_perolehan;
	private Double pajak;
	private Double diskon;
	private Double plafon_pengajuan;
	private Double uang_muka;
	private Integer tenor_pengajuan;
	private String kode_margin;
	private BigDecimal margin_pengajuan;
	private Float basis_point_margin;
	private String basis_point_margin_mark;
	private Float exp_rate;
	private Double nisbah_bank;
	private Double nisbah_nasabah;
	private Double angsuran_pengajuan;
	private String ud_nama_usaha;
	private String ud_bidang_usaha;
	private Integer ud_lama_usaha;
	private String ud_status_tempat_usaha;
	private Double ud_omset_perbulan;
	private Double ud_profit_perbulan;
	private Integer s_kepemilikan_agunan;
	private Integer s_jenis_agunan;
	private Integer s_status_kepemilikan_agunan;
	private Integer s_kriteria_agunan;
	private String blokir_saldo;
	private String is_wakalah;
	private String norek_wakalah;
	private String kode_rekening_wakalah;
	private String nama_rekening_wakalah;
	private LocalDate tgl_kunjungan;
	private String officer_bank;
	private String kode_broker;
	private String benefit_broker;
	private String kode_asuransi;
	private String benefit_asuransi;
	private Double nilai_harta_thp_kredit;
	private String ap_aspek_manajemen;
	private String ap_aspek_pemasaran;
	private String ap_aspek_teknis;
	private String ap_aspek_syariah;
	private String ap_orang_yang_ditemui;
	private Double copph;
	private Double jumlah_cost_of_project;
	private Double by_mp_kebun;
	private Double ttl_copph_allby;
	private Double dana_bantuan;
	private Double self_financing;
	private String created_by;
	private String created_nama;
	private LocalDateTime created_date;
	private String input_by;
	private String input_nama;
	private LocalDateTime input_date;
	private String input2_by;
	private String input2_nama;
	private LocalDateTime input2_date;
	private String review_by;
	private String review_nama;
	private LocalDateTime review_date;
	private String review_desc;
	private String submit_by;
	private String submit_nama;
	private LocalDateTime submit_date;
	private String review2_by;
	private String review2_nama;
	private LocalDateTime review2_date;
	private String review2_desc;
	private String submit2_by;
	private String submit2_nama;
	private LocalDateTime submit2_date;
	private String approve1_by;
	private String approve1_nama;
	private LocalDateTime approve1_date;
	private String approve12_by;
	private String approve12_nama;
	private LocalDateTime approve12_date;
	private String approve2_by;
	private String approve2_nama;
	private LocalDateTime approve2_date;
	private String approve3_by;
	private String approve3_nama;
	private LocalDateTime approve3_date;
	private Double plafon_disetujui;
	private Integer tenor_disetujui;
	private Double angsuran_disetujui;
	private String rekomendasi;
	private String keputusan;
	private String no_sp3;
	private String sp3_file;
	private String no_sp4;
	private String sp4_file;
	private String is_biaya_adm;
	private Double persentase_biaya_adm;
	private Double biaya_adm;
	private Double biaya_asuransi_jiwa;
	private Double biaya_asuransi_kebakaran;
	private Double biaya_asuransi_pembiayaan;
	private Double biaya_materai;
	private String nomor_akad;
	private String akad_by;
	private String akad_nama;
	private LocalDateTime akad_date;
	private String akad_file;
	private String upload_akad_by;
	private String upload_akad_nama;
	private LocalDateTime upload_akad_date;
	private String bawa_pasangan;
	private String kode_mata_uang;
	private Integer norek_pencairan;
	private String nama_rekening_pencairan;
	private String kode_rekening_pencairan;
	private String norek_proceed;
	private String nama_rekening_proceed;
	private String kode_rekening_proceed;
	private String norek_penghasilan;
	private String nama_rekening_penghasilan;
	private String kode_rekening_penghasilan;
	private String norek_loan;
	private String kode_rekening_loan;
	private String nama_rekening_loan;
	private String cair_by;
	private String cair_name;
	private LocalDateTime cair_date;
	private String approve_cair1_by;
	private LocalDateTime approve_cair1_date;
	private String approve_cair1_name;
	private String approve_cair2_by;
	private LocalDateTime approve_cair2_date;
	private String approve_cair2_name;
	private String approve_cair3_by;
	private LocalDateTime approve_cair3_date;
	private String approve_cair3_name;
	private String n22num;
	private Integer n22dt1;
	private Integer n22dt2;
	private Integer n22day;
	private String n22ds1;
	private Integer n21brg;
	private Integer n21jum;
	private Double n21hde;
	private Double n21tak;
	private Double n21pby;
	private Double n21mxp;
	private Double n21ujr;
	private String status_qaca;
	private String catatan_qaca;
	private String qaca_review_by_id;
	private String qaca_review_by_nama;
	private LocalDateTime qaca_review_date;
	private String no_tiket;
	private String status_vlink;
	private String status;
	private Integer id_template_dokumen;
	private String fr_file_review;
	private String fr_reviewer;
	private String fr_hasil_review;
	private LocalDate fr_tgl_review;
	private String notisi_file;
	private String id_loan;
	private String id_old_usulan;
	private String cancel_by;
	private String cancel_nama;
	private LocalDateTime cancel_date;
	private String cancel_desc;
	private String is_forward;
	private String barang;
	

	// PARENT PAR par_agunan_basel2_jenis_agunan
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_margin", insertable = false, updatable = false)
	private Par_kode_margin desc_kode_margin;

	@JsonProperty("desc_kode_margin")
	public String getPar_kode_margin() {
		if (desc_kode_margin == null) {
			return "";
		} else {
			return desc_kode_margin.getName();
		}
	}

	// PARENT DEBITUR
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "id_debitur", insertable = false, updatable = false)
	private M_data_debitur m_data_debitur;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "id_penghasilan", insertable = false, updatable = false)
	private M_data_penghasilan m_data_penghasilan;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "id_pasangan", insertable = false, updatable = false)
	private M_data_pasangan m_data_pasangan;

	// PARENT CABANG
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne
	@JoinColumn(name = "id_cab", insertable = false, updatable = false)
	private Par_cabang cabang;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = MScoringLoan.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id_loan", insertable = false, updatable = false)
	private List<MScoringLoan> mscoringloan;


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = M_data_hub_perbankan_detail.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id_loan", insertable = false, updatable = false)
	@OrderBy("periode ASC")
	private List<M_data_hub_perbankan_detail> m_data_hub_perbankan_detail;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = M_data_agunan.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id_loan", insertable = false, updatable = false)
	private List<M_data_agunan> m_data_agunan;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = M_data_harta.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id_loan", insertable = false, updatable = false)
	private List<M_data_harta> m_data_harta;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = M_data_bi.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id_loan", insertable = false, updatable = false)
	private List<M_data_bi> m_data_bi;

	//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = M_data_usulan.class)
	@JoinColumn(name = "id_loan", referencedColumnName = "id", insertable = false, updatable = false)
	@OrderBy("input_date DESC")
	private List<M_data_usulan> m_data_usulan;

	@JsonProperty("usulan")
	public List<String> getUsulan() {
		List<String> numbers = new ArrayList<>();
		//System.out.println("########## GET ID USULAN #########" + usulan.get(0).getId() + " " + usulan.size());
		if (m_data_usulan.size() > 0) {
			return numbers = Arrays.asList(m_data_usulan.get(0).getId());
		} else {
			return numbers = Arrays.asList();

		}
	}

	// PARENT PAR KATEGORI PRODUK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_kategori_produk", insertable = false, updatable = false, nullable = true)
	private Par_kategori_produk par_kategori_produk;

	@JsonProperty("nama_kategori_produk")
	public String getNama_kategori_produk() {
		if (par_kategori_produk == null) {
			return "";
		} else {
		return par_kategori_produk.getName();
		}
	}

	// PARENT PAR PLAN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_plan", insertable = false, updatable = false, nullable = true)
	private Par_plan par_plan;

	@JsonProperty("nama_sub_produk")
	public String getPar_plan() {
		if (par_plan == null) {
			return "";
		} else {
			return par_plan.getPar_sub_produk();
		}
	}

	@JsonProperty("id_sub_produk")
	public Integer getId_sub_produk() {
		if (par_plan == null) {
			return 0;
		} else {
			return par_plan.getId_sub_produk();
		}
	}
	
	@JsonProperty("type_plan")
	public Integer getType_plan() {
		if (par_plan == null) {
			return null;
		} else {
			return par_plan.getType();
		}
	}

	@JsonProperty("nama_plan")
	public String getPar_plan_name() {
		if (par_plan == null) {
			return "";
		} else {
			return par_plan.getName();
		}
	}

	@JsonProperty("nama_produk")
	public String getPar_produk() {
		if (par_plan == null) {
			return "";
		} else {
			return par_plan.getPar_produk();
		}
	}

	@JsonProperty("id_produk")
	public Integer getId_produk() {
		if (par_plan == null) {
			return 0;
		} else {
			return par_plan.getId_produk();
		}
	}

//	@JsonProperty("id_template_dokumen")
//	public Integer getId_template_dokumen() {
//		if (par_plan == null) {
//			return 0;
//		} else {
//			return par_plan.getId_template_dokumen();
//		}
//	}

	@JsonProperty("inisial_akad")
	public String getInisial_akad() {
		if (par_plan == null) {
			return "";
		} else {
			return par_plan.getInisial_akad();
		}
	}

	// PARENT PAR BROKER
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_broker", insertable = false, updatable = false)
	private Par_broker par_broker;

	@JsonProperty("nama_broker")
	public String getNama_broker() {
		if (par_broker == null) {
			return "";
		} else {
			return par_broker.getName();
		}

	}

	// PARENT PAR ASURANSI
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kode_asuransi", insertable = false, updatable = false)
	private Par_asu par_asuransi;

	@JsonProperty("desc_asuransi")
	public String getNama_asuransi() {
		if (par_asuransi == null) {
			return "";
		} else {
			return par_asuransi.getName();
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_cab() {
		return id_cab;
	}

	public void setId_cab(String id_cab) {
		this.id_cab = id_cab;
	}

	public LocalDateTime getTgl_pengajuan() {
		return tgl_pengajuan;
	}

	public void setTgl_pengajuan(LocalDateTime tgl_pengajuan) {
		this.tgl_pengajuan = tgl_pengajuan;
	}

	public String getId_debitur() {
		return id_debitur;
	}

	public void setId_debitur(String id_debitur) {
		this.id_debitur = id_debitur;
	}

	public Integer getId_wallet() {
		return id_wallet;
	}

	public void setId_wallet(Integer id_wallet) {
		this.id_wallet = id_wallet;
	}

	public Integer getId_neraca() {
		return id_neraca;
	}

	public void setId_neraca(Integer id_neraca) {
		this.id_neraca = id_neraca;
	}

	public Integer getId_penghasilan() {
		return id_penghasilan;
	}

	public void setId_penghasilan(Integer id_penghasilan) {
		this.id_penghasilan = id_penghasilan;
	}

	public String getId_pasangan() {
		return id_pasangan;
	}

	public void setId_pasangan(String id_pasangan) {
		this.id_pasangan = id_pasangan;
	}

	public Double getCaoi() {
		return caoi;
	}

	public void setCaoi(Double caoi) {
		this.caoi = caoi;
	}

	public Double getPcda() {
		return pcda;
	}

	public void setPcda(Double pcda) {
		this.pcda = pcda;
	}

	public String getIdeb() {
		return ideb;
	}

	public void setIdeb(String ideb) {
		this.ideb = ideb;
	}

	public Integer getHubungan_bank() {
		return hubungan_bank;
	}

	public void setHubungan_bank(Integer hubungan_bank) {
		this.hubungan_bank = hubungan_bank;
	}

	public String getNorek() {
		return norek;
	}

	public void setNorek(String norek) {
		this.norek = norek;
	}

	public String getNama_bank() {
		return nama_bank;
	}

	public void setNama_bank(String nama_bank) {
		this.nama_bank = nama_bank;
	}

	public String getAtas_nama() {
		return atas_nama;
	}

	public void setAtas_nama(String atas_nama) {
		this.atas_nama = atas_nama;
	}

	public Integer getStatus_rekening() {
		return status_rekening;
	}

	public void setStatus_rekening(Integer status_rekening) {
		this.status_rekening = status_rekening;
	}

	public Double getSaldo_rata_bulanan() {
		return saldo_rata_bulanan;
	}

	public void setSaldo_rata_bulanan(Double saldo_rata_bulanan) {
		this.saldo_rata_bulanan = saldo_rata_bulanan;
	}

	public Integer getHubungan_bank_lain() {
		return hubungan_bank_lain;
	}

	public void setHubungan_bank_lain(Integer hubungan_bank_lain) {
		this.hubungan_bank_lain = hubungan_bank_lain;
	}

	public String getNama_bank_lain() {
		return nama_bank_lain;
	}

	public void setNama_bank_lain(String nama_bank_lain) {
		this.nama_bank_lain = nama_bank_lain;
	}

	public String getSlik_ref_pengguna() {
		return slik_ref_pengguna;
	}

	public void setSlik_ref_pengguna(String slik_ref_pengguna) {
		this.slik_ref_pengguna = slik_ref_pengguna;
	}

	public String getSlik_nomor_laporan() {
		return slik_nomor_laporan;
	}

	public void setSlik_nomor_laporan(String slik_nomor_laporan) {
		this.slik_nomor_laporan = slik_nomor_laporan;
	}

	public LocalDate getSlik_tgl_data_terakhir() {
		return slik_tgl_data_terakhir;
	}

	public void setSlik_tgl_data_terakhir(LocalDate slik_tgl_data_terakhir) {
		this.slik_tgl_data_terakhir = slik_tgl_data_terakhir;
	}

	public LocalDate getSlik_tgl_permintaan() {
		return slik_tgl_permintaan;
	}

	public void setSlik_tgl_permintaan(LocalDate slik_tgl_permintaan) {
		this.slik_tgl_permintaan = slik_tgl_permintaan;
	}

	public Integer getIs_take_over() {
		return is_take_over;
	}

	public void setIs_take_over(Integer is_take_over) {
		this.is_take_over = is_take_over;
	}

	public String getTake_over_nama_bank() {
		return take_over_nama_bank;
	}

	public void setTake_over_nama_bank(String take_over_nama_bank) {
		this.take_over_nama_bank = take_over_nama_bank;
	}

	public Double getTake_over_total() {
		return take_over_total;
	}

	public void setTake_over_total(Double take_over_total) {
		this.take_over_total = take_over_total;
	}

	public String getCp_jenis1() {
		return cp_jenis1;
	}

	public void setCp_jenis1(String cp_jenis1) {
		this.cp_jenis1 = cp_jenis1;
	}

	public Double getCp_plafon1() {
		return cp_plafon1;
	}

	public void setCp_plafon1(Double cp_plafon1) {
		this.cp_plafon1 = cp_plafon1;
	}

	public Double getCp_baki_debet1() {
		return cp_baki_debet1;
	}

	public void setCp_baki_debet1(Double cp_baki_debet1) {
		this.cp_baki_debet1 = cp_baki_debet1;
	}

	public Integer getCp_jangka_waktu1() {
		return cp_jangka_waktu1;
	}

	public void setCp_jangka_waktu1(Integer cp_jangka_waktu1) {
		this.cp_jangka_waktu1 = cp_jangka_waktu1;
	}

	public Double getCp_margin1() {
		return cp_margin1;
	}

	public void setCp_margin1(Double cp_margin1) {
		this.cp_margin1 = cp_margin1;
	}

	public Double getCp_angsuran1() {
		return cp_angsuran1;
	}

	public void setCp_angsuran1(Double cp_angsuran1) {
		this.cp_angsuran1 = cp_angsuran1;
	}

	public String getCp_tujuan_pinjaman1() {
		return cp_tujuan_pinjaman1;
	}

	public void setCp_tujuan_pinjaman1(String cp_tujuan_pinjaman1) {
		this.cp_tujuan_pinjaman1 = cp_tujuan_pinjaman1;
	}

	public Double getCp_tunggakan1() {
		return cp_tunggakan1;
	}

	public void setCp_tunggakan1(Double cp_tunggakan1) {
		this.cp_tunggakan1 = cp_tunggakan1;
	}

	public String getCp_kolektibilitas1() {
		return cp_kolektibilitas1;
	}

	public void setCp_kolektibilitas1(String cp_kolektibilitas1) {
		this.cp_kolektibilitas1 = cp_kolektibilitas1;
	}

	public String getCp_jenis2() {
		return cp_jenis2;
	}

	public void setCp_jenis2(String cp_jenis2) {
		this.cp_jenis2 = cp_jenis2;
	}

	public Double getCp_plafon2() {
		return cp_plafon2;
	}

	public void setCp_plafon2(Double cp_plafon2) {
		this.cp_plafon2 = cp_plafon2;
	}

	public Double getCp_baki_debet2() {
		return cp_baki_debet2;
	}

	public void setCp_baki_debet2(Double cp_baki_debet2) {
		this.cp_baki_debet2 = cp_baki_debet2;
	}

	public Integer getCp_jangka_waktu2() {
		return cp_jangka_waktu2;
	}

	public void setCp_jangka_waktu2(Integer cp_jangka_waktu2) {
		this.cp_jangka_waktu2 = cp_jangka_waktu2;
	}

	public Double getCp_margin2() {
		return cp_margin2;
	}

	public void setCp_margin2(Double cp_margin2) {
		this.cp_margin2 = cp_margin2;
	}

	public Double getCp_angsuran2() {
		return cp_angsuran2;
	}

	public void setCp_angsuran2(Double cp_angsuran2) {
		this.cp_angsuran2 = cp_angsuran2;
	}

	public String getCp_tujuan_pinjaman2() {
		return cp_tujuan_pinjaman2;
	}

	public void setCp_tujuan_pinjaman2(String cp_tujuan_pinjaman2) {
		this.cp_tujuan_pinjaman2 = cp_tujuan_pinjaman2;
	}

	public Double getCp_tunggakan2() {
		return cp_tunggakan2;
	}

	public void setCp_tunggakan2(Double cp_tunggakan2) {
		this.cp_tunggakan2 = cp_tunggakan2;
	}

	public String getCp_kolektibilitas2() {
		return cp_kolektibilitas2;
	}

	public void setCp_kolektibilitas2(String cp_kolektibilitas2) {
		this.cp_kolektibilitas2 = cp_kolektibilitas2;
	}

	public String getCp_jenis3() {
		return cp_jenis3;
	}

	public void setCp_jenis3(String cp_jenis3) {
		this.cp_jenis3 = cp_jenis3;
	}

	public Double getCp_plafon3() {
		return cp_plafon3;
	}

	public void setCp_plafon3(Double cp_plafon3) {
		this.cp_plafon3 = cp_plafon3;
	}

	public Double getCp_baki_debet3() {
		return cp_baki_debet3;
	}

	public void setCp_baki_debet3(Double cp_baki_debet3) {
		this.cp_baki_debet3 = cp_baki_debet3;
	}

	public Integer getCp_jangka_waktu3() {
		return cp_jangka_waktu3;
	}

	public void setCp_jangka_waktu3(Integer cp_jangka_waktu3) {
		this.cp_jangka_waktu3 = cp_jangka_waktu3;
	}

	public Double getCp_margin3() {
		return cp_margin3;
	}

	public void setCp_margin3(Double cp_margin3) {
		this.cp_margin3 = cp_margin3;
	}

	public Double getCp_angsuran3() {
		return cp_angsuran3;
	}

	public void setCp_angsuran3(Double cp_angsuran3) {
		this.cp_angsuran3 = cp_angsuran3;
	}

	public String getCp_tujuan_pinjaman3() {
		return cp_tujuan_pinjaman3;
	}

	public void setCp_tujuan_pinjaman3(String cp_tujuan_pinjaman3) {
		this.cp_tujuan_pinjaman3 = cp_tujuan_pinjaman3;
	}

	public Double getCp_tunggakan3() {
		return cp_tunggakan3;
	}

	public void setCp_tunggakan3(Double cp_tunggakan3) {
		this.cp_tunggakan3 = cp_tunggakan3;
	}

	public String getCp_kolektibilitas3() {
		return cp_kolektibilitas3;
	}

	public void setCp_kolektibilitas3(String cp_kolektibilitas3) {
		this.cp_kolektibilitas3 = cp_kolektibilitas3;
	}

	public String getJenis_pembiayaan() {
		return jenis_pembiayaan;
	}

	public void setJenis_pembiayaan(String jenis_pembiayaan) {
		this.jenis_pembiayaan = jenis_pembiayaan;
	}

	public Double getPremi_asuransi_lama() {
		return premi_asuransi_lama;
	}

	public void setPremi_asuransi_lama(Double premi_asuransi_lama) {
		this.premi_asuransi_lama = premi_asuransi_lama;
	}

	public String getTgl_kredit_lama() {
		return tgl_kredit_lama;
	}

	public void setTgl_kredit_lama(String tgl_kredit_lama) {
		this.tgl_kredit_lama = tgl_kredit_lama;
	}

	public String getBenefit_kredit_lama() {
		return benefit_kredit_lama;
	}

	public void setBenefit_kredit_lama(String benefit_kredit_lama) {
		this.benefit_kredit_lama = benefit_kredit_lama;
	}

	public Integer getTenor_kredit_lama() {
		return tenor_kredit_lama;
	}

	public void setTenor_kredit_lama(Integer tenor_kredit_lama) {
		this.tenor_kredit_lama = tenor_kredit_lama;
	}

	public Integer getKuasa_pemotongan() {
		return kuasa_pemotongan;
	}

	public void setKuasa_pemotongan(Integer kuasa_pemotongan) {
		this.kuasa_pemotongan = kuasa_pemotongan;
	}

	public Integer getPersentase_kuasa_pemotongan() {
		return persentase_kuasa_pemotongan;
	}

	public void setPersentase_kuasa_pemotongan(Integer persentase_kuasa_pemotongan) {
		this.persentase_kuasa_pemotongan = persentase_kuasa_pemotongan;
	}

	public String getKode_officer_1() {
		return kode_officer_1;
	}

	public void setKode_officer_1(String kode_officer_1) {
		this.kode_officer_1 = kode_officer_1;
	}

	public String getKode_officer_2() {
		return kode_officer_2;
	}

	public void setKode_officer_2(String kode_officer_2) {
		this.kode_officer_2 = kode_officer_2;
	}

	public String getKode_referal() {
		return kode_referal;
	}

	public void setKode_referal(String kode_referal) {
		this.kode_referal = kode_referal;
	}

	public String getKode_notaris() {
		return kode_notaris;
	}

	public void setKode_notaris(String kode_notaris) {
		this.kode_notaris = kode_notaris;
	}

	public String getTujuan_pembiayaan() {
		return tujuan_pembiayaan;
	}

	public void setTujuan_pembiayaan(String tujuan_pembiayaan) {
		this.tujuan_pembiayaan = tujuan_pembiayaan;
	}

	public Integer getId_kategori_produk() {
		return id_kategori_produk;
	}

	public void setId_kategori_produk(Integer id_kategori_produk) {
		this.id_kategori_produk = id_kategori_produk;
	}

	public Integer getId_plan() {
		return id_plan;
	}

	public void setId_plan(Integer id_plan) {
		this.id_plan = id_plan;
	}

	public String getKategori_template() {
		return kategori_template;
	}

	public void setKategori_template(String kategori_template) {
		this.kategori_template = kategori_template;
	}

	public String getFrequensi_pembayaran_margin() {
		return frequensi_pembayaran_margin;
	}

	public void setFrequensi_pembayaran_margin(String frequensi_pembayaran_margin) {
		this.frequensi_pembayaran_margin = frequensi_pembayaran_margin;
	}

	public Integer getFrequensi_pembayaran_number() {
		return frequensi_pembayaran_number;
	}

	public void setFrequensi_pembayaran_number(Integer frequensi_pembayaran_number) {
		this.frequensi_pembayaran_number = frequensi_pembayaran_number;
	}

	public Integer getGrace_period() {
		return grace_period;
	}

	public void setGrace_period(Integer grace_period) {
		this.grace_period = grace_period;
	}

	public Double getHarga_perolehan() {
		return harga_perolehan;
	}

	public void setHarga_perolehan(Double harga_perolehan) {
		this.harga_perolehan = harga_perolehan;
	}

	public Double getPajak() {
		return pajak;
	}

	public void setPajak(Double pajak) {
		this.pajak = pajak;
	}

	public Double getDiskon() {
		return diskon;
	}

	public void setDiskon(Double diskon) {
		this.diskon = diskon;
	}

	public Double getPlafon_pengajuan() {
		return plafon_pengajuan;
	}

	public void setPlafon_pengajuan(Double plafon_pengajuan) {
		this.plafon_pengajuan = plafon_pengajuan;
	}

	public Double getUang_muka() {
		return uang_muka;
	}

	public void setUang_muka(Double uang_muka) {
		this.uang_muka = uang_muka;
	}

	public Integer getTenor_pengajuan() {
		return tenor_pengajuan;
	}

	public void setTenor_pengajuan(Integer tenor_pengajuan) {
		this.tenor_pengajuan = tenor_pengajuan;
	}

	public String getKode_margin() {
		return kode_margin;
	}

	public void setKode_margin(String kode_margin) {
		this.kode_margin = kode_margin;
	}

	

	public String getBasis_point_margin_mark() {
		return basis_point_margin_mark;
	}

	public void setBasis_point_margin_mark(String basis_point_margin_mark) {
		this.basis_point_margin_mark = basis_point_margin_mark;
	}

	

	public Double getNisbah_bank() {
		return nisbah_bank;
	}

	public void setNisbah_bank(Double nisbah_bank) {
		this.nisbah_bank = nisbah_bank;
	}

	public Double getNisbah_nasabah() {
		return nisbah_nasabah;
	}

	public void setNisbah_nasabah(Double nisbah_nasabah) {
		this.nisbah_nasabah = nisbah_nasabah;
	}

	public Double getAngsuran_pengajuan() {
		return angsuran_pengajuan;
	}

	public void setAngsuran_pengajuan(Double angsuran_pengajuan) {
		this.angsuran_pengajuan = angsuran_pengajuan;
	}

	public String getUd_nama_usaha() {
		return ud_nama_usaha;
	}

	public void setUd_nama_usaha(String ud_nama_usaha) {
		this.ud_nama_usaha = ud_nama_usaha;
	}

	public String getUd_bidang_usaha() {
		return ud_bidang_usaha;
	}

	public void setUd_bidang_usaha(String ud_bidang_usaha) {
		this.ud_bidang_usaha = ud_bidang_usaha;
	}

	public Integer getUd_lama_usaha() {
		return ud_lama_usaha;
	}

	public void setUd_lama_usaha(Integer ud_lama_usaha) {
		this.ud_lama_usaha = ud_lama_usaha;
	}

	public String getUd_status_tempat_usaha() {
		return ud_status_tempat_usaha;
	}

	public void setUd_status_tempat_usaha(String ud_status_tempat_usaha) {
		this.ud_status_tempat_usaha = ud_status_tempat_usaha;
	}

	public Double getUd_omset_perbulan() {
		return ud_omset_perbulan;
	}

	public void setUd_omset_perbulan(Double ud_omset_perbulan) {
		this.ud_omset_perbulan = ud_omset_perbulan;
	}

	public Double getUd_profit_perbulan() {
		return ud_profit_perbulan;
	}

	public void setUd_profit_perbulan(Double ud_profit_perbulan) {
		this.ud_profit_perbulan = ud_profit_perbulan;
	}

	public Integer getS_kepemilikan_agunan() {
		return s_kepemilikan_agunan;
	}

	public void setS_kepemilikan_agunan(Integer s_kepemilikan_agunan) {
		this.s_kepemilikan_agunan = s_kepemilikan_agunan;
	}

	public Integer getS_jenis_agunan() {
		return s_jenis_agunan;
	}

	public void setS_jenis_agunan(Integer s_jenis_agunan) {
		this.s_jenis_agunan = s_jenis_agunan;
	}

	public Integer getS_status_kepemilikan_agunan() {
		return s_status_kepemilikan_agunan;
	}

	public void setS_status_kepemilikan_agunan(Integer s_status_kepemilikan_agunan) {
		this.s_status_kepemilikan_agunan = s_status_kepemilikan_agunan;
	}

	public Integer getS_kriteria_agunan() {
		return s_kriteria_agunan;
	}

	public void setS_kriteria_agunan(Integer s_kriteria_agunan) {
		this.s_kriteria_agunan = s_kriteria_agunan;
	}

	public String getBlokir_saldo() {
		return blokir_saldo;
	}

	public void setBlokir_saldo(String blokir_saldo) {
		this.blokir_saldo = blokir_saldo;
	}

	public String getIs_wakalah() {
		return is_wakalah;
	}

	public void setIs_wakalah(String is_wakalah) {
		this.is_wakalah = is_wakalah;
	}

	public String getNorek_wakalah() {
		return norek_wakalah;
	}

	public void setNorek_wakalah(String norek_wakalah) {
		this.norek_wakalah = norek_wakalah;
	}

	public String getKode_rekening_wakalah() {
		return kode_rekening_wakalah;
	}

	public void setKode_rekening_wakalah(String kode_rekening_wakalah) {
		this.kode_rekening_wakalah = kode_rekening_wakalah;
	}

	public String getNama_rekening_wakalah() {
		return nama_rekening_wakalah;
	}

	public void setNama_rekening_wakalah(String nama_rekening_wakalah) {
		this.nama_rekening_wakalah = nama_rekening_wakalah;
	}

	public LocalDate getTgl_kunjungan() {
		return tgl_kunjungan;
	}

	public void setTgl_kunjungan(LocalDate tgl_kunjungan) {
		this.tgl_kunjungan = tgl_kunjungan;
	}

	public String getOfficer_bank() {
		return officer_bank;
	}

	public void setOfficer_bank(String officer_bank) {
		this.officer_bank = officer_bank;
	}

	public String getKode_broker() {
		return kode_broker;
	}

	public void setKode_broker(String kode_broker) {
		this.kode_broker = kode_broker;
	}

	public String getBenefit_broker() {
		return benefit_broker;
	}

	public void setBenefit_broker(String benefit_broker) {
		this.benefit_broker = benefit_broker;
	}

	public String getKode_asuransi() {
		return kode_asuransi;
	}

	public void setKode_asuransi(String kode_asuransi) {
		this.kode_asuransi = kode_asuransi;
	}

	public String getBenefit_asuransi() {
		return benefit_asuransi;
	}

	public void setBenefit_asuransi(String benefit_asuransi) {
		this.benefit_asuransi = benefit_asuransi;
	}

	public Double getNilai_harta_thp_kredit() {
		return nilai_harta_thp_kredit;
	}

	public void setNilai_harta_thp_kredit(Double nilai_harta_thp_kredit) {
		this.nilai_harta_thp_kredit = nilai_harta_thp_kredit;
	}

	public String getAp_aspek_manajemen() {
		return ap_aspek_manajemen;
	}

	public void setAp_aspek_manajemen(String ap_aspek_manajemen) {
		this.ap_aspek_manajemen = ap_aspek_manajemen;
	}

	public String getAp_aspek_pemasaran() {
		return ap_aspek_pemasaran;
	}

	public void setAp_aspek_pemasaran(String ap_aspek_pemasaran) {
		this.ap_aspek_pemasaran = ap_aspek_pemasaran;
	}

	public String getAp_aspek_teknis() {
		return ap_aspek_teknis;
	}

	public void setAp_aspek_teknis(String ap_aspek_teknis) {
		this.ap_aspek_teknis = ap_aspek_teknis;
	}

	public String getAp_aspek_syariah() {
		return ap_aspek_syariah;
	}

	public void setAp_aspek_syariah(String ap_aspek_syariah) {
		this.ap_aspek_syariah = ap_aspek_syariah;
	}

	public Double getCopph() {
		return copph;
	}

	public void setCopph(Double copph) {
		this.copph = copph;
	}

	public Double getJumlah_cost_of_project() {
		return jumlah_cost_of_project;
	}

	public void setJumlah_cost_of_project(Double jumlah_cost_of_project) {
		this.jumlah_cost_of_project = jumlah_cost_of_project;
	}

	public Double getBy_mp_kebun() {
		return by_mp_kebun;
	}

	public void setBy_mp_kebun(Double by_mp_kebun) {
		this.by_mp_kebun = by_mp_kebun;
	}

	public Double getTtl_copph_allby() {
		return ttl_copph_allby;
	}

	public void setTtl_copph_allby(Double ttl_copph_allby) {
		this.ttl_copph_allby = ttl_copph_allby;
	}

	public Double getDana_bantuan() {
		return dana_bantuan;
	}

	public void setDana_bantuan(Double dana_bantuan) {
		this.dana_bantuan = dana_bantuan;
	}

	public Double getSelf_financing() {
		return self_financing;
	}

	public void setSelf_financing(Double self_financing) {
		this.self_financing = self_financing;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreated_nama() {
		return created_nama;
	}

	public void setCreated_nama(String created_nama) {
		this.created_nama = created_nama;
	}

	public LocalDateTime getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}

	public String getInput_by() {
		return input_by;
	}

	public void setInput_by(String input_by) {
		this.input_by = input_by;
	}

	public String getInput_nama() {
		return input_nama;
	}

	public void setInput_nama(String input_nama) {
		this.input_nama = input_nama;
	}

	public LocalDateTime getInput_date() {
		return input_date;
	}

	public void setInput_date(LocalDateTime input_date) {
		this.input_date = input_date;
	}

	public String getInput2_by() {
		return input2_by;
	}

	public void setInput2_by(String input2_by) {
		this.input2_by = input2_by;
	}

	public String getInput2_nama() {
		return input2_nama;
	}

	public void setInput2_nama(String input2_nama) {
		this.input2_nama = input2_nama;
	}

	public LocalDateTime getInput2_date() {
		return input2_date;
	}

	public void setInput2_date(LocalDateTime input2_date) {
		this.input2_date = input2_date;
	}

	public String getReview_by() {
		return review_by;
	}

	public void setReview_by(String review_by) {
		this.review_by = review_by;
	}

	public String getReview_nama() {
		return review_nama;
	}

	public void setReview_nama(String review_nama) {
		this.review_nama = review_nama;
	}

	public LocalDateTime getReview_date() {
		return review_date;
	}

	public void setReview_date(LocalDateTime review_date) {
		this.review_date = review_date;
	}

	public String getReview_desc() {
		return review_desc;
	}

	public void setReview_desc(String review_desc) {
		this.review_desc = review_desc;
	}

	public String getSubmit_by() {
		return submit_by;
	}

	public void setSubmit_by(String submit_by) {
		this.submit_by = submit_by;
	}

	public String getSubmit_nama() {
		return submit_nama;
	}

	public void setSubmit_nama(String submit_nama) {
		this.submit_nama = submit_nama;
	}

	public LocalDateTime getSubmit_date() {
		return submit_date;
	}

	public void setSubmit_date(LocalDateTime submit_date) {
		this.submit_date = submit_date;
	}

	public String getReview2_by() {
		return review2_by;
	}

	public void setReview2_by(String review2_by) {
		this.review2_by = review2_by;
	}

	public String getReview2_nama() {
		return review2_nama;
	}

	public void setReview2_nama(String review2_nama) {
		this.review2_nama = review2_nama;
	}

	public LocalDateTime getReview2_date() {
		return review2_date;
	}

	public void setReview2_date(LocalDateTime review2_date) {
		this.review2_date = review2_date;
	}

	public String getReview2_desc() {
		return review2_desc;
	}

	public void setReview2_desc(String review2_desc) {
		this.review2_desc = review2_desc;
	}

	public String getSubmit2_by() {
		return submit2_by;
	}

	public void setSubmit2_by(String submit2_by) {
		this.submit2_by = submit2_by;
	}

	public String getSubmit2_nama() {
		return submit2_nama;
	}

	public void setSubmit2_nama(String submit2_nama) {
		this.submit2_nama = submit2_nama;
	}

	public LocalDateTime getSubmit2_date() {
		return submit2_date;
	}

	public void setSubmit2_date(LocalDateTime submit2_date) {
		this.submit2_date = submit2_date;
	}

	public String getApprove1_by() {
		return approve1_by;
	}

	public void setApprove1_by(String approve1_by) {
		this.approve1_by = approve1_by;
	}

	public String getApprove1_nama() {
		return approve1_nama;
	}

	public void setApprove1_nama(String approve1_nama) {
		this.approve1_nama = approve1_nama;
	}

	public LocalDateTime getApprove1_date() {
		return approve1_date;
	}

	public void setApprove1_date(LocalDateTime approve1_date) {
		this.approve1_date = approve1_date;
	}

	public String getApprove12_by() {
		return approve12_by;
	}

	public void setApprove12_by(String approve12_by) {
		this.approve12_by = approve12_by;
	}

	public String getApprove12_nama() {
		return approve12_nama;
	}

	public void setApprove12_nama(String approve12_nama) {
		this.approve12_nama = approve12_nama;
	}

	public LocalDateTime getApprove12_date() {
		return approve12_date;
	}

	public void setApprove12_date(LocalDateTime approve12_date) {
		this.approve12_date = approve12_date;
	}

	public String getApprove2_by() {
		return approve2_by;
	}

	public void setApprove2_by(String approve2_by) {
		this.approve2_by = approve2_by;
	}

	public String getApprove2_nama() {
		return approve2_nama;
	}

	public void setApprove2_nama(String approve2_nama) {
		this.approve2_nama = approve2_nama;
	}

	public LocalDateTime getApprove2_date() {
		return approve2_date;
	}

	public void setApprove2_date(LocalDateTime approve2_date) {
		this.approve2_date = approve2_date;
	}

	public String getApprove3_by() {
		return approve3_by;
	}

	public void setApprove3_by(String approve3_by) {
		this.approve3_by = approve3_by;
	}

	public String getApprove3_nama() {
		return approve3_nama;
	}

	public void setApprove3_nama(String approve3_nama) {
		this.approve3_nama = approve3_nama;
	}

	public LocalDateTime getApprove3_date() {
		return approve3_date;
	}

	public void setApprove3_date(LocalDateTime approve3_date) {
		this.approve3_date = approve3_date;
	}

	public Double getPlafon_disetujui() {
		return plafon_disetujui;
	}

	public void setPlafon_disetujui(Double plafon_disetujui) {
		this.plafon_disetujui = plafon_disetujui;
	}

	public Double getAngsuran_disetujui() {
		return angsuran_disetujui;
	}

	public void setAngsuran_disetujui(Double angsuran_disetujui) {
		this.angsuran_disetujui = angsuran_disetujui;
	}

	public String getRekomendasi() {
		return rekomendasi;
	}

	public void setRekomendasi(String rekomendasi) {
		this.rekomendasi = rekomendasi;
	}

	public String getKeputusan() {
		return keputusan;
	}

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}

	public String getNo_sp3() {
		return no_sp3;
	}

	public void setNo_sp3(String no_sp3) {
		this.no_sp3 = no_sp3;
	}

	public String getSp3_file() {
		return sp3_file;
	}

	public void setSp3_file(String sp3_file) {
		this.sp3_file = sp3_file;
	}

	public String getNo_sp4() {
		return no_sp4;
	}

	public void setNo_sp4(String no_sp4) {
		this.no_sp4 = no_sp4;
	}

	public String getSp4_file() {
		return sp4_file;
	}

	public void setSp4_file(String sp4_file) {
		this.sp4_file = sp4_file;
	}

	public String getIs_biaya_adm() {
		return is_biaya_adm;
	}

	public void setIs_biaya_adm(String is_biaya_adm) {
		this.is_biaya_adm = is_biaya_adm;
	}

	public Double getPersentase_biaya_adm() {
		return persentase_biaya_adm;
	}

	public void setPersentase_biaya_adm(Double persentase_biaya_adm) {
		this.persentase_biaya_adm = persentase_biaya_adm;
	}

	public Double getBiaya_adm() {
		return biaya_adm;
	}

	public void setBiaya_adm(Double biaya_adm) {
		this.biaya_adm = biaya_adm;
	}

	public Double getBiaya_asuransi_jiwa() {
		return biaya_asuransi_jiwa;
	}

	public void setBiaya_asuransi_jiwa(Double biaya_asuransi_jiwa) {
		this.biaya_asuransi_jiwa = biaya_asuransi_jiwa;
	}

	public Double getBiaya_asuransi_kebakaran() {
		return biaya_asuransi_kebakaran;
	}

	public void setBiaya_asuransi_kebakaran(Double biaya_asuransi_kebakaran) {
		this.biaya_asuransi_kebakaran = biaya_asuransi_kebakaran;
	}

	public Double getBiaya_asuransi_pembiayaan() {
		return biaya_asuransi_pembiayaan;
	}

	public void setBiaya_asuransi_pembiayaan(Double biaya_asuransi_pembiayaan) {
		this.biaya_asuransi_pembiayaan = biaya_asuransi_pembiayaan;
	}

	public Double getBiaya_materai() {
		return biaya_materai;
	}

	public void setBiaya_materai(Double biaya_materai) {
		this.biaya_materai = biaya_materai;
	}

	public String getNomor_akad() {
		return nomor_akad;
	}

	public void setNomor_akad(String nomor_akad) {
		this.nomor_akad = nomor_akad;
	}

	public String getAkad_by() {
		return akad_by;
	}

	public void setAkad_by(String akad_by) {
		this.akad_by = akad_by;
	}

	public String getAkad_nama() {
		return akad_nama;
	}

	public void setAkad_nama(String akad_nama) {
		this.akad_nama = akad_nama;
	}

	public LocalDateTime getAkad_date() {
		return akad_date;
	}

	public void setAkad_date(LocalDateTime akad_date) {
		this.akad_date = akad_date;
	}

	public String getAkad_file() {
		return akad_file;
	}

	public void setAkad_file(String akad_file) {
		this.akad_file = akad_file;
	}

	public String getUpload_akad_by() {
		return upload_akad_by;
	}

	public void setUpload_akad_by(String upload_akad_by) {
		this.upload_akad_by = upload_akad_by;
	}

	public String getUpload_akad_nama() {
		return upload_akad_nama;
	}

	public void setUpload_akad_nama(String upload_akad_nama) {
		this.upload_akad_nama = upload_akad_nama;
	}

	public LocalDateTime getUpload_akad_date() {
		return upload_akad_date;
	}

	public void setUpload_akad_date(LocalDateTime upload_akad_date) {
		this.upload_akad_date = upload_akad_date;
	}

	public String getBawa_pasangan() {
		return bawa_pasangan;
	}

	public void setBawa_pasangan(String bawa_pasangan) {
		this.bawa_pasangan = bawa_pasangan;
	}

	public String getKode_mata_uang() {
		return kode_mata_uang;
	}

	public void setKode_mata_uang(String kode_mata_uang) {
		this.kode_mata_uang = kode_mata_uang;
	}

	public Integer getNorek_pencairan() {
		return norek_pencairan;
	}

	public void setNorek_pencairan(Integer norek_pencairan) {
		this.norek_pencairan = norek_pencairan;
	}

	public String getNama_rekening_pencairan() {
		return nama_rekening_pencairan;
	}

	public void setNama_rekening_pencairan(String nama_rekening_pencairan) {
		this.nama_rekening_pencairan = nama_rekening_pencairan;
	}

	public String getKode_rekening_pencairan() {
		return kode_rekening_pencairan;
	}

	public void setKode_rekening_pencairan(String kode_rekening_pencairan) {
		this.kode_rekening_pencairan = kode_rekening_pencairan;
	}

	public String getNorek_proceed() {
		return norek_proceed;
	}

	public void setNorek_proceed(String norek_proceed) {
		this.norek_proceed = norek_proceed;
	}

	public String getNama_rekening_proceed() {
		return nama_rekening_proceed;
	}

	public void setNama_rekening_proceed(String nama_rekening_proceed) {
		this.nama_rekening_proceed = nama_rekening_proceed;
	}

	public String getKode_rekening_proceed() {
		return kode_rekening_proceed;
	}

	public void setKode_rekening_proceed(String kode_rekening_proceed) {
		this.kode_rekening_proceed = kode_rekening_proceed;
	}

	public String getNorek_penghasilan() {
		return norek_penghasilan;
	}

	public void setNorek_penghasilan(String norek_penghasilan) {
		this.norek_penghasilan = norek_penghasilan;
	}

	public String getNama_rekening_penghasilan() {
		return nama_rekening_penghasilan;
	}

	public void setNama_rekening_penghasilan(String nama_rekening_penghasilan) {
		this.nama_rekening_penghasilan = nama_rekening_penghasilan;
	}

	public String getKode_rekening_penghasilan() {
		return kode_rekening_penghasilan;
	}

	public void setKode_rekening_penghasilan(String kode_rekening_penghasilan) {
		this.kode_rekening_penghasilan = kode_rekening_penghasilan;
	}

	public String getNorek_loan() {
		return norek_loan;
	}

	public void setNorek_loan(String norek_loan) {
		this.norek_loan = norek_loan;
	}

	public String getKode_rekening_loan() {
		return kode_rekening_loan;
	}

	public void setKode_rekening_loan(String kode_rekening_loan) {
		this.kode_rekening_loan = kode_rekening_loan;
	}

	public String getNama_rekening_loan() {
		return nama_rekening_loan;
	}

	public void setNama_rekening_loan(String nama_rekening_loan) {
		this.nama_rekening_loan = nama_rekening_loan;
	}

	public String getCair_by() {
		return cair_by;
	}

	public void setCair_by(String cair_by) {
		this.cair_by = cair_by;
	}

	public String getCair_name() {
		return cair_name;
	}

	public void setCair_name(String cair_name) {
		this.cair_name = cair_name;
	}

	public LocalDateTime getCair_date() {
		return cair_date;
	}

	public void setCair_date(LocalDateTime cair_date) {
		this.cair_date = cair_date;
	}

	public String getApprove_cair1_by() {
		return approve_cair1_by;
	}

	public void setApprove_cair1_by(String approve_cair1_by) {
		this.approve_cair1_by = approve_cair1_by;
	}

	public LocalDateTime getApprove_cair1_date() {
		return approve_cair1_date;
	}

	public void setApprove_cair1_date(LocalDateTime approve_cair1_date) {
		this.approve_cair1_date = approve_cair1_date;
	}

	public String getApprove_cair1_name() {
		return approve_cair1_name;
	}

	public void setApprove_cair1_name(String approve_cair1_name) {
		this.approve_cair1_name = approve_cair1_name;
	}

	public String getApprove_cair2_by() {
		return approve_cair2_by;
	}

	public void setApprove_cair2_by(String approve_cair2_by) {
		this.approve_cair2_by = approve_cair2_by;
	}

	public LocalDateTime getApprove_cair2_date() {
		return approve_cair2_date;
	}

	public void setApprove_cair2_date(LocalDateTime approve_cair2_date) {
		this.approve_cair2_date = approve_cair2_date;
	}

	public String getApprove_cair2_name() {
		return approve_cair2_name;
	}

	public void setApprove_cair2_name(String approve_cair2_name) {
		this.approve_cair2_name = approve_cair2_name;
	}

	public String getApprove_cair3_by() {
		return approve_cair3_by;
	}

	public void setApprove_cair3_by(String approve_cair3_by) {
		this.approve_cair3_by = approve_cair3_by;
	}

	public LocalDateTime getApprove_cair3_date() {
		return approve_cair3_date;
	}

	public void setApprove_cair3_date(LocalDateTime approve_cair3_date) {
		this.approve_cair3_date = approve_cair3_date;
	}

	public String getApprove_cair3_name() {
		return approve_cair3_name;
	}

	public void setApprove_cair3_name(String approve_cair3_name) {
		this.approve_cair3_name = approve_cair3_name;
	}

	public String getN22num() {
		return n22num;
	}

	public void setN22num(String n22num) {
		this.n22num = n22num;
	}

	public Integer getN22dt1() {
		return n22dt1;
	}

	public void setN22dt1(Integer n22dt1) {
		this.n22dt1 = n22dt1;
	}

	public Integer getN22dt2() {
		return n22dt2;
	}

	public void setN22dt2(Integer n22dt2) {
		this.n22dt2 = n22dt2;
	}

	public Integer getN22day() {
		return n22day;
	}

	public void setN22day(Integer n22day) {
		this.n22day = n22day;
	}

	public String getN22ds1() {
		return n22ds1;
	}

	public void setN22ds1(String n22ds1) {
		this.n22ds1 = n22ds1;
	}

	public Integer getN21brg() {
		return n21brg;
	}

	public void setN21brg(Integer n21brg) {
		this.n21brg = n21brg;
	}

	public Integer getN21jum() {
		return n21jum;
	}

	public void setN21jum(Integer n21jum) {
		this.n21jum = n21jum;
	}

	public Double getN21hde() {
		return n21hde;
	}

	public void setN21hde(Double n21hde) {
		this.n21hde = n21hde;
	}

	public Double getN21tak() {
		return n21tak;
	}

	public void setN21tak(Double n21tak) {
		this.n21tak = n21tak;
	}

	public Double getN21pby() {
		return n21pby;
	}

	public void setN21pby(Double n21pby) {
		this.n21pby = n21pby;
	}

	public Double getN21mxp() {
		return n21mxp;
	}

	public void setN21mxp(Double n21mxp) {
		this.n21mxp = n21mxp;
	}

	public Double getN21ujr() {
		return n21ujr;
	}

	public void setN21ujr(Double n21ujr) {
		this.n21ujr = n21ujr;
	}

	public String getStatus_qaca() {
		return status_qaca;
	}

	public void setStatus_qaca(String status_qaca) {
		this.status_qaca = status_qaca;
	}

	public String getCatatan_qaca() {
		return catatan_qaca;
	}

	public void setCatatan_qaca(String catatan_qaca) {
		this.catatan_qaca = catatan_qaca;
	}

	public String getQaca_review_by_id() {
		return qaca_review_by_id;
	}

	public void setQaca_review_by_id(String qaca_review_by_id) {
		this.qaca_review_by_id = qaca_review_by_id;
	}

	public String getQaca_review_by_nama() {
		return qaca_review_by_nama;
	}

	public void setQaca_review_by_nama(String qaca_review_by_nama) {
		this.qaca_review_by_nama = qaca_review_by_nama;
	}

	public LocalDateTime getQaca_review_date() {
		return qaca_review_date;
	}

	public void setQaca_review_date(LocalDateTime qaca_review_date) {
		this.qaca_review_date = qaca_review_date;
	}

	public String getNo_tiket() {
		return no_tiket;
	}

	public void setNo_tiket(String no_tiket) {
		this.no_tiket = no_tiket;
	}

	public String getStatus_vlink() {
		return status_vlink;
	}

	public void setStatus_vlink(String status_vlink) {
		this.status_vlink = status_vlink;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId_template_dokumen() {
		return id_template_dokumen;
	}

	public void setId_template_dokumen(Integer id_template_dokumen) {
		this.id_template_dokumen = id_template_dokumen;
	}

	public Par_kode_margin getDesc_kode_margin() {
		return desc_kode_margin;
	}

	public void setDesc_kode_margin(Par_kode_margin desc_kode_margin) {
		this.desc_kode_margin = desc_kode_margin;
	}

	public M_data_debitur getM_data_debitur() {
		return m_data_debitur;
	}

	public void setM_data_debitur(M_data_debitur m_data_debitur) {
		this.m_data_debitur = m_data_debitur;
	}

	public M_data_penghasilan getM_data_penghasilan() {
		return m_data_penghasilan;
	}

	public void setM_data_penghasilan(M_data_penghasilan m_data_penghasilan) {
		this.m_data_penghasilan = m_data_penghasilan;
	}

	public Par_cabang getCabang() {
		return cabang;
	}

	public void setCabang(Par_cabang cabang) {
		this.cabang = cabang;
	}

	public List<MScoringLoan> getMscoringloan() {
		return mscoringloan;
	}

	public void setMscoringloan(List<MScoringLoan> mscoringloan) {
		this.mscoringloan = mscoringloan;
	}



	public List<M_data_hub_perbankan_detail> getM_data_hub_perbankan_detail() {
		return m_data_hub_perbankan_detail;
	}

	public void setM_data_hub_perbankan_detail(List<M_data_hub_perbankan_detail> m_data_hub_perbankan_detail) {
		this.m_data_hub_perbankan_detail = m_data_hub_perbankan_detail;
	}

	public List<M_data_agunan> getM_data_agunan() {
		return m_data_agunan;
	}

	public void setM_data_agunan(List<M_data_agunan> m_data_agunan) {
		this.m_data_agunan = m_data_agunan;
	}

	public List<M_data_harta> getM_data_harta() {
		return m_data_harta;
	}

	public void setM_data_harta(List<M_data_harta> m_data_harta) {
		this.m_data_harta = m_data_harta;
	}


//	public Par_kategori_produk getPar_kategori_produk() {
//		return par_kategori_produk;
//	}

	public void setPar_kategori_produk(Par_kategori_produk par_kategori_produk) {
		this.par_kategori_produk = par_kategori_produk;
	}


	public void setPar_broker(Par_broker par_broker) {
		this.par_broker = par_broker;
	}


	public void setPar_asuransi(Par_asu par_asuransi) {
		this.par_asuransi = par_asuransi;
	}


	public void setPar_plan(Par_plan par_plan) {
		this.par_plan = par_plan;
	}

	public String getAp_orang_yang_ditemui() {
		return ap_orang_yang_ditemui;
	}

	public void setAp_orang_yang_ditemui(String ap_orang_yang_ditemui) {
		this.ap_orang_yang_ditemui = ap_orang_yang_ditemui;
	}



	public Float getBasis_point_margin() {
		return basis_point_margin;
	}

	public void setBasis_point_margin(Float basis_point_margin) {
		this.basis_point_margin = basis_point_margin;
	}

	public Float getExp_rate() {
		return exp_rate;
	}

	public void setExp_rate(Float exp_rate) {
		this.exp_rate = exp_rate;
	}

	public Integer getTenor_disetujui() {
		return tenor_disetujui;
	}

	public void setTenor_disetujui(Integer tenor_disetujui) {
		this.tenor_disetujui = tenor_disetujui;
	}

	public M_data_pasangan getM_data_pasangan() {
		return m_data_pasangan;
	}

	public void setM_data_pasangan(M_data_pasangan m_data_pasangan) {
		this.m_data_pasangan = m_data_pasangan;
	}

	public String getFr_file_review() {
		return fr_file_review;
	}

	public void setFr_file_review(String fr_file_review) {
		this.fr_file_review = fr_file_review;
	}

	public String getFr_reviewer() {
		return fr_reviewer;
	}

	public void setFr_reviewer(String fr_reviewer) {
		this.fr_reviewer = fr_reviewer;
	}

	public String getFr_hasil_review() {
		return fr_hasil_review;
	}

	public void setFr_hasil_review(String fr_hasil_review) {
		this.fr_hasil_review = fr_hasil_review;
	}

	public LocalDate getFr_tgl_review() {
		return fr_tgl_review;
	}

	public void setFr_tgl_review(LocalDate fr_tgl_review) {
		this.fr_tgl_review = fr_tgl_review;
	}

	public String getNotisi_file() {
		return notisi_file;
	}

	public void setNotisi_file(String notisi_file) {
		this.notisi_file = notisi_file;
	}

	public List<M_data_bi> getM_data_bi() {
		return m_data_bi;
	}

	public void setM_data_bi(List<M_data_bi> m_data_bi) {
		this.m_data_bi = m_data_bi;
	}

	public List<M_data_usulan> getM_data_usulan() {
		return m_data_usulan;
	}

	public void setM_data_usulan(List<M_data_usulan> m_data_usulan) {
		this.m_data_usulan = m_data_usulan;
	}

	public String getId_loan() {
		return id_loan;
	}

	public void setId_loan(String id_loan) {
		this.id_loan = id_loan;
	}

	public String getId_old_usulan() {
		return id_old_usulan;
	}

	public void setId_old_usulan(String id_old_usulan) {
		this.id_old_usulan = id_old_usulan;
	}

	public String getCancel_by() {
		return cancel_by;
	}

	public void setCancel_by(String cancel_by) {
		this.cancel_by = cancel_by;
	}

	public String getCancel_nama() {
		return cancel_nama;
	}

	public void setCancel_nama(String cancel_nama) {
		this.cancel_nama = cancel_nama;
	}

	public LocalDateTime getCancel_date() {
		return cancel_date;
	}

	public void setCancel_date(LocalDateTime cancel_date) {
		this.cancel_date = cancel_date;
	}

	public String getCancel_desc() {
		return cancel_desc;
	}

	public void setCancel_desc(String cancel_desc) {
		this.cancel_desc = cancel_desc;
	}

	public String getIs_forward() {
		return is_forward;
	}

	public void setIs_forward(String is_forward) {
		this.is_forward = is_forward;
	}

	public String getBarang() {
		return barang;
	}

	public void setBarang(String barang) {
		this.barang = barang;
	}

	public BigDecimal getMargin_pengajuan() {
		return margin_pengajuan;
	}

	public void setMargin_pengajuan(BigDecimal margin_pengajuan) {
		this.margin_pengajuan = margin_pengajuan;
	}


}
